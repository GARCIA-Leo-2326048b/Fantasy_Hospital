package HopitalsFantastiques;

import java.util.ArrayList;
import java.util.List;
import Medecins.Medecin;
import ServicesMedicaux.ServiceMedical;
import Creatures.Creature;
import Threads.MiseAJourCreatures;
import Threads.MiseAJourServicesMedicaux;
import Threads.TacheMedecin;

public class HopitalFantastique {
    private String nom;
    private int SERVICES_MAX;
    private List<ServiceMedical> servicesMedicaux;
    private List<Medecin> medecins;

    public HopitalFantastique(String nom) {
        this.nom = nom;
        this.SERVICES_MAX = 5;
        this.servicesMedicaux = new ArrayList<>();
        this.medecins = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void ajouterService(ServiceMedical service) {
        if (servicesMedicaux.size() < SERVICES_MAX) {
            servicesMedicaux.add(service);
            System.out.println("Service " + service.getNom() + " a été ajouté à l'hôpital " + nom);
        } else {
            System.out.println("Nombre maximal de services atteint dans l'hôpital " + nom);
        }
    }

    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
        System.out.println("Médecin " + medecin.getNom() + " a été ajouté à l'hôpital " + nom);
    }

    public int nombreTotalCreatures() {
        int total = 0;
        for (ServiceMedical service : servicesMedicaux) {
            total += service.getCreatures().size();
        }
        return total;
    }

    public void afficherCreaturesDansServices() {
        System.out.println("Créatures présentes dans les services de l'hôpital " + nom + " :");
        for (ServiceMedical service : servicesMedicaux) {
            System.out.println("Service " + service.getNom() + " :");
            for (Creature creature : service.getCreatures()) {
                System.out.println(" - " + creature.getNom());
            }
        }
    }

    public void afficherCaracteristiques() {
        System.out.println("Hôpital Fantastique : " + nom);
        System.out.println("Nombre maximal de services : " + SERVICES_MAX);
        System.out.println("Services médicaux :");
        for (ServiceMedical service : servicesMedicaux) {
            service.afficherCaracteristiques();
        }
        System.out.println("Médecins :");
        for (Medecin medecin : medecins) {
            System.out.println(" - " + medecin.getNom() + ", Sexe : " + medecin.getSexe() + ", Âge : " + medecin.getAge());
        }
        System.out.println("Nombre total de créatures dans l'hôpital : " + nombreTotalCreatures());
    }

    public static void main(String[] args) {
        //CREATION DE L4HOPITAL
        //HopitalFantastique hopital = new HopitalFantastique();
        //Medecin medecin = new Medecin();

        // Création des threads avec les tâches spécifiques
        Thread threadCreatures = new Thread(new MiseAJourCreatures());
        Thread threadServices = new Thread(new MiseAJourServicesMedicaux());
        Thread threadMedecin = new Thread(new TacheMedecin());

        // Démarrage des threads
        threadCreatures.start();
        threadServices.start();
        threadMedecin.start();
    }

}