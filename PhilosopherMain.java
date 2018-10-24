import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhilosopherMain {
    public static void main(String arg[]) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Printer.show("Enter no. of philosopher");
            int philosopherCount = Integer.parseInt(bufferedReader.readLine());
            Thread thread;
            if (philosopherCount < 2) {
                Printer.show("Choose more than 1 Philosopher");
                return;
            }
            Fork[] fork = new Fork[philosopherCount];
            Philosopher[] philosophers = new Philosopher[philosopherCount];
            for (int i = 0; i < philosopherCount; i++) {
                fork[i] = new Fork(i, true);
            }

            for (int i = 0; i < philosopherCount; i++) {
                thread = new Thread(new Philosopher(fork[i], fork[(i + 1) % philosopherCount]), "Philosopher " + i);
                thread.start();
            }
        } catch (IOException e) {
            Printer.show("Exception: " + e);
        }
    }
}
