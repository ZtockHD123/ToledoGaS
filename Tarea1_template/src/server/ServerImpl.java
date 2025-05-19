package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
	
	
	private String retrieveToken() throws Exception {

	    String commands = "(Invoke-RestMethod -Uri 'https://api.cne.cl/api/login?email=psreinoso5@gmail.com&password=62255907pA' -Method POST).token";
	    ProcessBuilder pb = new ProcessBuilder(
	        "powershell.exe", "-Command", commands
	    );
	    pb.redirectErrorStream(true);
	    Process p = pb.start();

	    try (BufferedReader reader = new BufferedReader(
	            new InputStreamReader(p.getInputStream()))) {
	        StringBuilder output = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            output.append(line);
	        }
	        p.waitFor();
	        return output.toString().trim();
	    }
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
    	
    	String output = null;
    	
    	try {
    		
    		URL apiUrl = new URL("https://api.cne.cl/api/v3/combustible/calefaccion/puntosdeventa");
    		//URL apiUrl = new URL("https://mindicador.cl/api");
    		String token = retrieveToken();
    		
    		HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
    		
    		connection.setRequestMethod("GET");
    		connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");
    		
    		int responseCode = connection.getResponseCode();
    		
    		if (responseCode == HttpURLConnection.HTTP_OK) {
    			
    			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    			String inputLine;
    			StringBuilder response = new StringBuilder();
    			
    			while ((inputLine = in.readLine()) != null) {
    				response.append(inputLine);
    			}
    			
    			in.close();
    			output = response.toString();
    		
    		} else {
    			System.out.println("Error de conexion de API.");
    		}
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	return output;
    }
}
