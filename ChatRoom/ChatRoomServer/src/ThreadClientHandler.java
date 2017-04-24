/**
 * Created by kai on 24/04/17.
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class ThreadClientHandler extends Thread {
    private Socket conn = null;
    private BufferedReader inFromClient = null;
    private DataOutputStream outToClient = null;
    private BufferString bufferString;
    private String response = "";
    private String nickname = "";
    public int tId;
    private ArrayList<ThreadClientHandler> threads = null;

    public ThreadClientHandler(int id, Socket connection, BufferString buffer){
        conn = connection;
        bufferString = buffer;
        tId = id;

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

    public void run()
    {
        response = readLine();
        String result[] = response.split("\n");
        nickname = result[0];
        while(true){
            response = readLine();
            if(response.equals("stop\n"))
            {
                break;
            }
            bufferString.writeMessage(nickname+" : "+response);
        }
        System.out.println("Client Disconnected\n");
        synchronized (threads){
            for(int i=0; i<threads.size(); i++){
                if(threads.get(i).tId == tId)
                {
                    threads.remove(i);
                    break;
                }
            }
        }

    }
}
