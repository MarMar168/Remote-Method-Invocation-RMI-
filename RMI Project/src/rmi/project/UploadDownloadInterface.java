//a remote interface used as a gatway for a client
package rmi.project;
import java.rmi.*;
public interface UploadDownloadInterface extends Remote{
    //download file method with no implementation(abstract method)
    public byte[] downloadFile(String fileName) throws RemoteException;
    
    //upload file method with no implementation(abstract method)
    public void uploadFile(String fileName, byte[] buffer) throws RemoteException;
    
    //view log file method with no implementation(abstract method)
    public String viewLogs() throws RemoteException;
    
    //view list of file method with no implementation(abstract method)
    public String[] listOfFiles() throws RemoteException;
    
    //view file ingo method with no implementation(abstract method)
    public String fileInfo(String fileName) throws RemoteException;

    /*Note : The definition of all the above method is found in 
      UploadDownImplement.java source file!
    */
}
