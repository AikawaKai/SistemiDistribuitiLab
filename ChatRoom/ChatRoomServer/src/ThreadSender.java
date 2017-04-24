import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by kai on 24/04/17.
 */
public class ThreadSender extends Thread {
    private ArrayList<ThreadClientHandler> threads;
    private BufferString buffer;
    public ThreadSender(ArrayList<ThreadClientHandler> threads_, BufferString bufferString){
        threads = threads_;
        buffer = bufferString;
    }

    public void run(){
        String toSend = "";
        ThreadClientHandler client = null;
        while(true){
            toSend = buffer.readMessage();
            for(int i=0; i<threads.size(); i++)
            {
                client = threads.get(i);
                client.writeBytes(toSend);
            }
            System.out.print(toSend);
        }
    }
}
