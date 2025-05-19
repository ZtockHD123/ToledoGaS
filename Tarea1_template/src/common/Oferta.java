package common;

import java.io.Serializable;

public class Oferta implements Serializable {
	
	private String proveedor;
	private int id_oferta;
	private int precio;
	private String tipo;
	
<<<<<<< HEAD
	public Oferta( int id_oferta, String proveedor, int precio, String tipo) {
=======
	public Oferta(String proveedor, int idOferta, int precio, String tipo) {
>>>>>>> daa25f9c8e9faf47f249dfddf0c25f79e7dd7e18
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
	
<<<<<<< HEAD
    
    }
=======
}
>>>>>>> daa25f9c8e9faf47f249dfddf0c25f79e7dd7e18
