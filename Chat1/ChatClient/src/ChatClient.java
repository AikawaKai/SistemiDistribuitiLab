import java.io.*;
import java.net.*;

public class ChatClient
{
    public static void main(String argv[]) throws Exception
    {
        String indirizzo_ip = null;
        String input = null;
        int porta = 0;
        String serverResponse = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci l'indirizzo ip del server: ");
        indirizzo_ip = bufferedReader.readLine();
        System.out.println("Inserisci la porta in ascolto del server ");
        input = bufferedReader.readLine();
        porta = Integer.parseInt(input);
        System.out.println("Indirizzo ip: "+indirizzo_ip + "| porta: "+porta);

        Socket clientSocket = new Socket(indirizzo_ip, porta);
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer =
                new BufferedReader(new
                        InputStreamReader(clientSocket.getInputStream()));
        while(!input.equals("stop"))
        {
            System.out.println("Messaggio: ");
            input = bufferedReader.readLine();
            outToServer.writeBytes(input + "\n");
            serverResponse = inFromServer.readLine();
            System.out.println(serverResponse);
        }
    }
}