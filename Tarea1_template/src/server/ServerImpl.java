package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import common.InterfazDeServer;
import common.Oferta;

public class ServerImpl extends UnicastRemoteObject implements InterfazDeServer {
	
	public ServerImpl() throws RemoteException{
		conectarBD();
	}
	
	private ArrayList<Oferta> bd_Ofertas_copia = new ArrayList<>();
	
	public void conectarBD() {
		Connection connection = null;
		Statement query = null;
		//PreparedStatement test = null;
		ResultSet resultados = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/proyecto_paralela";
			String username = "root";
			String password = "";
			
			connection = DriverManager.getConnection(url, username, password);
			
			//TODO Metodos de la BD
			query = connection.createStatement();
			String sql = "SELECT * FROM ofertas";
			//INSERT
			resultados = query.executeQuery(sql);
			
			while(resultados.next()) {
				String proveedor = resultados.getString("Proveedor");
				String tipo = resultados.getString("Tipo");
				int precio = resultados.getInt("Precio");
				int idOferta = resultados.getInt("ID_oferta");
				
				Oferta newpersona = new Oferta(proveedor, idOferta, precio, tipo);
				
				bd_Ofertas_copia.add(newpersona);
			}
			connection.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("No se pudo conectar a la BD");
			
		}
	}
	
	
	@Override
    public ArrayList<Oferta> getOfertas() throws RemoteException {
        System.out.println("Cliente solicitó la lista de ofertas de galones.");
        return bd_Ofertas_copia;
    }

    
    @Override
    public String getDataFromApi(){
    	return null;
    }
}
