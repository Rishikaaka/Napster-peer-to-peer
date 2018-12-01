// Programming project 2- Rishika Reddy Akavaram


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CentralServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Registry registry = null;
        
       /*  Registery Creation for Central Server  */ 
            try {
                int port = 8062;
                registry = LocateRegistry.createRegistry(port);
                System.out.println("Indexing server has been created successfully"); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        
      /* Creation of Stubs for Napster Server */
        try {
             Server_Interface si=(Server_Interface)new Server();
            registry.rebind("IndexServer", si); 
            System.out.println("Napster Server has started! Register the peers");
        } catch (Exception e) {
            e.printStackTrace();
        }
      
     /* Thread is made to sleep for some time untill all the peers are connected to it */
        try {
           Thread.sleep(3000000);
       } catch (InterruptedException e) {
           
           e.printStackTrace();
       }
    }
    
}
