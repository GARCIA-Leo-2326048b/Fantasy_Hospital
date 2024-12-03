package Threads;

import Meute.Meute;
import Lycanthrope.TypeHurlement;
import Lycanthrope.Lycanthrope2;


import java.util.List;
import java.util.Random;

public class MiseAJourMeute extends Thread {

    private final List<Meute> meutes;
    private final Random random = new Random();

    public MiseAJourMeute(List<Meute> meutes) {
        this.meutes = meutes;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Reproduction dans les meutes
                gererReproduction();

                // Conflits hiérarchiques internes
                gererConflitsHierarchiques();

                // Hurlements de meute
                gererHurlements();

                // Dissolution des meutes
                verifierDissolutions();

                // Pause avant la prochaine itération
                Thread.sleep(5000); // Temps entre les mises à jour (5 secondes)
            } catch (InterruptedException e) {
                System.err.println("Mise à jour des meutes interrompue : " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Gère la reproduction dans les meutes si la saison des amours est active.
     */
    private void gererReproduction() {
        for (Meute meute : meutes) {
            if (meute.isSaisonDesAmours()) {
                meute.reproduire();
            }
        }
    }

    /**
     * Gère les conflits hiérarchiques entre membres des meutes.
     */
    private void gererConflitsHierarchiques() {
        for (Meute meute : meutes) {
            List<Lycanthrope2> membres = meute.getMembres();
            if (membres.size() > 1) {
                int defiantIndex = random.nextInt(membres.size());
                int cibleIndex = random.nextInt(membres.size());
                while (cibleIndex == defiantIndex) {
                    cibleIndex = random.nextInt(membres.size());
                }
                Lycanthrope2 defiant = membres.get(defiantIndex);
                Lycanthrope2 cible = membres.get(cibleIndex);
                meute.gererConflitsHierarchiques(defiant, cible);
            }
        }
    }

    /**
     * Déclenche des hurlements spécifiques dans les meutes.
     */
    private void gererHurlements() {
        for (Meute meute : meutes) {
            TypeHurlement type = TypeHurlement.values()[random.nextInt(TypeHurlement.values().length)];
            meute.hurlementCollectif(type);
        }
    }

    /**
     * Vérifie si des meutes doivent être dissoutes.
     */
    private void verifierDissolutions() {
        for (Meute meute : meutes) {
            if (meute.isDissoute()) {
                // Simuler la gestion de la dissolution si la meute est liée à une colonie
                // Exemple : ajouter les membres aux lycanthropes sans meute de la colonie.
                System.out.println("La meute " + meute + " est dissoute.");
                meutes.remove(meute);
            }
        }
    }
}
