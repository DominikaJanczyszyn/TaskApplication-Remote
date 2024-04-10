package com.example.workshops2session2.server;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerStart
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException
    {
        Registry registry = LocateRegistry.createRegistry(8080);
        RemoteConnector remoteConnector = new RemoteConnector();
        Remote remote = UnicastRemoteObject.exportObject(remoteConnector, 0);
        registry.bind("rmiServer", remote);
        System.out.println("Server running");
    }
}
