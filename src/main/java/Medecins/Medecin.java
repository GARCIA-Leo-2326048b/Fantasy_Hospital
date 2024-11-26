package Medecins;
import Creatures.Creature;
import HopitalsFantastiques.HopitalFantastique;
import Maladies.Maladie;
import Maladies.MaladieType;
import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;

import java.util.Random;
import java.util.Scanner;

public interface Medecin {

    default void examinerService(ServiceMedical service) {
        if (!((Creature) this).estMedecin()) {
            throw new IllegalStateException(((Creature) this).getNom() + " n'est pas médecin et ne peut pas examiner le service.");
        }
        System.out.println(((Creature) this).getNom()+ " examine le service " + service.getNom());
        service.afficherCaracteristiques();
    }

    default void soignerService(ServiceMedical service, Maladie maladie) {
        if (!((Creature) this).estMedecin()) {
            throw new IllegalStateException(((Creature) this).getNom() + " n'est pas médecin et ne peut pas soigner les créatures.");
        }
        System.out.println(((Creature) this).getNom() + " soigne les créatures du service " + service.getNom());
        service.soignerCreatures(maladie);
    }

    default void reviserBudget(ServiceMedical service, Budget nouveauBudget) {
        if (!((Creature) this).estMedecin()) {
            throw new IllegalStateException(((Creature) this).getNom() + " n'est pas médecin et ne peut pas réviser le budget.");
        }
        service.reviserBudget(nouveauBudget);
        System.out.println(((Creature) this).getNom() + " a révisé le budget du service " + service.getNom() + " à : " + nouveauBudget);
    }

    default void gererHopital(ServiceMedical service){
        System.out.println("\nC'est à vous de jouer !");
        int actionsRestantes = 5; // Nombre d'actions par intervalle
        Scanner scanner = new Scanner(System.in);
        examinerService(service);

        while (actionsRestantes > 0) {
            System.out.println("Vous avez " + actionsRestantes + " actions restantes.");
            System.out.println("Que souhaitez-vous faire ? (1: Soigner le service créature, 2: Réviser le budget du service, 3: Quitter le service)");
            System.out.print("Votre choix : ");
            int choixAction = scanner.nextInt();

            switch (choixAction) {
                case 1:
                    System.out.println("De quelle maladie souhaitez vous soigner le service ?(1: MDC, 2: FOMO, 3: DRS, 4: PEC, 5: ZPL)");
                    System.out.print("Votre choix : ");
                    int choixMaladie = scanner.nextInt();
                    // Associer le choix utilisateur à une maladie
                    MaladieType maladieChoisie;
                    switch (choixMaladie) {
                        case 1 -> maladieChoisie = MaladieType.MDC;
                        case 2 -> maladieChoisie = MaladieType.FOMO;
                        case 3 -> maladieChoisie = MaladieType.DRS;
                        case 4 -> maladieChoisie = MaladieType.PEC;
                        case 5 -> maladieChoisie = MaladieType.ZPL;
                        default -> {
                            System.out.println("Choix de maladie invalide.");
                            continue; // Revenir au menu principal sans consommer d'action
                        }
                    }

                    // Appeler la méthode pour soigner le service
                    soignerService(service, new Maladie(maladieChoisie));
                    break;
                case 2:
                    System.out.println("Quel budget voulez vous attribuer à ce service ?");
                    System.out.print("Votre choix : ");
                    int choixBudget = scanner.nextInt();
                    Budget budgetChoisi;
                    switch (choixBudget) {
                        case 1 -> budgetChoisi = Budget.inexistant;
                        case 2 -> budgetChoisi = Budget.médiocre;
                        case 3 -> budgetChoisi = Budget.insuffisant;
                        case 4 -> budgetChoisi = Budget.faible;
                        default -> {
                            System.out.println("Choix de maladie invalide.");
                            continue;
                        }
                    }
                    reviserBudget(service,budgetChoisi);
                    break;
                case 3:
                    System.out.println("Vous avez décidé de quitter la gestion.");
                    actionsRestantes = 0; // Met fin à la boucle
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 3.");
                    continue;

            }
            actionsRestantes--;
        }
    }



}











