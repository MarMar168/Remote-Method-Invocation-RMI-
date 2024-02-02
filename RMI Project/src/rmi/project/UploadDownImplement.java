//implementation of the remote interface program server-side
package rmi.project;
import java.io.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UploadDownImplement extends UnicastRemoteObject
implements UploadDownloadInterface {

//constructor of the class
public UploadDownImplement() throws RemoteException{}

//server-side downloadFile method
@Override
public byte[] downloadFile(String fileName){

try {
    File file = new File("FileServer/"+fileName);
    byte buffer[] = new byte[(int)file.length()];
    // read information from stream/file
    BufferedInputStream input = new
    BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
    input.read(buffer,0,buffer.length);
    input.close();
    //log registration
    FileWriter fwrite = new FileWriter("FileServer/logs.txt",true);
    fwrite.write("\nClient Downloaded "+fileName+" at "+LocalDateTime.now());
    fwrite.close();
    return(buffer);  //return to the client!
} catch(Exception e){
    System.out.println("Exception Appear: ");
    e.printStackTrace();
    return(null);
}
}
//server-side uploadfile method!
@Override
public void uploadFile(String fileName, byte[] buffer){
 try {
    File file = new File("FileServer/"+fileName);
    // write information to the file
    BufferedOutputStream output = new
    BufferedOutputStream(new FileOutputStream(file.getAbsolutePath()));
    output.write(buffer,0,buffer.length);
    output.flush();
    output.close();
    //write log info
    FileWriter fwrite = new FileWriter("FileServer/logs.txt",true);
    fwrite.write("\nClient Uploaded "+fileName+" at "+LocalDateTime.now());
    fwrite.close();   
 } 
 catch(Exception e) {
    System.out.println("Exception Appear: ");
    e.printStackTrace();

 }
}

//server-side viewLog method
@Override
public String viewLogs()
{   
    File inputFile = new File("FileServer/logs.txt");
    String log="";
    try {
        Scanner reader = new Scanner(inputFile);
        //read each line of text from log file
        while (reader.hasNextLine()) {
            log = log+reader.nextLine()+"\n";
        }
        reader.close();
    } catch (FileNotFoundException e) {
        System.out.println("File not found error");
        e.printStackTrace();
    }
    return log;
}

//server-side display list of files method!
@Override
public String[] listOfFiles()
{   
    File dir = new File("FileServer/");
    //search every file found on the FileServer folder
    String[] str = dir.list();
     //log registration
   try
   {
    FileWriter fwrite = new FileWriter("FileServer/logs.txt",true);
    fwrite.write("\nClient view List of Files at "+LocalDateTime.now());
    fwrite.close();
   }
   catch(Exception ex){
    ex.printStackTrace();
   }
    return str;
}

//server-side fileInfo(size, type) display method!
@Override
public String fileInfo(String fileName)
{   String info="";
    File file = new File("FileServer/"+fileName);
    if(file.exists()||file.isFile())
    {
        String size="Size: "+file.length()+" Bytes\n";
        String extension = "";
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = "Type: "+fileName.substring(index + 1);
        }
         info=size+extension;
    }
    //log registration
   try
   {
    FileWriter fwrite = new FileWriter("FileServer/logs.txt",true);
    fwrite.write("\nClient view file information at "+LocalDateTime.now());
    fwrite.close();
   }
   catch(Exception ex){
    ex.printStackTrace();
   }
        
   return info;
}

}
