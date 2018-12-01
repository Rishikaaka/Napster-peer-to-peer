
/**
 *
 * @author Rishika 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client extends UnicastRemoteObject implements Client_Interface,Serializable {

    static String sourceDirectory=null;
    static String targetDirectory=null;

    Client() throws RemoteException{
        super();
    }
	
	
    /* Setting the source and destination directory */
     public synchronized void setDirectory(String sourceDirectory, String targetDirectory) throws RemoteException {
           Client.sourceDirectory=sourceDirectory;
            Client.targetDirectory=targetDirectory;
    }
	
	/* Downloading file to the destination client which requested the file */
	
   // @Override
    public synchronized boolean obtain(String filename) throws RemoteException {
    InputStream input = null;
	OutputStream output = null;
	try {
           
		input = new FileInputStream(Client.sourceDirectory+filename);
		output = new FileOutputStream(Client.targetDirectory+filename);
		byte[] buf = new byte[1024];
		int bytesRead;
		while ((bytesRead = input.read(buf)) > 0) {
			output.write(buf, 0, bytesRead);
		}
	} catch (FileNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return true;
    }
    
}
