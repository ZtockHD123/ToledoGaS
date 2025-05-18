package server;

import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import common.InterfazDeServer;
import common.Oferta;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerImpl implements InterfazDeServer {

	public ServerImpl() throws RemoteException {
		conectarBD();
		UnicastRemoteObject.exportObject(this, 0);
	}
	
	private ArrayList<Oferta> bd_ofertas_copia = new ArrayList<>();
	
	public void conectarBD() {
		Connection connection = null;
		Statement query = null;
		//PreparedStatement test = null;
		ResultSet resultados = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/proyecto_paralela";
			String username = "root";
			String password_bd = "";
			connection = DriverManager.getConnection(url, username, password_bd);
			
			//Hacer metodos con la BD
			
			query = connection.createStatement();
			String sql = "SELECT * FROM ofertas";
			
			//INSERT
			//DELETE
			//UPDATE
			
			resultados = query.executeQuery(sql);
			
			while(resultados.next()) {
				String proveedor = resultados.getString("proveedor");
				String tipo = resultados.getString("tipo");
				int precio = resultados.getInt("precio");
				int idOferta = resultados.getInt("idOferta");
				String comuna = resultados.getString("comuna");
				String region = resultados.getString("region");
				
				Oferta newpersona = new Oferta(proveedor, idOferta, precio, tipo, comuna, region);
				
				bd_ofertas_copia.add(newpersona);
			}
			
			
			System.out.println("Conexion exitosa a la BD");
			connection.close();
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("No se pudo conectar a la BD");
		}
		
	}
	
	@Override
	public ArrayList<Oferta> getOfertas() throws RemoteException{
		return bd_ofertas_copia;
	}
	
	@Override
	public String getDataFromApi(){
		
		String output = null;
		
		try {
			//URL apiUrl = new URL("https://api.cne.cl/api/v3/combustible/calefaccion/callcenters");
			URL apiUrl = new URL("https://mindicador.cl/api");
			HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
			
			connection.setRequestMethod("GET");
			
			int responseCode = connection.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				
				while((inputLine = in.readLine()) != null) {
					response.append(inputLine);	
				}
				
				in.close();
				output = response.toString();
			} else {
				System.out.println("Error al detectat la API. Codigo de respuesta:" + responseCode);
			}
			
		} catch (Exception e ){
			e.printStackTrace();
		}
		 return output;
	}
	
	
}
	
	
