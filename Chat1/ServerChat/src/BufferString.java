/**
 * Created by kai on 24/04/17.
 */
import java.util.*;
public class BufferString {

    private ArrayList<String> buffer = new ArrayList<String>();

    public synchronized void writeMessage(String msg){
        buffer.add(msg);
        notify();
    }

    public synchronized String readMessage(){
        while(buffer.size()==0){
            try{
                wait();
            }catch(java.lang.InterruptedException e){
                e.printStackTrace();
            }
        }
        String toReturn = buffer.get(0);
        buffer.remove(0);
        return toReturn;
    }

}
