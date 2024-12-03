package Threads;

import Meute.Meute;
import Lycanthrope.TypeHurlement;
import Lycanthrope.Lycanthrope2;

import java.util.Random;

/**
 * La classe MiseAJourMeute gère les événements liés à une seule meute.
 */
public class MiseAJourMeute implements Runnable {

    private final Meute meute;
    private final Random random;

    /**
     * Constructeur pour la classe MiseAJourMeute.
     *
     * @param meute La meute à gérer.
     */
    public MiseAJourMeute(Meute meute) {
        this.meute = meute;
        this.random = new Random();
        // Application de la gestion de la mise à jour des lycanthropes de la liste
        Thread thread = new Thread(new MiseAJourLycanthrope2(meute.getMembres()));
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Gestion de la reproduction si la saison des amours est active
                meute.reproduire();

                // Gestion des conflits hiérarchiques internes
                gererConflitsHierarchiques();

                // Déclenchement de hurlements spécifiques à la meute
                declencherHurlementDeMeute();

                // Pause pour simuler un intervalle de temps
                Thread.sleep(5000); // 5 secondes entre chaque mise à jour
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // Arrêt du thread en cas d'interruption
            }
        }
    }

    /**
     * Gère les conflits hiérarchiques internes dans la meute.
     */
    private void gererConflitsHierarchiques() {
        if (meute.getMembres().size() > 1) {
            Lycanthrope2 defiant = meute.getMembres().get(random.nextInt(meute.getMembres().size()));
            Lycanthrope2 cible = meute.getMembres().get(random.nextInt(meute.getMembres().size()));

            // S'assurer que le défieur et la cible sont différents
            if (defiant != cible) {
                meute.gererConflitsHierarchiques(defiant, cible);
            }
        }
    }

    /**
     * Déclenche un hurlement collectif pour la meute.
     */
    private void declencherHurlementDeMeute() {
        TypeHurlement type = TypeHurlement.values()[random.nextInt(TypeHurlement.values().length)];
        meute.hurlementCollectif(type);
    }



}
