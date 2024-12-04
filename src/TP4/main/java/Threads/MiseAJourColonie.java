package Threads;
import Colonie.Colonie;
import Lycanthrope.TypeHurlement;
import Meute.Meute;

import java.util.Random;

/**
 * Classe qui gère les mises à jour périodiques des colonies.
 */
public class MiseAJourColonie implements Runnable {

    // Attributs

    private final Colonie colonie;
    private final Random random;
    private boolean isRunning = true;

    /**
     * Constructeur de la classe MiseAJourColonie.
     *
     * @param colonie La colonie à gérer.
     */
    public MiseAJourColonie(Colonie colonie) {
        this.colonie = colonie;
        this.random = new Random();
        int i = 1;
        for (Meute meute : colonie.getMeutes()){
            String nomThread = "MAJMeute" + i; // Génération du nom
            Thread t = new Thread(new MiseAJourMeute(meute), nomThread);
            i ++;
        }
    }

    /**
     * Méthode principale exécutée par le thread.
     */
    @Override
    public void run() {
        while (isRunning) {
            try {
                // Gestion des saisons
                gererSaisons();

                // Redistribution des lycanthropes sans meute
                colonie.redistribuerLycanthropesSansMeute();

                // Hurlements collectifs
                declencherHurlementCollectif();

                // Pause entre chaque cycle
                Thread.sleep(10000); // Pause de 10 secondes
            } catch (InterruptedException e) {
                System.out.println("Thread interrompu : " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Gère l'activation et la désactivation des saisons des amours.
     */
    private void gererSaisons() {
        if (random.nextBoolean()) { // Simule une alternance entre activation et désactivation
            colonie.activerSaisonDesAmours();
        } else {
            colonie.desactiverSaisonDesAmours();
        }
    }

    /**
     * Déclenche un hurlement collectif pour la colonie.
     */
    private void declencherHurlementCollectif() {
        TypeHurlement type = TypeHurlement.values()[random.nextInt(TypeHurlement.values().length)];
        colonie.hurlementCollectif(type);
    }

    /**
     * Arrête le thread proprement.
     */
    public void stop() {
        isRunning = false;
    }
}

