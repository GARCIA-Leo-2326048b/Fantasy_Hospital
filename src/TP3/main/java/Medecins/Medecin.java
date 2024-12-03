package Medecins;
import Creatures.Creature;
import Maladies.Maladie;
import Maladies.MaladieType;
import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;

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

    default MaladieType selectionMaladie(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("De quelle maladie souhaitez-vous soigner le service ? (1: MDC, 2: FOMO, 3: DRS, 4: PEC, 5: ZPL)");
        System.out.print("Votre choix : ");
        int choixMaladie = scanner.nextInt();
        return switch (choixMaladie) {
            case 1 -> MaladieType.MDC;
            case 2 -> MaladieType.FOMO;
            case 3 -> MaladieType.DRS;
            case 4 -> MaladieType.PEC;
            case 5 -> MaladieType.ZPL;
            default -> null;
        };

    }

    default Budget selectionBudget(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel budget voulez-vous attribuer à ce service ? (1: inexistant, 2: médiocre, 3: insuffisant, 4: faible)");
        System.out.print("Votre choix : ");
        int choixBudget = scanner.nextInt();
        return switch (choixBudget) {
            case 1 -> Budget.inexistant;
            case 2 -> Budget.médiocre;
            case 3 -> Budget.insuffisant;
            case 4 -> Budget.faible;
            default -> null;
        };
    }
     default void gererService(ServiceMedical service) {
         Scanner scanner = new Scanner(System.in);

        System.out.println("\nC'est à vous de jouer !");
        examinerService(service);

        while (true) {
            try {
                System.out.println("\nQue souhaitez-vous faire ? (1: Soigner le service, 2: Réviser le budget, 3: Quitter)");
                System.out.print("Votre choix : ");
                int choixAction = scanner.nextInt();

                switch (choixAction) {
                    case 1 -> {
                        MaladieType maladieChoisie = selectionMaladie();
                        if (maladieChoisie == null) {
                            System.out.println("Choix de maladie invalide.");
                            continue;
                        }
                        soignerService(service, new Maladie(maladieChoisie));
                    }
                    case 2 -> {
                        Budget budgetChoisi = selectionBudget();
                        if (budgetChoisi == null) {
                            System.out.println("Choix de budget invalide.");
                            continue;
                        }
                        reviserBudget(service, budgetChoisi);
                    }
                    case 3 -> {
                        System.out.println("Vous avez décidé de quitter la gestion de ce service.");
                        return;
                    }
                    default -> System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 3.");
                }
                break;

            } catch (Exception e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine();
            }
        }
    }

}











