/**
 * Created by kai on 24/04/17.
 */
public class ThreadReader extends Thread {
    private BufferString buffer;
    public ThreadReader(BufferString bufferString){
        buffer = bufferString;
    }

    public void run(){
        while(true){
            System.out.println(buffer.readMessage());
        }
    }
}
