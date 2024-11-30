package HopitalsFantastiques;

import Creatures.Age;
import Creatures.Creature;
import Creatures.Demoralisantes.Elfe;
import Creatures.VIP.Reptilien;
import Medecins.Medecin;
import ServicesMedicaux.ServiceMedical;

import java.util.Scanner;

public class GestionHopital {
    private HopitalFantastique hopital;
    private final Scanner scanner = new Scanner(System.in);
    private final int ACTIONS_MAX = 2;

    public GestionHopital(HopitalFantastique hopital) {
        this.hopital = hopital;
    }

    public HopitalFantastique getHopital() {
        return hopital;
    }

    public void setHopital(HopitalFantastique hopital) {
        this.hopital = hopital;
    }

    private int demanderChoixUtilisateur(String message, int min, int max) {
        while (true) {
            try {
                System.out.println(message);
                int choix = Integer.parseInt(scanner.nextLine());
                if (choix >= min && choix <= max) {
                    return choix;
                } else {
                    System.out.println("Numéro invalide. Veuillez choisir un nombre entre " + min + " et " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un numéro valide.");
            }
        }
    }


    public void gerer() {

        int actionsRestantes = ACTIONS_MAX; // Initialisation des actions restantes
        hopital.afficherCaracteristiques();
        System.out.println("Vous avez " + actionsRestantes + " actions restantes.");

        while (actionsRestantes > 0) {
            int numMedecin = demanderChoixUtilisateur(
                    "\nQuel médecin voulez-vous contrôler ? (Entrez son numéro ou 0 pour quitter)", 0, hopital.getMedecins().size());

            // Option de sortie
            if (numMedecin == 0) {
                System.out.println("Vous avez quitté la gestion de l'hôpital.");
                break;
            }

            int numService = demanderChoixUtilisateur(
                    "Dans quel service voulez-vous envoyer le médecin ? (Entrez son numéro)", 1, hopital.getServicesMedicaux().size());

            // Récupérer le médecin et le service
            Medecin medecin = hopital.getMedecins().get(numMedecin - 1);
            ServiceMedical service = hopital.getServicesMedicaux().get(numService - 1);

            // Passer la main au médecin pour gérer le service
            medecin.gererService(service);

            // Décrémenter les actions restantes
            actionsRestantes--;
            System.out.println("Actions restantes : " + actionsRestantes);
        }

        System.out.println("La session de gestion est terminée.");
    }
}
