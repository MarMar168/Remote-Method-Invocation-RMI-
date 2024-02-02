// rmi server program which accepts request from client 
package rmi.project;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Server {
    public static void main(String argv[])
    {
      try{
        //create a local object from UploadDownImplement class 
        UploadDownImplement locObj=new UploadDownImplement();
        //server ready to connect with any client with port 1099
        Registry registry = LocateRegistry.createRegistry(4545);
        registry.rebind("Hi Server", locObj);
        System.out.println("Server is ready!!");
      }
      catch(RemoteException re)
      {
        re.printStackTrace();
      }
     
    }
}
