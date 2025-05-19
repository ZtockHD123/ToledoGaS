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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.InterfazDeServer;
import common.Oferta;

import java.nio.charset.StandardCharsets;

<<<<<<< HEAD
    ArrayList<Oferta> bd_ofertas_copia = new ArrayList<>();
    String url = "jdbc:mysql://localhost:3306/proyecto_paralela";
    String username = "root";
    String password = "";

    public ServerImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        conectarBD();
    }

    public void conectarBD() {
        Connection connection = null;
        Statement query = null;
        ResultSet resultados = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            query = connection.createStatement();
            String sql = "SELECT * FROM ofertas";
            resultados = query.executeQuery(sql);

            bd_ofertas_copia.clear();

            while (resultados.next()) {
                String proveedor = resultados.getString("proveedor");
                String tipo = resultados.getString("tipo");
                int precio = resultados.getInt("precio");
                int id_oferta = resultados.getInt("id_oferta");
                Oferta nuevaOferta = new Oferta(id_oferta, proveedor, precio, tipo);
                bd_ofertas_copia.add(nuevaOferta);
            }
            System.out.println("Conexión a BD completada y ofertas actualizadas.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar a la BD.");
        } finally {
            try {
                if (resultados != null) resultados.close();
                if (query != null) query.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Oferta> getOfertas() throws RemoteException {
    	System.out.println("Cliente solicitó la lista de ofertas. Actualizando desde la BD...");
        conectarBD();
    	System.out.println("Enviando " + bd_ofertas_copia.size() + " ofertas al cliente.");
        return bd_ofertas_copia;
    }
    
    @Override
    public void agregarOferta(Oferta oferta) throws RemoteException {
        String sql = "INSERT INTO ofertas (proveedor, tipo, precio) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, oferta.getProveedor());
            pstmt.setString(2, oferta.getTipo());
            pstmt.setInt(3, oferta.getPrecio());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Nueva oferta agregada a la BD.");
                conectarBD();
            } else {
                System.out.println("No se pudo agregar la oferta a la BD.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar la nueva oferta a la BD.");
            throw new RemoteException("Error en el servidor al guardar la oferta.", e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getDataFromApi() {
        String output = null;
        try {
            URL apiUrl = new URL("https://mindicador.cl/api");
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
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
                System.out.println("Error al conectar la API. Código de respuesta:" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}
=======
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
			System.out.println("Conexion exitosa a la bd");
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
    		
    		//URL apiUrl = new URL("https://api.cne.cl/api/v3/combustible/calefaccion/marcas");
    		URL apiUrl = new URL("https://api-gas-8gld.onrender.com/api/prices");
    		//String token = retrieveToken();
    		
    		HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();    		
    		connection.setRequestMethod("GET");
    	
    		//connection.setRequestProperty("Authorization", "Bearer " + token);
            //connection.setRequestProperty("Accept", "application/json");
    		
    		int responseCode = connection.getResponseCode();
    		
    		if (responseCode == HttpURLConnection.HTTP_OK) {
    			
    			//BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), java.nio.charset.StandardCharsets.UTF_8));
    			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
    			String inputLine;
    			StringBuilder response = new StringBuilder();
    			
    			while ((inputLine = in.readLine()) != null) {
    				response.append(inputLine);
    			}
    			
    			in.close();
    			output = response.toString();
    		
    		} else {
    			System.out.println("Error de conexion de API. Codigo de respuesta: " + responseCode);
    		}
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	System.out.println(output);
    	return output;
    }

    
    

    public Object[] getOfertaApi() throws RemoteException{
    	
    	String output = this.getDataFromApi();
    	
    	
    	
    	
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	try {
    		JsonNode jsonNode = objectMapper.readTree(output);
    		String id = jsonNode.get("items").get("id").asText();
    		//String fecha = jsonNode.get("items").get("fecha").asText();
    		double precio = jsonNode.get("items").get("precio").asDouble();
    		String proveedor = jsonNode.get("items").get("proveedor").asText();
    		String tipo_galon = jsonNode.get("items").get("tipo_galon").asText();
    		
    		return new Object[] { id, precio, proveedor, tipo_galon};
    		
    	}catch(JsonMappingException e){
    		e.printStackTrace();
    	}catch(JsonProcessingException e){
    		e.printStackTrace();
    	}

    	
    	return null;
    	
    }
    
}

    
>>>>>>> daa25f9c8e9faf47f249dfddf0c25f79e7dd7e18
