
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author Rishika 
 */


/* Creating Server for each Client using port number */
class ServerThread implements Runnable {
    
    
    @Override
    public void run() {
        Registry registry = null;
     
      
        
            try {
                int portNumber=Integer.parseInt(Thread.currentThread().getName());
                registry = LocateRegistry.createRegistry(portNumber);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        try {
             Client_Interface ci=(Client_Interface)new Client();
             registry.rebind("Server", ci); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
           Thread.sleep(50000);
       } catch (InterruptedException e) {
           
           e.printStackTrace();
       }
    }
    
    public static void main(String[] args){
        ServerThread ts=new ServerThread();
        ts.run();
    }
    
}
