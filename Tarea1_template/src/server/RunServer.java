package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.InterfazDeServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer {
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		InterfazDeServer server = new ServerImpl();
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind("server", server);
		System.out.println("Servidor Arriba!");
	}
}
