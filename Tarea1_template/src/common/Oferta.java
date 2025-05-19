package common;

import java.io.Serializable;

public class Oferta implements Serializable {
	
	private String proveedor;
	private int id_oferta;
	private int precio;
	private String tipo;
	
	public Oferta( int id_oferta, String proveedor, int precio, String tipo) {
		this.setProveedor(proveedor);
		this.setIdOferta(id_oferta);
		this.setPrecio(precio);
		this.setTipo(tipo);
	}
	
    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    public int getIdOferta() {
        return id_oferta;
    }
    public void setIdOferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }
    
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
	
    
    }
