/**
 * Created by kai on 24/04/17.
 */
import java.io.BufferedReader;
import java.net.*;

public class ThreadClientReader extends Thread{
    private Socket conn = null;
    private BufferedReader buffered = null;

    public ThreadClientReader(Socket connection, BufferedReader bufferedReader){
        conn = connection;
        buffered = bufferedReader;
    }

    public void run(){
        while(true){
            try{
                System.out.println(buffered.readLine());
            }catch(java.io.IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
