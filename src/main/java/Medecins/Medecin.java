package Medecins;
import Creatures.Age;
import Creatures.Creature;
import Maladies.Maladie;
import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;

public interface Medecin {

    public default void examinerService(ServiceMedical service, Creature medecin) {
        if (!medecin.estMedecin()) {
            throw new IllegalStateException(medecin.getNom() + " n'est pas médecin et ne peut pas examiner le service.");
        }
        System.out.println( medecin.getNom()+ " examine le service " + service.getNom());
        service.afficherCaracteristiques();
    }

    public default void soignerService(ServiceMedical service, Maladie maladie,Creature medecin) {
        if (!medecin.estMedecin()) {
            throw new IllegalStateException(medecin.getNom() + " n'est pas médecin et ne peut pas soigner les créatures.");
        }
        System.out.println(medecin.getNom() + " soigne les créatures du service " + service.getNom());
        service.soignerCreatures(maladie);
    }

    public default void reviserBudget(ServiceMedical service, Budget nouveauBudget,Creature medecin) {
        if (!medecin.estMedecin()) {
            throw new IllegalStateException(medecin.getNom() + " n'est pas médecin et ne peut pas réviser le budget.");
        }
        service.reviserBudget(nouveauBudget);
        System.out.println(medecin.getNom() + " a révisé le budget du service " + service.getNom() + " à : " + nouveauBudget);
    }



}











