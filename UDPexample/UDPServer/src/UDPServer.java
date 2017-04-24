import java.io.*;
import java.net.*;

public class UDPServer
{
    public static void main(String[] args) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(6666);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while(true)
        {
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);
        }
    }
}
