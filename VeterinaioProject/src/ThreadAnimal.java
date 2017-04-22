/**
 * Created by kai on 21/04/17.
 */
import java.util.Random;
public class ThreadAnimal extends Thread {
    public String race = "";
    public SalaDattesa sala;
    public ThreadAnimal(String r, SalaDattesa s){
        sala = s;
        race = r;
    }

    public void run() {
        sala.checkConditionAndComeIn(race);
        Random ran = new Random();
        int value = ran.nextInt(5000)+1000; // wait between 1 to 6 seconds
        try{
            Thread.sleep(value);
        }catch(java.lang.InterruptedException e){
            e.printStackTrace();
        }
        sala.goOut(race);


    }
}
