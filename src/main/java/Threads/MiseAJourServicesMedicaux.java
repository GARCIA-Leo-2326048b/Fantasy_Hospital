package Threads;

import java.util.Random;

public class MiseAJourServicesMedicaux implements Runnable {

    public void run() {
        while (true) {
            //mettre a jour les services
            try {
                Thread.sleep(5000); // Pause de 5 secondes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
