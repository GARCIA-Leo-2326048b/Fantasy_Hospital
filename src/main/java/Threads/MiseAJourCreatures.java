package Threads;

import Creatures.Creature;
import HopitalsFantastiques.HopitalFantastique;
import Maladies.Maladie;
import Maladies.MaladieType;
import ServicesMedicaux.ServiceMedical;

import java.util.List;
import java.util.Random;

public class MiseAJourCreatures implements Runnable {
    private HopitalFantastique hopital;
    private Random random = new Random();

    public MiseAJourCreatures(HopitalFantastique hopital) {
        this.hopital = hopital;
    }

    public void run() {
        if (!verifierHopitalStructure()) {
            System.out.println("Structure de l'hôpital incorrecte. Impossible de continuer.");
            return;
        }

        while (true) {

            ServiceMedical service = selectionnerServiceAleatoire();
            Creature creature = selectionnerCreatureAleatoire(service);

            // Modifier aléatoirement l'état de la créature
            int action = random.nextInt(3); // Trois actions possibles
            switch (action) {
                case 0:
                    // Rendre la créature malade

                    if(creature.getMaladies().size()==5)continue;

                    Maladie maladie = creerMaladieAleatoire();
                    creature.tomberMalade(maladie);
                    break;
                case 1:
                    // Faire évoluer une maladie existante

                    if (creature.getMaladies().isEmpty())continue;

                    // Sélectionner une maladie au hasard dans la liste des maladies de la créature
                    Maladie maladieExistante = selectionnerMaladieAleatoire(creature);
                    // Faire évoluer la maladie sélectionnée
                    maladieExistante.augmenterNiveau();

                    break;
                case 2:
                    // Faire évoluer le moral de la créature

                    creature.attendre(service);
                    if(creature.getMoral() == 0){
                        creature.hurler();
                        if (creature.getNombreHurlement() == 10) {
                            creature.setNombreHurlement(0);
                            creature.sEmporter();

                            if (random.nextDouble() < 0.7) {
                                //creature.contaminer();
                            }
                        }
                    }


                    break;
                default:
                    break;
            }

            try {
                Thread.sleep(5000); // Pause de 5 secondes
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

    public Maladie selectionnerMaladieAleatoire(Creature creature) {
        int indexMaladie = random.nextInt(creature.getMaladies().size());
        return creature.getMaladies().get(indexMaladie);
    }

    public ServiceMedical selectionnerServiceAleatoire() {
        // Obtenir la liste des services médicaux de l'hôpital
        List<ServiceMedical> services = hopital.getServicesMedicaux();

        // Sélectionner un service médical aléatoire
        int serviceIndex = random.nextInt(services.size());
        return services.get(serviceIndex);
    }

    public Creature selectionnerCreatureAleatoire(ServiceMedical service) {
        // Obtenir la liste des créatures dans ce service
        List<Creature> creatures = service.getCreatures();

        // Sélectionner une créature aléatoire
        int creatureIndex = random.nextInt(creatures.size());
        return creatures.get(creatureIndex);
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
}
