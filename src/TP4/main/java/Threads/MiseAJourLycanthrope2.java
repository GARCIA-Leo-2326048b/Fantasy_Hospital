package Threads;

import Lycanthrope.Lycanthrope2;
import Lycanthrope.TypeHurlement;

import java.util.List;
import java.util.Random;

/**
 * Classe qui gère les mises à jour lycanthropes.
 */
public class MiseAJourLycanthrope2 implements Runnable {

    // Attributs

    private List<Lycanthrope2> lycanthropes;
    private Random random = new Random();
    private boolean isRunning = true;

    /**
     * Constructeur de MiseAJourLycanthrope2
     *
     * @param lycanthropes Liste des lycanthropes à gérer
     */
    public MiseAJourLycanthrope2(List<Lycanthrope2> lycanthropes) {
        this.lycanthropes = lycanthropes;
    }

    /**
     * Méthode principale exécutée par le thread.
     */
    @Override
    public void run() {
        while (isRunning) {
            try {
                gererVieillissement();
                Thread.sleep(10000); // Pause entre les vieillissement

                gererMaladiesEtGuerisons();

                gererTransformationsEnHumain();

                verifierPerteDeRang();

                gererTentativesDomination();

                gererHurlements();

                // Pause entre chaque cycle
                Thread.sleep(5000); // 5 secondes
            } catch (InterruptedException e) {
                System.out.println("Thread interrompu : " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Gère le vieillissement des lycanthropes
     */
    private void gererVieillissement() {
        for (Lycanthrope2 lycanthrope : lycanthropes) {
            lycanthrope.veillir();
        }
    }

    /**
     * Gère les maladies et guérisons des lycanthropes
     */
    private void gererMaladiesEtGuerisons() {
        for (Lycanthrope2 lycanthrope : lycanthropes) {
            if (!lycanthrope.isMalade() && random.nextInt(100) < 20) { // 20% de chance de tomber malade
                lycanthrope.devenirMalade();
            } else if (lycanthrope.isMalade() && random.nextInt(100) < 30) { // 30% de chance de guérir
                lycanthrope.guerir();
            }
        }
    }

    /**
     * Gère les tentatives de transformation en humain.
     */
    private void gererTransformationsEnHumain() {
        for (Lycanthrope2 lycanthrope : lycanthropes) {
            if (!lycanthrope.isMort() && random.nextInt(100) < 5) { // 5% de chance de tenter la transformation
                lycanthrope.seTransformerEnHumain();
            }
        }
    }

    /**
     * Vérifie la perte de rang pour les lycanthropes.
     */
    private void verifierPerteDeRang() {
        for (Lycanthrope2 lycanthrope : lycanthropes) {
            lycanthrope.verifierPerteDeRang();
        }
    }

    /**
     * Gère les tentatives de domination entre les lycanthropes.
     */
    private void gererTentativesDomination() {
        if (lycanthropes.size() > 1) {
            for (int i = 0; i < lycanthropes.size(); i++) {
                Lycanthrope2 defiant = lycanthropes.get(i);
                Lycanthrope2 cible = lycanthropes.get(random.nextInt(lycanthropes.size()));

                if (defiant != cible && !defiant.isMort() && !cible.isMort()) {
                    defiant.tenterDomination(cible);
                }
            }
        }
    }

    /**
     * Gère les hurlements et leurs réactions.
     */
    private void gererHurlements() {
        for (Lycanthrope2 lycanthrope : lycanthropes) {
            if (!lycanthrope.isMort() && random.nextInt(100) < 10) { // 10% de chance de hurler
                TypeHurlement hurlement = TypeHurlement.values()[random.nextInt(TypeHurlement.values().length)];
                lycanthrope.hurler(hurlement);

                // Réaction des autres lycanthropes au hurlement
                for (Lycanthrope2 autre : lycanthropes) {
                    if (autre != lycanthrope && !autre.isMort()) {
                        autre.entendreHurlement(hurlement);
                    }
                }
            }
        }
    }

    /**
     * Permet d'arrêter le thread proprement.
     */
    public void stop() {
        isRunning = false;
    }
}
