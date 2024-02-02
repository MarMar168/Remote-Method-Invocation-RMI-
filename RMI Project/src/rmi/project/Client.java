//a client program which sends any request to the server!
package rmi.project;
import java.rmi.*;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client {
    static String host="localhost";  //address of the server
    static String conStr="//"+host+"/Updown"; 
    static Scanner sc=null;   //to read info from user!
    public static void main(String argv[])
    {   
        int choice=printUsage();
        while(choice!=0)
        {   
            if(choice == 1) {
                //call downloadfile method
                downloadFile();
                choice=printUsage();
            }
            else if (choice == 2){ 
                //call uploadFile method
                uploadFile();
                choice=printUsage();

            }
            else if (choice == 3){
                //call viewLogFile method
                viewLogFile();
                choice=printUsage();
            }
            else if (choice == 4){
                //call displayFileList method
                displayFileLists();
                choice=printUsage();
            }
            else if (choice == 5){
                //call viewFileInfo method
                viewFileInfo();
                choice=printUsage();
            }
            else {
                System.out.println("Menu not Found! Select other: ");
                choice=printUsage();
            }          
     }
    }
  //a method which displays menu!
  public static int printUsage()
  {
        System.out.println("===================================");  
        System.out.println("Usage: Guide to access the System ");
        System.out.println("1 for download!");
        System.out.println("2 for upload!");
        System.out.println("3 to view log file!");
        System.out.println("4 to view list of files!");
        System.out.println("5 to view file info!");
        System.out.println("0 to exit the system!");
        Scanner sc=new Scanner(System.in);
        System.out.print("What do you want: ");
        int choice=sc.nextInt();
        return choice;
  }
  
  //a method which downloads file from server
  public static void downloadFile()
  {       
    try
    {
        sc=new Scanner(System.in);
        System.out.print("file name: ");
        String fileName=sc.next();
        //client connect with the server address and port no
        Registry registry = LocateRegistry.getRegistry("localhost",4545);
        //create remote object from UploadDownloadInterface interface
        UploadDownloadInterface fi = (UploadDownloadInterface) registry.lookup("Hi Server");
        byte[] filedata = fi.downloadFile(fileName);
        File file = new File("ClientDownload/"+fileName);
        BufferedOutputStream output = new
        BufferedOutputStream(new FileOutputStream(file.getAbsolutePath()));
        output.write(filedata,0,filedata.length);
        output.flush();
        output.close();
        System.out.println("File downloaded succesfully!");
    }
    catch(RemoteException re) {
        System.err.println("Remote Exception: ");
        re.printStackTrace();
    }
    catch(NotBoundException nbe) {
        System.err.println("Not Bound Exception: ");
        nbe.printStackTrace();
    }
    catch(MalformedURLException mue) {
        System.err.println("Malformed Exception: ");
        mue.printStackTrace();
    } 
    catch(IOException re) {
        System.err.println("IO Exception: ");
        re.printStackTrace();
    }
  }
  //a method which uploads file to the server from the client!
  public static void uploadFile()
  { 
    try
    {
        sc=new Scanner(System.in);
        System.out.print("file name: ");
        String fileName=sc.next();
        Registry registry = LocateRegistry.getRegistry("localhost",4545);
        UploadDownloadInterface fi = (UploadDownloadInterface) registry.lookup("Hi Server");
        File file = new File("upload/"+fileName);
        System.out.println(file.getAbsolutePath());
        byte buffer[] = new byte[(int)file.length()];
        BufferedInputStream input;
        input = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
        input.read(buffer,0,buffer.length);
        input.close();
        fi.uploadFile(fileName,buffer);
        System.out.println("File Uploaded succesfully!");
    }
    catch(RemoteException re) {
        System.err.println("Remote Exception: ");
        re.printStackTrace();
    }
    catch(NotBoundException nbe) {
        System.err.println("Not Bound Exception: ");
        nbe.printStackTrace();
    }
    catch(MalformedURLException mue) {
        System.err.println("Malformed Exception: ");
        mue.printStackTrace();
    } 
    catch(IOException re) {
        System.err.println("File Not Found Exception: ");
        re.printStackTrace();
    }
  }
  
  //a method which used to view log files written by the server
  public static void viewLogFile()
  {  
    try
    {
        Registry registry = LocateRegistry.getRegistry("localhost",4545);
        UploadDownloadInterface fi = (UploadDownloadInterface) registry.lookup("Hi Server");
        String filedata = fi.viewLogs();
        System.out.println("Logs registered by Server!");
        System.out.print("---------------------------");
        System.out.print(filedata);
    }
    catch(RemoteException re) {
        System.err.println("Remote Exception: ");
        re.printStackTrace();
    }
    catch(NotBoundException nbe) {
        System.err.println("Not Bound Exception: ");
        nbe.printStackTrace();
    }
   
  }
  
  //a method that displays lists of file found in server!
  public static void displayFileLists()
  { 
    try
    {
        Registry registry = LocateRegistry.getRegistry("localhost",4545);
        UploadDownloadInterface fi = (UploadDownloadInterface) registry.lookup("Hi Server");
        String fileLists[] = fi.listOfFiles();
        System.out.println("Lists of files on the Server are: ");
        for(String file:fileLists)
        System.out.println(file);
    }
    catch(RemoteException re) {
        System.err.println("Remote Exception: ");
        re.printStackTrace();
    }
    catch(NotBoundException nbe) {
        System.err.println("Note Bound Exception: ");
        nbe.printStackTrace();
    }
    
  }  
  //a method which helps to view file info of a specific file!
  public static void viewFileInfo()
  {   
    try
    {
        sc=new Scanner(System.in);
        System.out.print("file name: ");
        String fileName=sc.next();
        Registry registry = LocateRegistry.getRegistry("localhost",4545);
        UploadDownloadInterface fi = (UploadDownloadInterface) registry.lookup("Hi Server");
        String fileInfo = fi.fileInfo(fileName);
        System.out.println(fileInfo);
    }
    catch(RemoteException re) {
        System.err.println("Remote Exception: ");
        re.printStackTrace();
    }
    catch(NotBoundException nbe) {
        System.err.println("Not Bound Exception: ");
        nbe.printStackTrace();
    }
  }
}
