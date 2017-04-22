import java.io.*;
import java.net.*;

public class TCPServerThread extends Thread {

    private Socket connectionSocket = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private Spettacolo spett;

    public TCPServerThread(Socket s, Spettacolo sp) {
        connectionSocket = s;
        spett = sp;
        try {
            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBytes(String message)
    {
        try
        {
            outToClient.writeBytes(message);
        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public String readLine()
    {
        String response = null;
        try
        {
            response = inFromClient.readLine()+"\n";
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        return response;

    }

    private int checkIntValue()
    {
        int number = 0;
        boolean check = true;

        while(check)
        {
            try {
                String[] parts = readLine().split("\n");
                number = Integer.parseInt(parts[0]);
                check = false;
                writeBytes("ok\n");
            } catch (NumberFormatException e) {
                writeBytes("ko\n");
            }
        }
        return number;
    }

    public void run()
    {

        int number1 = 0;
        Boolean prenotati = false;
        number1 = checkIntValue();
        prenotati = spett.prenota(number1);
        if(prenotati) {
            writeBytes("Prenotati\n");
        }else
        {
            writeBytes("Non prenotati. Posti finiti\n");
        }
    }
}