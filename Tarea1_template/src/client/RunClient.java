package client;

import java.io.IOException;
import java.rmi.NotBoundException;


public class RunClient {
	
	 public static void main(String[] args) throws NotBoundException, IOException{
	        
	        Client cliente = new Client();
	        cliente.conectar();

	        System.out.println("¡Conectado al servidor!");

	        while (true) {
	            System.out.println("\n--- Menú Cliente ---");
	            System.out.println("1. Mostrar la base de datos de ofertas");
	            System.out.println("2. Obtener datos desde la API");
	            System.out.println("3. Obtener ofertas desde la API");
	            System.out.println("0. Salir");
	            System.out.print("Seleccione una opción: ");

	            try {
	                char bufferInput = (char) System.in.read();

	                switch (bufferInput) {
	                    case '1':
	                        cliente.getOfertas(); 
	                        break;
	                    case '2':
	                    	System.out.println(cliente.getDataFromApi());
	                        break;
	                    case '3':
	                    	System.out.println(cliente.getOfertaApi());
	                    	break;
	                    case '0':
	                        System.out.println("Saliendo...");
	                        return;
	                    default:
	                        System.out.println("Opción no válida.");
	                        break;
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Error: Ingrese un número válido."); 
	            }
	            System.in.skip(System.in.available());
	        }

	        // Cerrar scanners al salir
	        //menuScanner.close();
	        //cliente.cerrarScanner();
	        //System.out.println("Cliente desconectado.");
	    }

}
