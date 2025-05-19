package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import common.Oferta;

public class RunClient {

    public static void main(String[] args) {

<<<<<<< HEAD
        Client cliente = new Client();
        try {
            cliente.conectar();
            System.out.println("¡Conectado al servidor!");
        } catch (RemoteException | NotBoundException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
            e.printStackTrace();
            return;
        }
=======
	        while (true) {
	            System.out.println("\n--- Menú Cliente ---");
	            System.out.println("1. Mostrar la base de datos de ofertas");
	            System.out.println("2. Obtener datos desde la API");
	            System.out.println("3. Obtener ofertas desde la API");
	            System.out.println("0. Salir");
	            System.out.print("Seleccione una opción: ");
>>>>>>> daa25f9c8e9faf47f249dfddf0c25f79e7dd7e18


<<<<<<< HEAD
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("\n--- Menú Cliente ---");
                System.out.println("1. Mostrar la base de datos de ofertas");
                System.out.println("2. Agregar nueva oferta");
                System.out.println("3. Mostrar datos testing API");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
=======
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
>>>>>>> daa25f9c8e9faf47f249dfddf0c25f79e7dd7e18

                String inputLine = reader.readLine();

                if (inputLine == null) break;
                if (inputLine.isEmpty()) {
                    System.out.println("Opción no válida.");
                    continue;
                }

                char choice = inputLine.charAt(0);

                switch (choice) {
                    case '1':
                        	System.out.println("\n------ Lista de Ofertas ------");
	                    	System.out.println("ID PROVEEDOR TIPO PRECIO");
	                    	cliente.getOfertas(); 
	                        System.out.println("-------------------------------");
                        break;
                    case '2':
                        try {
                            System.out.println("\n--- Agregar Nueva Oferta ---");
                            System.out.print("Ingrese Proveedor: ");
                            String proveedor = reader.readLine();
                            System.out.print("Ingrese Tipo: ");
                            String tipo = reader.readLine();
                            System.out.print("Ingrese Precio: ");
                            int precio = Integer.parseInt(reader.readLine());

                            Oferta nuevaOferta = new Oferta(0, proveedor, precio, tipo);
                            cliente.agregarNuevaOferta(nuevaOferta);
                            System.out.println("\nOferta agregada.");
                            System.out.println("-------------------------------");
                        } catch (NumberFormatException e) {
                            System.err.println("Error: El precio debe ser un número válido.");
                        } catch (RemoteException e) {
                            System.err.println("Error al agregar oferta en el servidor: " + e.getMessage());
                        } catch (IOException e) { 
                            System.err.println("Error de entrada/salida: " + e.getMessage());
                        }
                        break;
                    case '3':
                        try {
                            System.out.println("\n--- Datos de la API ---");
                            System.out.println(cliente.getDataFromApi());
                            System.out.println("------------------------");
                        } catch (RemoteException e) {
                            System.err.println("Error al obtener datos de la API desde el servidor: " + e.getMessage());
                        }
                        break;
                    case '0':
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error con la entrada del cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}