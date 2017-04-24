/**
 * Created by kai on 24/04/17.
 */
import java.io.*;
import java.net.*;
public class ThreadClientHandler extends Thread {
    private Socket conn = null;
    private BufferedReader inFromClient = null;
    private DataOutputStream outToClient = null;
    private BufferString bufferString;
    private String response = "";
    private String nickname = "";

    public ThreadClientHandler(Socket connection, BufferString buffer){
        conn = connection;
        bufferString = buffer;

        try {
            inFromClient = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            outToClient = new DataOutputStream(conn.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readLine()
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

    private void writeBytes(String message)
    {
        try
        {
            outToClient.writeBytes(message);
        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public void run()
    {
        response = readLine();
        String result[] = response.split("\n");
        nickname = result[0];
        while(true){
            response = readLine();
            writeBytes("-\n");
            if(response.equals("stop\n"))
            {
                writeBytes("-\n");
                break;
            }

            bufferString.writeMessage(nickname+" : "+response);
        }
        System.out.println("Client Disconnected\n");

    }
}
