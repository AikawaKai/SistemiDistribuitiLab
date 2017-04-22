/**
 * Created by kai on 21/04/17.
 */
import java.util.Random;
public class VeterinarioMain {

    public static void main(String argv[]) throws Exception
    {
        Random generator = new Random();
        SalaDattesa sala = new SalaDattesa();
        String currentAnimal;
        String animals[] = {"c", "c", "g"};
        ThreadAnimal threadanimal[] = new ThreadAnimal[20];
        for(int i=0; i<20; i++)
        {
            currentAnimal = animals[generator.nextInt( 3 )];
            threadanimal[i] = new ThreadAnimal(currentAnimal, sala);
            threadanimal[i].start();
        }
    }
}
