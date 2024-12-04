import Creatures.*;
import Creatures.Bestiales.*;
import Creatures.Demoralisantes.*;
import Creatures.MortVivantes.*;
import Creatures.VIP.*;
import HopitalsFantastiques.GestionHopital;
import HopitalsFantastiques.HopitalFantastique;
import Maladies.*;
import ServicesMedicaux.*;
import Threads.MiseAJourCreatures;
import Threads.MiseAJourServicesMedicaux;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Création de l'hôpital
        HopitalFantastique hopital = new HopitalFantastique("Hopital Fantastique");
        // Création de services médicaux
        ServiceMedical service1 = new ServiceMedical("Service des Orques",25.75, Budget.faible, TypeCreature.ORQUE);
        ServiceMedical service2 = new ServiceMedical("Service des Vampires",17.23, Budget.médiocre, TypeCreature.VAMPIRE);
        ServiceMedical service3 = new ServiceMedical("Service des Zombie",31.50, Budget.insuffisant, TypeCreature.ZOMBIE);
        ServiceMedical service4 = new ServiceMedical("Service des Nains", 22.00, Budget.inexistant, TypeCreature.NAIN);
        ServiceMedical service5 = new ServiceMedical("Service des Elfes", 28.00, Budget.faible, TypeCreature.ELFE);
        hopital.ajouterService(service1);
        hopital.ajouterService(service2);
        hopital.ajouterService(service3);
        hopital.ajouterService(service4);
        hopital.ajouterService(service5);

        // Création de créatures/médecins
        Creature medecin1 = new Elfe("Dr. Watson", "Homme", Age.adulte);
        Creature medecin2 = new Reptilien("Dr. Holmes", "Homme", Age.jeune);
        hopital.ajouterMedecin(medecin1);
        hopital.ajouterMedecin(medecin2);


        // Orques
        Creature orque1 = new Orque("Ork", "Mâle", 150.0, 2.30, Age.adulte, 100);
        Creature orque2 = new Orque("Grul", "Femelle", 140.0, 2.20, Age.adulte, 90);
        Creature orque3 = new Orque("Thrak", "Mâle", 160.0, 2.50, Age.adulte, 100);

        service1.ajouterCreature(orque1);
        service1.ajouterCreature(orque2);
        service1.ajouterCreature(orque3);

        // Vampires
        Creature vampire1 = new Vampire("Dracula", "Mâle", 82.54, 1.82, Age.vieux, 5);
        Creature vampire2 = new Vampire("Vlad", "Mâle", 80.45, 1.85, Age.adulte, 7);
        Creature vampire3 = new Vampire("Elvira", "Femelle", 75.00, 1.75, Age.jeune, 3);

        service2.ajouterCreature(vampire1);
        service2.ajouterCreature(vampire2);
        service2.ajouterCreature(vampire3);


        // Zombies
        Creature zombie1 = new Zombie("Zombus", "Mâle", 90.00, 1.75, Age.vieux, 60);
        Creature zombie2 = new Zombie("Zomblina", "Femelle", 95.50, 1.70, Age.jeune, 45);
        Creature zombie3 = new Zombie("Zed", "Mâle", 92.00, 1.80, Age.adulte, 50);
        Creature zombie4 = new Zombie("Rot", "Mâle", 100.00, 1.85, Age.adulte, 55);
        Creature zombie5 = new Zombie("Decay", "Femelle", 105.00, 1.65, Age.vieux, 60);

        service3.ajouterCreature(zombie1);
        service3.ajouterCreature(zombie2);
        service3.ajouterCreature(zombie3);
        service3.ajouterCreature(zombie4);
        service3.ajouterCreature(zombie5);

        // Nains
        Creature nain1 = new Nain("Gimli", "Mâle", 80.0, 1.40, Age.adulte, 10);
        Creature nain2 = new Nain("Dori", "Mâle", 75.0, 1.45, Age.vieux, 12);
        Creature nain3 = new Nain("Balin", "Mâle", 78.0, 1.42, Age.adulte, 11);
        Creature nain4 = new Nain("Thorin", "Mâle", 82.0, 1.50, Age.vieux, 15);

        service4.ajouterCreature(nain1);
        service4.ajouterCreature(nain2);
        service4.ajouterCreature(nain3);
        service4.ajouterCreature(nain4);

        // Elfes
        Creature elfe1 = new Elfe("Legolas", "Mâle", 70.0, 1.85, Age.jeune, 8);
        Creature elfe2 = new Elfe("Arwen", "Femelle", 55.0, 1.70, Age.adulte, 9);
        Creature elfe3 = new Elfe("Galadriel", "Femelle", 65.0, 1.80, Age.vieux, 10);

        service5.ajouterCreature(elfe1);
        service5.ajouterCreature(elfe2);
        service5.ajouterCreature(elfe3);

        Maladie maladie1 = new Maladie(MaladieType.MDC);  // Maladie débilitante chronique
        Maladie maladie2 = new Maladie(MaladieType.FOMO);  // Syndrome fear of missing out
        Maladie maladie3 = new Maladie(MaladieType.DRS);   // Dépendance aux réseaux sociaux
        Maladie maladie4 = new Maladie(MaladieType.PEC);   // Porphyrie érythropoétique congénitale
        Maladie maladie5 = new Maladie(MaladieType.ZPL);

        //
        orque1.tomberMalade(maladie1);
        orque1.tomberMalade(maladie2);
        orque2.tomberMalade(maladie3);
        orque3.tomberMalade(maladie4);
        orque3.tomberMalade(maladie5);

        vampire1.tomberMalade(maladie2);
        vampire2.tomberMalade(maladie3);
        vampire3.tomberMalade(maladie1);

        zombie1.tomberMalade(maladie5);
        zombie2.tomberMalade(maladie4);
        zombie3.tomberMalade(maladie3);
        zombie4.tomberMalade(maladie2);
        zombie5.tomberMalade(maladie1);

        nain1.tomberMalade(maladie1);
        nain2.tomberMalade(maladie2);
        nain3.tomberMalade(maladie3);
        nain4.tomberMalade(maladie4);

        elfe1.tomberMalade(maladie5);
        elfe2.tomberMalade(maladie4);
        elfe3.tomberMalade(maladie2);

        GestionHopital gestionHopital = new GestionHopital(hopital);

        // Création des threads avec les tâches spécifiques
        MiseAJourCreatures creaturesTache =new MiseAJourCreatures(hopital);
        Thread threadCreatures = new Thread(creaturesTache);
        MiseAJourServicesMedicaux serviceTask = new MiseAJourServicesMedicaux(hopital);
        Thread threadServices = new Thread(serviceTask);


            System.out.println("\n=== Menu principal ===");
            System.out.println("1. Démarrer");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            if(choix==1) {
               threadServices.start();
               threadCreatures.start();
                Thread.sleep(10000);
                while (true) {
                    serviceTask.mettreEnPause();
                    creaturesTache.mettreEnPause();// Met le thread en pause

                    System.out.println("\nC'est à vous de gérer l'hopital !");
                    System.out.println("1. Continuer");
                    System.out.println("0. Quitter");
                    choix = scanner.nextInt();
                    if (choix == 0) break;

                    gestionHopital.gerer();

                    serviceTask.reprendre();
                    creaturesTache.reprendre();// Relance le thread
                    Thread.sleep(10000);

                }
                serviceTask.arreter();
                creaturesTache.arreter();
                threadServices.interrupt();
                threadCreatures.interrupt();
            }
            System.out.println("Au revoir !");


    }

}
