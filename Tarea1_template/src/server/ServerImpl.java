package server;

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
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerImpl implements InterfazDeServer {

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