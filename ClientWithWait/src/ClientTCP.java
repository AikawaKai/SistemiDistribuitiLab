import java.io.*;
import java.net.*;
public class ClientTCP
{
    public static void main(String argv[]) throws Exception
    {
        String indirizzo_ip = null;
        String input = null;
        String op = null;
        int porta = 0;
        int number1 = 0;
        int number2 = 0;
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
        serverResponse = "ko";
        while(serverResponse.equals("ko"))
        {
            System.out.println("Inserisci il primo numero: ");
            input = bufferedReader.readLine();
            outToServer.writeBytes(input + '\n');
            serverResponse = inFromServer.readLine();
            System.out.println(serverResponse);
        }
        serverResponse = "ko";
        while(serverResponse.equals("ko"))
        {
            System.out.println("Inserisci il secondo numero: ");
            input = bufferedReader.readLine();
            outToServer.writeBytes(input + '\n');
            serverResponse = inFromServer.readLine();
            System.out.println(serverResponse);
        }
        serverResponse = "ko";
        while(serverResponse.equals("ko"))
        {
            System.out.println("Inserisci l'operazione da eseguire: ");
            op = bufferedReader.readLine();
            outToServer.writeBytes(op + "\n");
            serverResponse = inFromServer.readLine();
            System.out.println(serverResponse);
        }
        serverResponse = inFromServer.readLine();
        System.out.println("Risposta: "+serverResponse);
    }
}
