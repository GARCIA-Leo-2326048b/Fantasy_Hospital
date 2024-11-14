package Threads;

import HopitalsFantastiques.HopitalFantastique;

import java.util.Random;
import java.util.Scanner;

public class TacheMedecin implements Runnable {

    public void run() {
        while (true) {
            System.out.println("\nC'est à vous de jouer !");
            int actionsRestantes = 3; // Nombre d'actions par intervalle
            while (actionsRestantes > 0) {
                System.out.println("Vous avez " + actionsRestantes + " actions restantes.");
                System.out.println("Que souhaitez-vous faire ? (1: Soigner une créature, 2: Améliorer un service, 3: Quitter)");
               //switch des actions possible


                actionsRestantes--;
            }

            // Pause de 10 secondes avant le prochain tour du médecin
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
