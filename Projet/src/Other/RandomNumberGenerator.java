package Other;
import java.util.Random;

public class RandomNumberGenerator {
    public static int generateRandomNumber(int n1, int n2) {
        Random rand = new Random();
        return rand.nextInt((n2 - n1) + 1) + n1;
    }

}
