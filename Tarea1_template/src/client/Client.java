package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import common.InterfazDeServer;
import common.Oferta;


public class Client {
	
	private InterfazDeServer server;
	public Client() { }
	public boolean conectar() throws RemoteException, NotBoundException {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (InterfazDeServer) registry.lookup("server");
            return true;
    }
	
	public ArrayList<Oferta> getOfertas() throws RemoteException{
		ArrayList<Oferta> lista_Ofertas = server.getOfertas();
		
		for (int i = 0; i < lista_Ofertas.size(); i++) {
			Oferta oferta = lista_Ofertas.get(i);
			String proveedor, tipo;
			int  idOferta, precio;
			
			proveedor = oferta.getProveedor();
			idOferta = oferta.getIdOferta();
			precio = oferta.getPrecio();
			tipo = oferta.getTipo();
			
			System.out.println(idOferta + " " + proveedor + " " + tipo + " " + precio);
		}
		
		return null;
	}
	
	String getDataFromApi() throws RemoteException{
		return server.getDataFromApi();
	}
	 
}
