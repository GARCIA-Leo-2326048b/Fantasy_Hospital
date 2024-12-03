package HopitalsFantastiques;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Medecins.Medecin;
import ServicesMedicaux.ServiceMedical;
import Creatures.Creature;

public class HopitalFantastique {
    private String nom;
    private int SERVICES_MAX;
    private List<ServiceMedical> servicesMedicaux;
    private List<Creature> medecins;

    public HopitalFantastique(String nom) {
        this.nom = nom;
        this.SERVICES_MAX = 5;
        this.servicesMedicaux = new ArrayList<>();
        this.medecins = new ArrayList<>();

    }

    public List<ServiceMedical> getServicesMedicaux() {
        return servicesMedicaux;
    }

    public List<Creature> getMedecins() {
        return medecins;
    }

    public int getSERVICES_MAX() {
        return SERVICES_MAX;
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


    public void ajouterMedecin(Creature medecin) {
        if (!medecin.estMedecin()) {
            throw new IllegalStateException(medecin.getNom() + " n'est pas un médecin.");
        }
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
    public void afficherMedecins() {
        Iterator<Creature> iterator = medecins.iterator();
        System.out.println("Liste des médecins dans l'hôpital :");
        while (iterator.hasNext()) {
            Creature medecin = iterator.next();
            System.out.println("- " + medecin.getNom());
        }
    }

    public void afficherCaracteristiques() {
        int numMedecin =1,numService = 1;
        System.out.println("Hôpital Fantastique : " + nom);
        System.out.println("Nombre maximal de services : " + SERVICES_MAX);
        System.out.println("Services médicaux :");
        for (ServiceMedical service : servicesMedicaux) {
            System.out.println(numService +" - ");
            service.afficherCaracteristiques();
            numService++;
        }
        System.out.println("Médecins :");
        for (Creature medecin : medecins) {
            System.out.println(numMedecin +" - " + medecin.getNom() + ", Sexe : " + medecin.getSexe() + ", Âge : " + medecin.getAge());
            numMedecin++;
        }
        System.out.println("Nombre total de créatures dans l'hôpital : " + nombreTotalCreatures());
    }



}