package common;

import java.io.Serializable;

public class Oferta implements Serializable {
	
	private String proveedor;
	private int idOferta;
	private int precio;
	private String tipo;
	
	public Oferta(String proveedor, int idOferta, int precio, String tipo) {
		this.setProveedor(proveedor);
		this.setIdOferta(idOferta);
		this.setPrecio(precio);
		this.setTipo(tipo);
	}
	
	  // Getter y setter para empresa
    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    // Getter y setter para peso
    public int getIdOferta() {
        return idOferta;
    }
    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }
    
    // Getter y setter para precio
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    // Getter y setter para telefono
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
	
}
