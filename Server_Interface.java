
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Rishika 
 */


/* Remote Interface for Server */

public interface Server_Interface extends Remote{
    void insertToRegistry(int peerID, String filename) throws RemoteException;
    ArrayList<Integer> peerSearch (String filename) throws RemoteException;
    void insertPortNumber(int peerID,int port)throws RemoteException;
    int returnPortNumber(int peerID) throws RemoteException;
}
