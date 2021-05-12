import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

//
// ChatRobot implementation
//
public class ChatRobot implements MessageListener {
    private ChatConfiguration conf;
    private IChatServer server = null;
    private IChatUser user = null;

    public ChatRobot(ChatConfiguration conf) {
        this.conf = conf;
    }

    private void doConnect() throws Exception {
        //Guardar el nom en el servidor de noms del bot
        try {
            Registry reg = LocateRegistry.getRegistry(conf.getNameServiceHost(), conf.getNameServicePort());
            server = (IChatServer) reg.lookup(conf.getServerName());
        } catch (RemoteException e) {
            throw new Exception("rmiregistry not found at '" + conf.getNameServiceHost() + ":" +
                    conf.getNameServicePort() + "'");
        } catch (NotBoundException e) {
            throw new Exception("Server '" + conf.getServerName() + "' not found.");
        }

        //Unir al bot al canal com a usuari
            //el Hashcode li afig un valor unic al nom, per a que ningu
            //li copie el nom com a usuari
        user = new ChatUser("bot " + this.hashCode(), this);
        if (!server.connectUser(user))
            throw new Exception("Nick already in use");

        //Comprovar si hi ha canals en el servidor.
        IChatChannel[] channels = server.listChannels();
        if (channels == null || channels.length == 0)
            throw new Exception("Server has no channels");
            
        //Unir el bot a cadascun dels canals
        for (IChatChannel channel : channels) {
            channel.join(user);
        }

        System.out.println("Bot unit als canals i preparat.");
    }

    @Override
    public void messageArrived(IChatMessage msg) {
        //Si el message comensa amb join es que s'ha unit un user al canal.
        try {            
            if (msg.getText().matches("JOIN .*")) {
                //Obtenir el canal on s'ha unit.
                IChatChannel channel = server.getChannel(conf.getChannelName());                              
                //Crear el missatge per a donar la benvinguda al usuari
                IChatMessage message = new ChatMessage(user, channel,
                        "Benvingut, " + msg.getText().substring(ChatChannel.JOIN.length() + 1));
                //Enviar el missatge
                channel.sendMessage(message);
            }
        } catch (RemoteException e) {
            
        }
    }

    public static void main(String[] args) throws Exception {
        ChatRobot robot = new ChatRobot(ChatConfiguration.parse(args));
        robot.doConnect();
    }
}