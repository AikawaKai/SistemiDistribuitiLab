/**
 * Created by kai on 24/04/17.
 */
/**
 * Created by kai on 24/04/17.
 */
import java.net.*;
import java.util.*;

public class ChatRoomServer {

    public static void main(String argv[]) throws Exception
    {
        int threadId = 0;
        ServerSocket welcomeSocket = new ServerSocket(6666);
        BufferString buffer = new BufferString();
        ArrayList<ThreadClientHandler> threads= new ArrayList<ThreadClientHandler>();
        ThreadSender sender = new ThreadSender(threads, buffer);
        sender.start();

        while(true)
        {

            Socket connectionSocket = welcomeSocket.accept();
            ThreadClientHandler handler = new ThreadClientHandler(threadId, connectionSocket, buffer);
            handler.start();
            threads.add(handler);
            threadId++;
        }
    }
}