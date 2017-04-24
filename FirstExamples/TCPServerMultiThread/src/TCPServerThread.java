import java.io.*;
import java.net.*;

public class TCPServerThread extends Thread {

    private Socket connectionSocket = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private String operation = null;
    private int number1 = 0;
    private int number2 = 0;

    public TCPServerThread(Socket s) {
        connectionSocket = s;
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

    public String checkOperation()
    {
        String op = null;
        boolean check = true;
        while(check)
        {
            String[] parts = readLine().split("\n");
            op = parts[0];
            check = false;
            writeBytes("ok\n");
        }
        return op;
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
        int number2 = 0;
        String op = null;
        number1 = checkIntValue();
        number2 = checkIntValue();
        op = checkOperation();
        writeBytes(number1 + op + number2 + "\n");
    }
}
