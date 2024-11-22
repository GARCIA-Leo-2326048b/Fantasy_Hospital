package Threads;

import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;
import ServicesMedicaux.ServicesSpeciaux.Crypte;
import ServicesMedicaux.ServicesSpeciaux.CentreDeQuarantaine;

import java.util.List;
import java.util.Random;

public class MiseAJourServicesMedicaux extends Thread {
    private final List<ServiceMedical> servicesMedicaux;
    private final Random random = new Random();
    private boolean running = true;

    public MiseAJourServicesMedicaux(List<ServiceMedical> servicesMedicaux) {
        this.servicesMedicaux = servicesMedicaux;
    }

    @Override
    public void run() {
        while (running) {
            try {
                // Parcourir tous les services médicaux et modifier leurs états
                for (ServiceMedical service : servicesMedicaux) {
                    modifierEtatService(service);
                }
                // Pause entre chaque cycle de mise à jour (ex. 5 secondes)
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrompu : " + e.getMessage());
                running = false;
            }
        }
    }

    private void modifierEtatService(ServiceMedical service) {
        System.out.println("Mise à jour du service : " + service.getNom());

        // Modifier aléatoirement le budget du service
        modifierBudget(service);

        // Si c'est un Centre de Quarantaine, modifier l'isolation
        if (service instanceof CentreDeQuarantaine) {
            modifierIsolation((CentreDeQuarantaine) service);
        }

        // Si c'est une Crypte, modifier la température et la ventilation
        if (service instanceof Crypte) {
            modifierCrypte((Crypte) service);
        }
    }

    private void modifierBudget(ServiceMedical service) {
        // Sélection aléatoire d'un budget parmi les valeurs de l'énumération Budget
        Budget[] budgets = Budget.values();
        Budget nouveauBudget = budgets[random.nextInt(budgets.length)];
        service.reviserBudget(nouveauBudget); // Met à jour le budget avec la valeur de l'énumération
        System.out.println("Nouveau budget pour " + service.getNom() + " : " + nouveauBudget.name());
    }


    private void modifierIsolation(CentreDeQuarantaine service) {
        int nouvelleIsolation = random.nextInt(101); // Isolation entre 0 et 100
        service.setIsolation(nouvelleIsolation);
        System.out.println("Nouvelle isolation pour " + service.getNom() + " : " + nouvelleIsolation);
    }

    private void modifierCrypte(Crypte service) {
        int nouvelleTemperature = random.nextInt(41) - 10; // Température entre -10 et 30°C
        int nouvelleVentilation = random.nextInt(101); // Ventilation entre 0 et 100
        service.setTemperature(nouvelleTemperature);
        service.setVentilation(nouvelleVentilation);
        System.out.println("Nouvelle température pour " + service.getNom() + " : " + nouvelleTemperature + "°C");
        System.out.println("Nouveau niveau de ventilation pour " + service.getNom() + " : " + nouvelleVentilation);
    }

    public void stopThread() {
        running = false;
    }
}
