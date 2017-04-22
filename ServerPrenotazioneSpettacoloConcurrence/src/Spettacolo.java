/**
 * Created by kai on 21/04/17.
 */
public class Spettacolo {

    private int num_biglietti = 0;
    private Boolean avaiable = false;

    public Spettacolo(int num){
        if(num>0)
        {
            num_biglietti = num;
            avaiable = true;
        }
    }

    public int getNumTicket()
    {
        return num_biglietti;
    }

    public synchronized Boolean prenota(int n){
        if(num_biglietti>=n)
        {
            try {
                Thread.sleep(3000);
            }catch(java.lang.InterruptedException e)
            {
                System.out.println("Interrotto thread");
            }
            num_biglietti = num_biglietti - n;
            return true;
        }
        return false;
    }
}
