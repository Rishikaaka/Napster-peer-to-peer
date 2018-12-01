


/**
 *
 * @author Rishika Reddy Akavaram
 */
import java.lang.Exception;
        
public class MainThread {
    public static void main(String[] args){
       
        /* host number to make client acting as a server */
      
        String HostNumber;
        HostNumber = args[0];
      
        /* Creating two instance variable to create threads */
        ClientThread tc=new ClientThread();
        ServerThread ts=new ServerThread();
        
        /* Creating a Client-Server architecture using Threads */
        Thread t1=new Thread(tc,HostNumber);
        Thread t2=new Thread(ts,HostNumber);
        
        
        t1.start();  /* starting server thread */
      
        t2.start(); /* starting client thread */
       
       
             
    }
}