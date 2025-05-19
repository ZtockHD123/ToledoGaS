package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InterfazDeServer extends Remote{
	
	ArrayList<Oferta> getOfertas() throws RemoteException; 
	
	String getDataFromApi() throws RemoteException;
	
	Object[] getOfertaApi() throws RemoteException;
}
