import java.io.*;
import java.net.*;

public class TCPMultiServer {

    public static void main(String argv[]) throws Exception
    {
        Spettacolo prova1 = new Spettacolo(100);
        ServerSocket welcomeSocket = new ServerSocket(6666);
        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            TCPServerThread thread = new TCPServerThread(connectionSocket, prova1);
            System.out.println("Biglietti rimasti: "+ prova1.getNumTicket());
            thread.start();
        }
    }
}