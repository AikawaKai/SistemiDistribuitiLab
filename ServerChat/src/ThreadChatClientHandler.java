/**
 * Created by kai on 24/04/17.
 */
import java.io.*;
import java.net.*;
public class ThreadChatClientHandler extends Thread {
    private Socket conn = null;
    private BufferedReader inFromClient = null;
    private DataOutputStream outToClient = null;
    private BufferString bufferString;
    private String response = "";

    public ThreadChatClientHandler(Socket connection, BufferString buffer){
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
        while(true){
            response = readLine();
            writeBytes("-\n");
            if(response.equals("stop\n"))
            {
                writeBytes("-\n");
                break;
            }

            bufferString.writeMessage(response);
        }
        System.out.println("Client Disconnected\n");

    }
}
