package Threads;

import HopitalsFantastiques.HopitalFantastique;

import java.util.Random;

public class MiseAJourCreatures implements Runnable {

    public void run() {
        while (true) {
           //logique
            try {
                Thread.sleep(5000); // Pause de 5 secondes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
