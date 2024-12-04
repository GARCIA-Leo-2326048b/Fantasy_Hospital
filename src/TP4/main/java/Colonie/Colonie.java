package Colonie;

import Meute.Meute;
import Lycanthrope.Lycanthrope2;
import Lycanthrope.TypeHurlement;
import Lycanthrope.RangHierarchie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe Colonie représente un ensemble de meutes et de lycanthropes sans meute
 */
public class Colonie {

    // Attributs

    private List<Meute> meutes; // Liste des meutes
    private List<Lycanthrope2> lycanthropesSansMeute; // Liste des lycanthropes solitaire (sans meute)
    private String nom; // Nom de la colonie
    private boolean enCrise; // Indique si la colonie est en crise
    private boolean saisonDesAmoursActive; // Indique si la saison des amours est active

    /**
     * Constructeur de la classe Colonie
     *
     * @param nom Nom de la colonie
     */
    public Colonie(String nom) {
        this.nom = nom;
        this.meutes = new ArrayList<>();
        this.lycanthropesSansMeute = new ArrayList<>();
        this.enCrise = false;
        this.saisonDesAmoursActive = false;
    }

    // Gestion des meutes

    /**
     * Ajoute une meute à la Colonie
     *
     * @param meute La meute à ajouter.
     */
    public void ajouterMeute(Meute meute) {
        if (!meutes.contains(meute)) {
            meutes.add(meute);
            verifierEtatCrise(); // Vérifie si l'ajout d'une meute modifie l'état de crise
        }
    }

    /**
     * Supprime une meute de la colonie et redistribue ses membres dans les lycanthropes sans meute
     *
     * @param meute La meute à supprimer
     */
    public void supprimerMeute(Meute meute) {
        if (meutes.remove(meute)) {
            lycanthropesSansMeute.addAll(meute.getMembres());
            verifierEtatCrise(); // Vérifie si la suppression modifie l'état de crise
        }
    }

    /**
     * Fusionne deux meutes
     * Ajoute les membres de la deuxième dans la première et dissout la deuxième
     *
     * @param meute1 La meute qui accueille les lycanthropes.
     * @param meute2 La meute à fusionner et à dissoudre.
     */
    public void fusionnerMeutes(Meute meute1, Meute meute2) {
        if (meutes.contains(meute1) && meutes.contains(meute2)) {
            for (Lycanthrope2 membre : meute2.getMembres()) {
                meute1.ajouterLycanthrope(membre);
            }
            meute2.dissoudreMeute();
            supprimerMeute(meute2);
        }
    }

    /**
     * Dissout une meute et transfère ses membres dans les lycanthropes sans meute
     *
     * @param meute La meute à dissoudre
     */
    public void dissoudreMeute(Meute meute) {
        if (meutes.contains(meute)) {
            supprimerMeute(meute);
            meute.dissoudreMeute();
        }
    }

    // Gestion des lycanthropes

    /**
     * Ajoute un lycanthrope sans meute à la liste des lycanthropes sans meute.
     *
     * @param lycanthrope Le lycanthrope à ajouter.
     */
    public void ajouterLycanthropeSansMeute(Lycanthrope2 lycanthrope) {
        if (!lycanthropesSansMeute.contains(lycanthrope) && lycanthrope.getMeute() == null) {
            lycanthropesSansMeute.add(lycanthrope);
            verifierEtatCrise(); // Vérifie si l'ajout modifie l'état de crise
        }
    }

    /**
     * Redistribue les lycanthropes sans meute dans les meutes de la colonie
     */
    public void redistribuerLycanthropesSansMeute() {
        for (Lycanthrope2 lycanthrope : new ArrayList<>(lycanthropesSansMeute)) {
            for (Meute meute : meutes) {
                try {
                    meute.ajouterLycanthrope(lycanthrope);
                    lycanthropesSansMeute.remove(lycanthrope);
                    break;
                } catch (Exception ignored) {
                }
            }
        }
        verifierEtatCrise(); // Vérifie cela modifie l'état de crise
    }

    // Saisons

    /**
     * Active la saison des amours dans toutes les meutes de la colonie
     */
    public void activerSaisonDesAmours() {
        saisonDesAmoursActive = true;
        for (Meute meute : meutes) {
            meute.mettreAJourSaisonDesAmours(true);
        }
    }

    /**
     * Désactive la saison des amours dans toutes les meutes de la colonie.
     */
    public void desactiverSaisonDesAmours() {
        saisonDesAmoursActive = false;
        for (Meute meute : meutes) {
            meute.mettreAJourSaisonDesAmours(false);
        }
    }


    // Hurlements

    /**
     * Déclenche un hurlement collectif dans toutes les meutes de la colonie.
     *
     * @param type Le type de hurlement
     */
    public void hurlementCollectif(TypeHurlement type) {
        for (Meute meute : meutes) {
            meute.hurlementCollectif(type);
        }
    }

    // Stats et analyses

    /**
     * Calcule la population totale de la colonie
     *
     * @return Le nombre total de lycanthropes dans la colonie
     */
    public int calculerPopulationTotale() {
        int total = lycanthropesSansMeute.size();
        for (Meute meute : meutes) {
            total += meute.getTaille();
        }
        return total;
    }

    /**
     * Retourne le nombre de meutes dans la colonie.
     *
     * @return Le nombre de meutes.
     */
    public int nombreDeMeutes() {
        return meutes.size();
    }



    /**
     * Répartit les lycanthropes par rang hiérarchique dans la colonie
     *
     * @return Une carte des rangs et leur fréquence
     */
    public Map<RangHierarchie, Integer> repartirParRangs() {
        Map<RangHierarchie, Integer> repartition = new HashMap<>();
        for (Meute meute : meutes) {
            for (Lycanthrope2 lycanthrope : meute.getMembres()) {
                repartition.put(lycanthrope.getRangHierarchie(),
                        repartition.getOrDefault(lycanthrope.getRangHierarchie(), 0) + 1);
            }
        }
        for (Lycanthrope2 lycanthrope : lycanthropesSansMeute) {
            repartition.put(lycanthrope.getRangHierarchie(),
                    repartition.getOrDefault(lycanthrope.getRangHierarchie(), 0) + 1);
        }
        return repartition;
    }

    // Interactions / Crises

    /**
     * Gère une interaction entre cette colonie et une autre.
     *
     * @param autreColonie    L'autre colonie impliquée.
     * @param typeInteraction Le type d'interaction (alliance / rivalite)
     */
    public void gererInteractionEntreColonies(Colonie autreColonie, Interaction typeInteraction) {
        if (typeInteraction == Interaction.Alliance) {
            System.out.println("Alliance conclue entre les colonies " + nom + " et " + autreColonie.nom);
        } else if (typeInteraction == Interaction.Rivalite) {
            System.out.println("Conflit déclaré entre les colonies " + nom + " et " + autreColonie.nom);
        } else {
            System.out.println("Type d'interaction non reconnu.");
        }
    }


    /**
     * Vérifie si la colonie doit être en crise en fonction du nombre
     * de lycanthropes malades.
     */
    public void verifierEtatCrise() {
        int totalPopulation = calculerPopulationTotale();
        if (totalPopulation == 0) {
            enCrise = false;
            return;
        }

        int malades = calculerNombreMalades();
        boolean nouvelEtatCrise = malades >= totalPopulation / 2;

        if (nouvelEtatCrise && !enCrise) {
            declarerCrise();
        } else if (!nouvelEtatCrise && enCrise) {
            mettreFinCrise();
        }
    }

    /**
     * Déclare une crise dans la colonie.
     */
    public void declarerCrise() {
        enCrise = true;
        System.out.println("La colonie " + nom + " est maintenant en crise.");
    }

    /**
     * Met fin à la crise dans la colonie.
     */
    public void mettreFinCrise() {
        enCrise = false;
        System.out.println("La crise dans la colonie " + nom + " est terminée.");
    }

    /**
     * Calcule le nombre total de lycanthropes malades dans la colonie.
     *
     * @return Le nombre de lycanthropes malades.
     */
    public int calculerNombreMalades() {
        int malades = 0;

        // Compter les malades dans les meutes
        for (Meute meute : meutes) {
            malades += meute.getNombreMalades();
        }

        // Compter les malades parmi les lycanthropes sans meute
        for (Lycanthrope2 lycanthrope : lycanthropesSansMeute) {
            if (lycanthrope.isMalade()) {
                malades++;
            }
        }
        return malades;
    }




    // Méthodes utilitaires

    /**
     * Vérifie si la colonie est en crise
     *
     * @return true si la colonie est en crise, sinon false
     */
    public boolean estEnCrise() {
        return enCrise;
    }


    /**
     * Affiche les statistiques de la colonie, y compris l'état de crise et le nombre de malades.
     */
    public void afficherStatistiquesColonie() {
        System.out.println("Statistiques de la colonie " + nom + ":");
        System.out.println("Population totale: " + calculerPopulationTotale());
        System.out.println("Nombre de meutes: " + nombreDeMeutes());
        System.out.println("Lycanthropes sans meute: " + lycanthropesSansMeute.size());
        System.out.println("Colonie en crise: " + (enCrise ? "Oui" : "Non"));
        System.out.println("Nombre de lycanthropes malades: " + calculerNombreMalades());
        System.out.println("Saison des amours active: " + (saisonDesAmoursActive ? "Oui" : "Non"));
    }


    public List<Meute> getMeutes() {
        return meutes;
    }

    public void setMeutes(List<Meute> meutes) {
        this.meutes = meutes;
    }

    public List<Lycanthrope2> getLycanthropesSansMeute() {
        return lycanthropesSansMeute;
    }

    public void setLycanthropesSansMeute(List<Lycanthrope2> lycanthropesSansMeute) {
        this.lycanthropesSansMeute = lycanthropesSansMeute;
    }

    public boolean isEnCrise() {
        return enCrise;
    }

    public void setEnCrise(boolean enCrise) {
        this.enCrise = enCrise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isSaisonDesAmoursActive() {
        return saisonDesAmoursActive;
    }

    public void setSaisonDesAmoursActive(boolean saisonDesAmoursActive) {
        this.saisonDesAmoursActive = saisonDesAmoursActive;
    }
}
