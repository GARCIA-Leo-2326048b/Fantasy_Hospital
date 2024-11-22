package Threads;

import Creatures.Creature;
import HopitalsFantastiques.HopitalFantastique;
import Maladies.Maladie;
import Maladies.MaladieType;
import ServicesMedicaux.ServiceMedical;

import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class MiseAJourCreatures implements Runnable {
    private HopitalFantastique hopital;
    private Random random = new Random();
    long debut ;
    long dureeMillis;


    public MiseAJourCreatures(HopitalFantastique hopital) {
        this.hopital = hopital;
        this.debut = System.currentTimeMillis();
        this.dureeMillis = 5000;
    }

    public void run() {
        if (!verifierHopitalStructure()) {
            System.out.println("Structure de l'hôpital incorrecte. Impossible de continuer.");
            return;
        }

        while (System.currentTimeMillis() - debut < dureeMillis) {

            ServiceMedical service = selectionnerAleatoire(hopital.getServicesMedicaux());
            Creature creature = selectionnerAleatoire(service.getCreatures());
            // Modifier aléatoirement l'état de la créature
            int action = random.nextInt(3); // Trois actions possibles
            switch (action) {
                case 0:
                    // Rendre la créature malade
                    rendreCreatureMalade(creature);
                    break;
                case 1:
                    // Faire évoluer une maladie existante
                    if (creature.getMaladies().isEmpty())continue;
                    evoluerMaladie(creature,service);
                    break;
                case 2:
                    // Faire évoluer le moral de la créature
                    evoluerMoral(creature,service);
                    break;
                default:
                    break;
            }
            try {
                sleep(5000); // Pause de 5 secondes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public Maladie creerMaladieAleatoire() {
        // Sélectionner un type de maladie aléatoire
        int indexMaladie = random.nextInt(MaladieType.values().length);
        MaladieType maladieType = MaladieType.values()[indexMaladie];
        return new Maladie(maladieType);
    }


    // Méthode générique pour sélectionner un élément aléatoire
    public <T> T selectionnerAleatoire(List<T> liste) {
        if (liste.isEmpty()) {
            return null;  // Si la liste est vide, retourner null
        }
        int index = random.nextInt(liste.size());
        return liste.get(index);
    }

    // Méthode pour vérifier la structure de l'hôpital
    public boolean verifierHopitalStructure() {
        // Vérifier que l'hôpital a des services médicaux
        List<ServiceMedical> services = hopital.getServicesMedicaux();
        if (services.isEmpty()) {
            System.out.println("L'hôpital n'a pas de services médicaux.");
            return false;
        }


        for (ServiceMedical service : services) {
            if (service.getCreatures().isEmpty()) {
                System.out.println("Le service " + service.getNom() + " ne contient aucune créature.");
                return false;
            }
        }

        return true; // L'hôpital est bien structuré
    }

    private void rendreCreatureMalade(Creature creature) {

        Maladie maladie = creerMaladieAleatoire();
        // Vérifier si la créature a déjà cette maladie
        boolean maladieExistante = creature.getMaladies().stream()
                .anyMatch(m -> m.getNomAbrege().equals(maladie.getNomAbrege()));

        if(maladieExistante)return;
        creature.tomberMalade(maladie);
    }

    private void evoluerMaladie(Creature creature, ServiceMedical service) {
        //Maladie maladieExistante = selectionnerMaladieAleatoire(creature);
        Maladie maladieExistante = selectionnerAleatoire(creature.getMaladies());
        // Faire évoluer la maladie sélectionnée
        maladieExistante.augmenterNiveau();

        if (maladieExistante.estLetal()) {
            creature.trepasser(maladieExistante, service);
        }
    }

    private void evoluerMoral(Creature creature, ServiceMedical service) {

        creature.attendre(service);

        if(creature.getMoral()!=0)return;
        creature.hurler();

        if (creature.getNombreHurlement() < 10)return;
        creature.setNombreHurlement(0);
        creature.sEmporter();

        if (random.nextDouble() < 0.7) {
            if (!creature.getMaladies().isEmpty()) {
                Maladie maladieAContaminer = creature.getMaladies().get(random.nextInt(creature.getMaladies().size()));
                creature.contaminer(maladieAContaminer, service.getAutresCreatures(creature));
            }
        }
    }
}
