import java.io.*;
import java.net.*;
public class ClientTCP
{
    public static void main(String argv[]) throws Exception
    {
        String indirizzo_ip = null;
        String input = null;
        int porta = 0;
        int number1 = 0;
        int number2 = 0;
        String serverResponse = null;
        try {
            System.out.println("Inserisci l'indirizzo ip del server: ");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            indirizzo_ip = bufferedReader.readLine();
            System.out.println("Inserisci la porta in ascolto del server ");
            input = bufferedReader.readLine();
            porta = Integer.parseInt(input);
            System.out.println("Inserisci il primo numero: ");
            input = bufferedReader.readLine();
            number1 = Integer.parseInt(input);
            System.out.println("Inserisci il secondo numero: ");
            input = bufferedReader.readLine();
            number2 = Integer.parseInt(input);
            System.out.println("Indirizzo ip: "+indirizzo_ip + "| porta: "+porta);
            System.out.println("Somma: "+number1+"+"+number2);
        } catch (NumberFormatException ex) {
            System.out.println("Not a number !");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket clientSocket = new Socket(indirizzo_ip, porta);
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer =
                new BufferedReader(new
                        InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(Integer.toString(number1) + '\n');
        outToServer.writeBytes(Integer.toString(number2) + '\n');
        serverResponse = inFromServer.readLine();
        System.out.println("Risposta: "+serverResponse);
    }
}
