package Medecins;

import Creatures.Age;
import Maladies.Maladie;
import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;

public class Medecin {
    private String nom;
    private String sexe;
    private Age age;

    public Medecin(String nom, String sexe, Age age) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
    }
    public String getNom() {
        return nom;
    }

    public String getSexe() {
        return sexe;
    }

    public Age getAge() {
        return age;
    }
    public void examinerService(ServiceMedical service) {
        System.out.println(nom + " examine le service " + service.getNom());
        service.afficherCaracteristiques();
    }

    public void soignerService(ServiceMedical service, Maladie maladie) {
        System.out.println(nom + " soigne les créatures du service " + service.getNom());
        service.soignerCreatures(maladie);
    }

    public void reviserBudget(ServiceMedical service, Budget nouveauBudget) {
        service.reviserBudget(nouveauBudget);
        System.out.println(nom + " a révisé le budget du service " + service.getNom() + " à : " + nouveauBudget);
    }


}
