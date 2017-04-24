import java.io.*;
import java.net.*;
class TCPServer
{
    public static void main(String argv[]) throws Exception
    {
        String result;
        String capitalizedSentence;
        /* Crea una "listening socket" sulla porta specificata */
        ServerSocket welcomeSocket = new ServerSocket(6665);
        int number1 = 0;
        int number2 = 0;
        while(true)
        {
            /*
            * Viene chiamata accept (bloccante).
            * All’arrivo di una nuova connessione crea una nuova
            * "established socket"
            */
            System.out.println("Ciao sono in ascolto\n");
            Socket connectionSocket = welcomeSocket.accept();
            /* Inizializza lo stream di input dalla socket */
            System.out.println("Il client: "+connectionSocket.getInetAddress().getHostAddress().toString());
            System.out.println("Porta: "+connectionSocket.getPort());
            BufferedReader inFromClient = new BufferedReader(new
                                InputStreamReader(connectionSocket.getInputStream()));
            /* Inizializza lo stream di output verso la socket */
            DataOutputStream outToClient =
                            new DataOutputStream(connectionSocket.getOutputStream());
            /* Legge una linea (terminata da \n) dal client */
            number1 = Integer.parseInt(inFromClient.readLine());
            number2 = Integer.parseInt(inFromClient.readLine());
            result = Integer.toString(number1 + number2) + '\n';
            /* Invia la risposta al client */
            outToClient.writeBytes(result);
        }
    }
}