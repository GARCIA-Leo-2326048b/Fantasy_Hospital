package Medecins;
import Creatures.Creature;
import Maladies.Maladie;
import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;

public interface Medecin {

    default void examinerService(ServiceMedical service, Creature medecin) {
        if (!medecin.estMedecin()) {
            throw new IllegalStateException(medecin.getNom() + " n'est pas médecin et ne peut pas examiner le service.");
        }
        System.out.println( medecin.getNom()+ " examine le service " + service.getNom());
        service.afficherCaracteristiques();
    }

    default void soignerService(ServiceMedical service, Maladie maladie,Creature medecin) {
        if (!medecin.estMedecin()) {
            throw new IllegalStateException(medecin.getNom() + " n'est pas médecin et ne peut pas soigner les créatures.");
        }
        System.out.println(medecin.getNom() + " soigne les créatures du service " + service.getNom());
        service.soignerCreatures(maladie);
    }

    default void reviserBudget(ServiceMedical service, Budget nouveauBudget,Creature medecin) {
        if (!medecin.estMedecin()) {
            throw new IllegalStateException(medecin.getNom() + " n'est pas médecin et ne peut pas réviser le budget.");
        }
        service.reviserBudget(nouveauBudget);
        System.out.println(medecin.getNom() + " a révisé le budget du service " + service.getNom() + " à : " + nouveauBudget);
    }

    default void gererHopital(){
        System.out.println("\nC'est à vous de jouer !");
        int actionsRestantes = 5; // Nombre d'actions par intervalle
        while (actionsRestantes > 0) {
            System.out.println("Vous avez " + actionsRestantes + " actions restantes.");
            System.out.println("Que souhaitez-vous faire ? (1: Soigner une créature, 2: Améliorer un service, 3: Quitter)");

            //switch des actions possible


            actionsRestantes--;
        }
    }



}











