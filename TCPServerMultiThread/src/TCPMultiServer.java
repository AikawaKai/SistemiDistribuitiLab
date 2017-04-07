import java.io.*;
import java.net.*;

public class TCPMultiServer {

    public static void main(String argv[]) throws Exception
    {
        ServerSocket welcomeSocket = new ServerSocket(6666);
        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            TCPServerThread thread = new TCPServerThread(connectionSocket);
            thread.start();
        }
    }
}
