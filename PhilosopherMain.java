import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhilosopherMain {

    private static final int minLimitPhilosopher=2;

    public static void main(String arg[]) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	
        try {
            Printer.show("Enter no. of philosopher");
            int philosopherCount = Integer.parseInt(bufferedReader.readLine());
            Thread thread;
            if (philosopherCount < minLimitPhilosopher) {
                Printer.show("Choose more than 1 Philosopher");
                return;
            }
            Fork[] fork = new Fork[philosopherCount];
            Philosopher[] philosophers = new Philosopher[philosopherCount];
            for (int count = 0; count < philosopherCount; count++) {
                fork[count] = new Fork(count, true);
            }

            for (int count = 0; count < philosopherCount; count++) {
                thread = new Thread(new Philosopher(fork[count], fork[(count + 1) % philosopherCount]), "Philosopher " + count);
                thread.start();
            }
        } catch (IOException ioException) {
            Printer.show("Exception: " + ioException);
        }
    }
}
