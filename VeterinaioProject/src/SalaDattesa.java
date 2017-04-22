import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kai on 21/04/17.
 */

public class SalaDattesa {
    int count_g = 0;
    int count_c = 0;

    public synchronized void checkConditionAndComeIn(String animale) {
        try{
                if (animale.equals("g")) {
                    while(!checkGattoConditions())
                    {
                        wait();
                    }
                    count_g++;
                    printSalaDattesa();
                } else {
                    while(!checkCaneConditions()) {
                        wait();
                    }
                    count_c++;
                    printSalaDattesa();
                }
        }catch(java.lang.InterruptedException e)
        {
            System.out.println("Interrotto");
        }
    }

    private boolean checkGattoConditions() {
        if(count_c >= 1 || count_g ==1)
        {
            return false;
        }
        return true;
    }

    private boolean checkCaneConditions() {

        if(count_g>=1)
        {
            return false;
        }
        if(count_c==4)
        {
            return false;
        }
        return true;
    }

    public synchronized void goOut(String animal){
        if(animal.equals("g"))
        {
            count_g--;
        }else{
            count_c--;
        }
        printSalaDattesa();
        notifyAll();
    }

    public void printSalaDattesa()
    {
        System.out.println("Ci sono "+count_c+" cani e "+count_g+" gatti");
    }

}


