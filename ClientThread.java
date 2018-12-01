
import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rishika Reddy Akavram
 */
class ClientThread implements Runnable {
    public static int ID;
    static Scanner sc;
    public static int serverToConnect;
    
 /* overriding run method to act as thread */   
  @Override
    public void run() {
      
       String hostName="localhost";
       int port=8062;
       String sourceDirectory=null;
       String targetDirectory=null;
       Registry registry = null;
       Server_Interface Si=null;
       String fileToSearch;
       int portNumber;
       List<Integer> found=new ArrayList<>();
       String options="Select an option\n"+"1.Search\n"+"2.exit";
       int optionsselect;
       
      /* connecting to napster server */
       try {
            registry = LocateRegistry.getRegistry(hostName,port);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Si=(Server_Interface)registry.lookup("IndexServer");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Successfully Registered to Napster server");
      
      /* getting host number to make client act as server */
       portNumber=Integer.parseInt(Thread.currentThread().getName());
       
       sc=new Scanner(System.in);
      
      /* peer will act according to the peer id */
       System.out.println("Enter PeerID:");
       ID=sc.nextInt();
        sourceDirectory=getDirectory(ID);
        try {
            Si.insertPortNumber(ID, portNumber);
            loadIntoRegisterMethod(Si,sourceDirectory);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       while(true){
     /* Giving peers the options to either search or exit */    
       System.out.println(options);
         optionsselect=sc.nextInt();
         switch(optionsselect){
           case 1:
      System.out.println("Enter the File name to search");
       fileToSearch=sc.next();
        try {
            found=Si.peerSearch(fileToSearch);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        /* Deleting duplicates from the arraylist */     
        Set<Integer> hs = new HashSet<>();
        hs.addAll(found);
        found.clear();
        found.addAll(hs);
        
       /* checking wheather return arraylist is empty or not */      
       boolean j=found.isEmpty();

       if(j) {
             System.out.println("File is not found"); 
             System.exit(-1);
           } 
        int k=0;
       for(int i=0;i<found.size();i++){
           if(k==0)
           System.out.println("File:"+fileToSearch+" is found on:");
           k++;
          if(found.get(i)==ID)
            continue;
           if(found.get(i)!=0){
            System.out.println(found.get(i));
           }
           
       }
       found=null;
       System.out.println("Enter the peer you want to connect:");
       int portnumber=sc.nextInt();
        try {
            serverToConnect=Si.returnPortNumber(portnumber);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        registry = null;
       Client_Interface Ci=null;
       
       /* making peer as a server using the portnumber */
             
       try {
            registry = LocateRegistry.getRegistry(hostName,serverToConnect);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Ci=(Client_Interface)registry.lookup("Server");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Successfully Connected\n"+serverToConnect);
       targetDirectory=getDirectory(portnumber);
        try {
            Ci.setDirectory(targetDirectory,sourceDirectory);
        } catch (RemoteException ex) {
           System.out.println("Peer not Connected");
           System.exit(-1);
        }
        
       boolean filetransfer = false;
       try {
             filetransfer=Ci.obtain(fileToSearch);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(filetransfer){
            System.out.println("File Transfer Sucessfull");   
            System.exit(0);
        }
             break;
           case 2: System.out.println("You have selected exit");
                    System.exit(0);
                    break;
           default:System.out.println("Invalid Choice");
                    break;
       }
       }
    }
    
    /* running the thread using run() */
    public static void main(String[] args){
       ClientThread tc;
       tc=new ClientThread();
       tc.run();  
    }
  
    /* listing files from shared directory and sending them
    to index server to index files */
    public static void loadIntoRegisterMethod(Server_Interface Si, String arg) throws RemoteException {
       File folder = new File(arg);
       File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                Si.insertToRegistry(ID, listOfFile.getName());
            }
        }
       
   }
    
   /* Directory information for each peer */
    private static String getDirectory(int ID) {
        String sourceDirectory;
        switch (ID) {
            case 1:
                sourceDirectory = "Peer_1/";
                break;
            case 2:
                sourceDirectory="Peer_2/";
                break;
            case 3:
                sourceDirectory="Peer_3/";
                break;
            default:
                sourceDirectory="";
        }
        return sourceDirectory;
    }

    
    
}
