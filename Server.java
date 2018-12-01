
import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;





public class Server extends UnicastRemoteObject implements Server_Interface {
    
    ArrayList<Integer> PeerID=new ArrayList<>();
    ArrayList<String> Filename=new ArrayList<>();
    ArrayList<Integer> MatchingFilename=new ArrayList<>();
    HashMap<Integer,Integer> portNumber=new HashMap<>();
    
    Server() throws RemoteException{
    super();
    }
    /* inserting peerid and filename of each peer connected  into indexserver */
  
    @Override
    public synchronized void insertToRegistry(int peerID, String filename) throws RemoteException {
        PeerID.add(peerID);
        Filename.add(filename);
    }

    /* Search filename on the indexing server and returns the ArrayList object */
  
    @Override
    public  ArrayList<Integer> peerSearch(String filename) throws RemoteException {
        int j=0;
        for(int i=0;i<PeerID.size();i++){
        if(Filename.get(i).equals(filename)){
            MatchingFilename.add(j, PeerID.get(i));
            j++;
        }
        
    }
    return MatchingFilename;    
    } 

    /* set port number and peerID as index */
    @Override
    public synchronized void insertPortNumber(int peerID,int port) throws RemoteException {
            portNumber.put(peerID, port);
    }

   /* get portNumber using peerID */
  @Override
    public synchronized int returnPortNumber(int peerID) throws RemoteException {
            if(portNumber.containsKey(peerID)){
                return portNumber.get(peerID);
            }
            return 0;
    }
}
