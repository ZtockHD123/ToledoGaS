package common;

import java.io.Serializable;

public class Oferta implements Serializable {
	
	private String proveedor;
	private int idOferta;
	private int precio;
	private String tipo;
	private String comuna;
	private String region;
	
	public Oferta(String proveedor, int idOferta, int precio, String tipo, String comuna, String region) {
		this.setProveedor(proveedor);
		this.setIdOferta(idOferta);
		this.setPrecio(precio);
		this.setTipo(tipo);
		this.setComuna(comuna);
		this.setRegion(region);
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
	
    public String getComuna() {
    	return comuna;
    }
    public void setComuna(String comuna) {
    	this.comuna = comuna;
    }
    
    public String getRegion() {
    	return region;
    }
    public void setRegion(String region) {
    	this.region = region;
    }
    
    }
