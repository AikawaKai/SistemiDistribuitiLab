/**
 * Created by kai on 24/04/17.
 */
import java.net.*;

public class ChatServerTCP {

    public static void main(String argv[]) throws Exception
    {
        //Spettacolo prova1 = new Spettacolo(100);
        ServerSocket welcomeSocket = new ServerSocket(6666);
        BufferString test = new BufferString();
        ThreadReader reader = new ThreadReader(test);
        reader.start();

        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            ThreadChatClientHandler handler = new ThreadChatClientHandler(connectionSocket, test);
            handler.start();
        }
    }
}