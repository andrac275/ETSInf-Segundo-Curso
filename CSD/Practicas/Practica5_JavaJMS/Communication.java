package comm;

import java.util.ArrayList;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import messageBodies.AckChatMessage;
import messageBodies.NewChatMessage;
import messageBodies.NewUser;
import messageBodies.ReadedChatMessage;
import messageBodies.UserStart;
import messageBodies.UsersList;
import ui.Model;

/**
 * 
 * @author Agustín Espinosa Minguet (aespinos@upvnet.upv.es)
 *
 */
public class Communication implements Runnable {
    public static Communication theCommunication = new Communication();
    private InitialContext ic;
    private ConnectionFactory cfac;
    private JMSContext jmsContext;
    private JMSProducer producer;    
    
    private Communication() {
    }

    static Communication getCommunication() {
        return theCommunication;
    }
    
    /**
     * Este método es llamado cuando se completa la inicialización de la
        * interfaz de usuario. Su función consiste en inicializar el componente de
     * comunicación.
     * 
     */
    void initialize() throws Exception{
        // ACTIVIDAD 3
        // 6.1Iniciamos el contexto JNDI. Se requiere que exista el fichero
        // jndi.properties correctamente configurado.                   
            ic = new InitialContext();
        
        // 6.2Establecemos la conexión con el broker           
            cfac = (ConnectionFactory) ic.lookup("ConnectionFactory");
        
        // 6.3Creamos el contexto JMS
            jmsContext = cfac.createContext();
        
        // 6.4Productor para enviar los mensajes a los usuarios
            producer = jmsContext.createProducer();
        
        //6.5Crear Objeto UserStart pasando al constructor "" como parametro
            UserStart us =new UserStart("");
        
        //6.6Crear mensaje asignando el objeto anterior
        //ACTIVIDAD 3
        //Linea siguiente para la act3 pero para la 4 son otras cosas dejando
        //el resto inalterado. La siguiente la comento porque no hara mas falta.
            //ObjectMessage message=jmsContext.createObjectMessage(us);
            
        //Lineas para la ACTIVIDAD 4: Punto 1
            //Objeto UserStart pasando el constructor ui.API.getMyName()
            UserStart us2 =new UserStart(ui.API.getMyName());
            //Construir el mensaje con el objeto UserStart anterior 
            ObjectMessage message=jmsContext.createObjectMessage(us2);
            //Crear una cola temportal JMS
            Queue colaTemporal = jmsContext.createTemporaryQueue();
            //Asignar la cola temporal a la propiedad ReplyTo
            message.setJMSReplyTo(colaTemporal);       
              
        // 6.7Accedemos a la cola donde esperamos las peticiones
            Queue queue = (Queue) ic.lookup("dynamicQueues/csd");
    
            //6.8Enviar el mensaje creado en 6.6 a la cola del punto 6.7 usando
                //el producer de 6.4
            producer.send(queue, message);
    
         //Lineas para la ACTIVIDAD 4: Punto 2
            //Creamos un consumidor asociado a la cola temporal
            JMSConsumer consumer = jmsContext.createConsumer(colaTemporal);            
           
            boolean missatgeRebut = false;
            //Esperamos llegada mensaje
            while(!missatgeRebut){
                ObjectMessage m = (ObjectMessage)consumer.receive();
                if(m!=null){                    
                    //LLamar al m�todo updateUserList() para actualizar lista de usuarios 
                    missatgeRebut = true;
                    UsersList listaUsers = (UsersList) m.getObject();  
                   System.out.println("hola Antes ui API");
                    //Llamar al metodo updateUserList pasando el array recibido como argumento
                    ui.API.updateUserList(listaUsers.getUsers());
                }else{break;}
            }
    }

    /*
     * Hilo receptor de mensajes
     */
    public void run(){
        // ACTIVIDAD 6
        //1.1 Crear contexto JMS con el objeto global de la act 3, usando createContext
            //pasando como parametro AUTO_ACKNOWLEDGE
        jmsContext = cfac.createContext(JMSContext.AUTO_ACKNOWLEDGE);
        
        //1.2Crear un producer asociado al contexto anterior
        producer = jmsContext.createProducer();
        
        //1.3Buscar cola asociada al nombre "dynamicQueues/users-"+ui.API.getMyName()
        try{
            Queue queue = (Queue) ic.lookup("dynamicQueues/users-"+ui.API.getMyName());
            //1.4 Crear consumer para la cola anterior asociado al contexto creado en el punto 1.1
            JMSConsumer consumer = jmsContext.createConsumer(queue);
            //1.5 Esperamos permanente la llegada de mensajes a la cola creada previamente
            while(true){                
                ObjectMessage m = (ObjectMessage)consumer.receive();
                String nomClase = m.getObject().getClass().toString();
                //1.6Comprobar si el mensaje 'm' es de la clase NewChatMessage
                if(nomClase.equals("class messageBodies.NewChatMessage")){                    
                    //1.6.1 Notificar a la interfaz de usuario la llegada del nuevo mensaje, con el metodo
                        //chatMessageReceived de la API: ui.API.chatMessageReceived();
                    System.out.println("Missatge instanceOf");
                    //Mensaje nuevo para actualizar la interfaz
                    NewChatMessage nouMess= (NewChatMessage) m.getObject();
                    ui.API.chatMessageReceived(nouMess.getSenderName(), nouMess.getText(), nouMess.getTimestamp());
                    
                }else{//1.7 Si el objeto contenido en el mensaje es de otra clase diferente
                    //1.7.1 Muestre en el Log el nombre de dicha clase.
                    System.out.println("No es la mateixa clase");
                    
                }
            }
            
        }catch (NamingException e){
        
        } catch (JMSException e){}
        
        
        
    }

    /**
     * Envía un mensaje de chat al usuario especificado.
     * 
     * @param destUser
     *            El usuario destinatario
     * @param text
     *            El texto del mensaje
     * @param timestamp
     *            La hora local del sistema
     * @throws NamingException
     * @throws JMSException
     */
    void sendChatMessage(String destUser, String text, long timestamp) throws NamingException, JMSException {
        // ACTIVIDAD 5
        //Buscar cola asociada al nombre "dynamicQueues/users-"+destUser
        Queue queue = (Queue) ic.lookup("dynamicQueues/users-"+destUser);
        
        //Crear objeto NewChatMessage y pasar constructor los argumentos text, ui.API.getMyName() y timestamp
        NewChatMessage chatMessage = new NewChatMessage(text, ui.API.getMyName(), timestamp);
        
        //Construir mensaje JMS y asignar el objeto anterior
         ObjectMessage message=jmsContext.createObjectMessage(chatMessage);
         
        //Enviar el mensaje utilizando la cola creada y el mensaje utilizando el mismo "producer" (atrib global) de la act3
        producer.send(queue, message);
    }

    /**
     * Notifica al usuario especificado que un mensaje en particular ha sido
     * leído.
     * 
     * @param user
     *            El usuario que envió el mensaje que se ha leído
     * @param timestamp
     *            La hora que fue establecida en origen del mensaje que se ha
     *            leído
     * @throws NamingException
     * @throws JMSException
     */
    void sendMessageReaded(String user, long timestamp) throws NamingException, JMSException {
        

    }

}
