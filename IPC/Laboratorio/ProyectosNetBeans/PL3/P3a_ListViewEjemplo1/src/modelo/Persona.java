package modelo;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
	
	private final StringProperty Nombre = new SimpleStringProperty();
	private final StringProperty Apellidos = new SimpleStringProperty();

        
        //ANDREU: Amb click dret i 'Insert Code' el programa ens ajuda a crear capçáleres de codi mes rapidament.
        // Podem sobreescriure metodes com el toString i altres coses. Mirar-ho, que es super interesant.
        //Amb aqueixa idea he sobreescrit el metode toString, que son les 4 linies següents.Estan comentades 
        // per lass PersonaCelda extends ListCell<Persona> del controlador
        
        //EltoString esta comentat per a que funcione el metode updateItem que hem fet per a les celes 
//        @Override
//        //Fer que el toString trega el nom i cognom i no el nom del objecte/posicio de memotia
//        public String toString() {
//            return Nombre.get() + " " + Apellidos.get();
//        }
	
        
	public Persona(String nombre, String apellidos)
	{
		Nombre.setValue(nombre);
		Apellidos.setValue(apellidos);
	}
	
	public final StringProperty NombreProperty() {
		return this.Nombre;
	}
	public final java.lang.String getNombre() {
		return this.NombreProperty().get();
	}
	public final void setNombre(final java.lang.String Nombre) {
		this.NombreProperty().set(Nombre);
	}
	public final StringProperty ApellidosProperty() {
		return this.Apellidos;
	}
	public final java.lang.String getApellidos() {
		return this.ApellidosProperty().get();
	}
	public final void setApellidos(final java.lang.String Apellidos) {
		this.ApellidosProperty().set(Apellidos);
	}

//    @Override
//    public String toString() {
//        return Apellidos.getValue()+", "+Nombre.getValue();
//    }
        

}