package Meute;

import Lycanthrope.CategorieAge;
import Lycanthrope.Lycanthrope2;
import Lycanthrope.RangHierarchie;
import Lycanthrope.TypeHurlement;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Meute est un groupe de lycanthropes dirigé par un couple alpha.
 */
public class Meute {

    // Attributs
    private Lycanthrope2 maleAlpha; // Mâle alpha de la meute
    private Lycanthrope2 femelleAlpha; // Femelle alpha de la meute
    private List<Lycanthrope2> membres; // Liste des membres de la meute
    private int taille; // Taille de la meute
    private boolean saisonDesAmours; // Indique si la saison des amours est active
    private int seuilForceOmega; // Seuil de force pour déterminer les omega
    private int jeune = 0; // Compteur global pour nommer les jeunes
    private boolean dissoute;

    /**
     * Constructeur de la classe Meute.
     *
     * @param maleAlpha       Le mâle alpha (doit être adulte et de rang α).
     * @param femelleAlpha    La femelle alpha (doit être adulte et de rang α).
     * @param seuilForceOmega Le seuil de force pour déclarer un lycanthrope omega.
     * @throws IllegalArgumentException Si les critères pour le couple alpha ne sont pas respectés.
     */
    public Meute(Lycanthrope2 maleAlpha, Lycanthrope2 femelleAlpha, int seuilForceOmega) {
        if (maleAlpha == null || femelleAlpha == null) {
            throw new IllegalArgumentException("Un couple alpha est nécessaire pour créer une meute.");
        }

        if (maleAlpha.getRangHierarchie() != RangHierarchie.α || femelleAlpha.getRangHierarchie() != RangHierarchie.α) {
            throw new IllegalArgumentException("Les deux membres du couple doivent avoir le rang α.");
        }

        if (maleAlpha.isSexe() || !femelleAlpha.isSexe()) {
            throw new IllegalArgumentException("Le couple alpha doit être composé d'un mâle et d'une femelle.");
        }

        if (maleAlpha.getCategorieAge() != CategorieAge.ADULTE || femelleAlpha.getCategorieAge() != CategorieAge.ADULTE) {
            throw new IllegalArgumentException("Les membres du couple alpha doivent être adultes.");
        }

        // Initialisation
        this.maleAlpha = maleAlpha;
        this.femelleAlpha = femelleAlpha;
        this.membres = new ArrayList<>();
        this.membres.add(maleAlpha);
        this.membres.add(femelleAlpha);
        this.taille = 2; // Couple alpha
        this.saisonDesAmours = false;
        this.seuilForceOmega = seuilForceOmega;
        this.dissoute = false;

        maleAlpha.setMeute(this);
        femelleAlpha.setMeute(this);
    }

    /**
     * Affiche les caractéristiques de la meute et les informations sur ses membres.
     */
    public void afficherCaracteristiques() {
        if (dissoute) {
            System.out.println("Cette meute est dissoute.");
            return;
        }

        System.out.println("Caractéristiques de la meute :");
        System.out.println("Taille : " + taille);
        System.out.println("Mâle Alpha : " + (maleAlpha != null ? maleAlpha.getNom() : "Aucun"));
        System.out.println("Femelle Alpha : " + (femelleAlpha != null ? femelleAlpha.getNom() : "Aucune"));

        System.out.println("\nMembres de la meute :");
        for (Lycanthrope2 membre : membres) {
            membre.afficherCaracteristiques();
            System.out.println("-----------------------");
        }
    }

    /**
     * Ajoute un lycanthrope à la meute après vérification.
     *
     * @param lycanthrope Le lycanthrope à ajouter.
     * @throws IllegalArgumentException Si le lycanthrope est déjà un alpha, mort ou si la meute est dissoute.
     */
    public void ajouterLycanthrope(Lycanthrope2 lycanthrope) {
        if (dissoute) {
            throw new IllegalStateException("Impossible d'ajouter un lycanthrope à une meute dissoute.");
        }

        if (lycanthrope.isMort()) {
            throw new IllegalArgumentException("Un lycanthrope mort ne peut pas être ajouté à une meute.");
        }

        if (lycanthrope.getRangHierarchie() == RangHierarchie.α) {
            throw new IllegalArgumentException("Un alpha doit être intégré via promotion, pas directement ajouté.");
        }

        membres.add(lycanthrope);
        lycanthrope.setMeute(this); // Mise à jour de la référence de meute
        taille++;
        declarerLycanthropesOmega(); // Met à jour les omega après chaque ajout
    }

    /**
     * Retire un lycanthrope de la meute.
     *
     * @param lycanthrope Le lycanthrope à retirer.
     */
    public void enleverLycanthrope(Lycanthrope2 lycanthrope) {
        membres.remove(lycanthrope);
        lycanthrope.setMeute(null); // Supprime la référence de meute
        taille--;

        if (lycanthrope == maleAlpha || lycanthrope == femelleAlpha) {
            redefinirCoupleAlpha();
        }
    }

    /**
     * Déclare un lycanthrope comme mort, en mettant à jour la meute si nécessaire.
     *
     * @param membre Le lycanthrope à déclarer mort.
     */
    public void declarerMort(Lycanthrope2 membre) {
        membre.mourir();
        enleverLycanthrope(membre);
    }

    /**
     * Réorganise le couple alpha si l'un des alpha est retiré ou décédé.
     */
    public void redefinirCoupleAlpha() {
        Lycanthrope2 nouveauMaleAlpha = null;
        Lycanthrope2 nouvelleFemelleAlpha = null;

        for (Lycanthrope2 membre : membres) {
            if (!membre.isMort() && membre.getCategorieAge() == CategorieAge.ADULTE && membre.getRangHierarchie() != RangHierarchie.ω) {
                if (!membre.isSexe() && (nouveauMaleAlpha == null || membre.getForce() > nouveauMaleAlpha.getForce())) {
                    nouveauMaleAlpha = membre;
                }
                if (membre.isSexe() && (nouvelleFemelleAlpha == null || membre.getForce() > nouvelleFemelleAlpha.getForce())) {
                    nouvelleFemelleAlpha = membre;
                }
            }
        }

        // Promotion des nouveaux alpha
        if (nouveauMaleAlpha != null) nouveauMaleAlpha.setRangHierarchie(RangHierarchie.α);
        if (nouvelleFemelleAlpha != null) nouvelleFemelleAlpha.setRangHierarchie(RangHierarchie.α);

        this.maleAlpha = nouveauMaleAlpha;
        this.femelleAlpha = nouvelleFemelleAlpha;

        if (maleAlpha == null || femelleAlpha == null) {
            dissoudreMeute();
        }
    }

    /**
     * Dissout la meute en vidant la liste des membres et en réinitialisant les attributs.
     */
    public void dissoudreMeute() {
        for (Lycanthrope2 membre : membres) {
            membre.setMeute(null);
        }
        membres.clear();
        taille = 0;
        maleAlpha = null;
        femelleAlpha = null;
        dissoute = true;
    }

    /**
     * Permet la reproduction du couple alpha.
     */
    public void reproduire() {
        if (!saisonDesAmours || maleAlpha == null || femelleAlpha == null || maleAlpha.isMalade() || femelleAlpha.isMalade()) {
            System.out.println("Reproduction impossible. Vérifiez la saison, la santé ou la présence du couple alpha.");
            return;
        }

        int nombreDeJeunes = (int) (Math.random() * 7) + 1; // 1 à 7 jeunes
        boolean rangBetaOccupe = membres.stream().anyMatch(m -> m.getRangHierarchie() == RangHierarchie.β);

        for (int i = 0; i < nombreDeJeunes; i++) {
            jeune++; // Incrémentation du compteur global

            RangHierarchie rangInitial = RangHierarchie.γ; // Par défaut, les jeunes sont rang γ
            if (!rangBetaOccupe && i == 0) {
                rangInitial = RangHierarchie.β; // Si β est vacant, le premier jeune prend ce rang
                rangBetaOccupe = true; // Le rang β est désormais occupé
            }

            // Création d'un jeune avec un nom unique
            Lycanthrope2 nouveauJeune = new Lycanthrope2(
                    "Jeune " + jeune,
                    Math.random() > 0.5, // Sexe aléatoire
                    CategorieAge.JEUNE,
                    50, // Force initiale
                    rangInitial,
                    0.2 // Facteur d'impétuosité initial
            );

            ajouterLycanthrope(nouveauJeune); // Ajout du jeune à la meute
        }

        System.out.println("Reproduction réussie ! " + nombreDeJeunes + " jeunes ont été ajoutés.");
    }

    /**
     * Déclare les lycanthropes omega en fonction de leur force.
     */
    public void declarerLycanthropesOmega() {
        for (Lycanthrope2 membre : membres) {
            if (membre.getForce() < seuilForceOmega) {
                membre.setRangHierarchie(RangHierarchie.ω);
            }
        }
    }


    /**
     * Gère les conflits hiérarchiques entre deux lycanthropes.
     * Le perdant peut perdre son rang.
     *
     * @param defiant Le lycanthrope qui initie le conflit.
     * @param cible   Le lycanthrope défié.
     */
    public void gererConflitsHierarchiques(Lycanthrope2 defiant, Lycanthrope2 cible) {
        if (defiant.isMort() || cible.isMort()) {
            System.out.println("Un lycanthrope mort ne peut pas participer à un conflit.");
            return;
        }

        if (defiant.getMeute() != this || cible.getMeute() != this) {
            System.out.println("Les deux lycanthropes doivent appartenir à la même meute.");
            return;
        }

        System.out.println(defiant.getNom() + " défie " + cible.getNom() + " pour un conflit hiérarchique !");
        if (defiant.getForce() + defiant.getFacteurDomination() > cible.getForce() + cible.getFacteurDomination()) {
            System.out.println(defiant.getNom() + " remporte le conflit !");
            defiant.setRangHierarchie(cible.getRangHierarchie());
            cible.setRangHierarchie(cible.decrementerRang(cible.getRangHierarchie()));
        } else {
            System.out.println(cible.getNom() + " défend son rang avec succès !");
            defiant.setFacteurDomination(defiant.getFacteurDomination() - 1);
        }
    }


    /**
     * Met à jour l'âge et les caractéristiques de chaque membre de la meute.
     */
    public void mettreAJourAgeEtCaracteristiques() {
        for (Lycanthrope2 membre : membres) {
            if (membre.isMort()) continue;

            // Transition d'âge
            if (membre.getCategorieAge() == CategorieAge.JEUNE) {
                membre.setCategorieAge(CategorieAge.ADULTE);
                membre.setForce(membre.getForce() + 10); // Gain de force à l'âge adulte
            } else if (membre.getCategorieAge() == CategorieAge.ADULTE) {
                membre.setCategorieAge(CategorieAge.VIEUX);
                membre.setForce(Math.max(0, membre.getForce() - 5)); // Perte de force en vieillissant
            }
            System.out.println(membre.getNom() + " est maintenant " + membre.getCategorieAge() + ".");
        }
    }




    /**
     * Les membres de la meute hurlent collectivement en fonction d'un type de hurlement.
     *
     * @param type Le type de hurlement (enum TypeHurlement).
     */
    public void hurlementCollectif(TypeHurlement type) {
        System.out.println("La meute commence un hurlement collectif : " + type);
        for (Lycanthrope2 membre : membres) {
            if (!membre.isMort() && !membre.isMalade()) {
                membre.hurler(type);
            }
        }
    }




    /**
     * Gère une interaction entre cette meute et une autre meute.
     *
     * @param autreMeute La meute avec laquelle interagir.
     * @param typeInteraction Le type d'interaction (alliance ou rivalité).
     */
    public void interactionEntreMeutes(Meute autreMeute, String typeInteraction) {
        if (autreMeute == null || autreMeute.getTaille() == 0) {
            System.out.println("Interaction impossible avec une meute inexistante ou vide.");
            return;
        }

        System.out.println("Interaction entre les meutes : " + typeInteraction);
        switch (typeInteraction.toLowerCase()) {
            case "alliance":
                System.out.println("Les meutes concluent une alliance temporaire.");
                // Implémenter des actions spécifiques liées à l'alliance
                break;

            case "rivalité":
                System.out.println("Les meutes entrent en conflit !");
                // Implémenter des actions spécifiques liées à la rivalité
                break;

            default:
                System.out.println("Type d'interaction non reconnu.");
        }
    }



    /**
     * Rétablit une meute dissoute avec un nouveau couple alpha.
     *
     * @param nouveauMaleAlpha    Le nouveau mâle alpha.
     * @param nouvelleFemelleAlpha La nouvelle femelle alpha.
     */
    public void remettreEnVigueur(Lycanthrope2 nouveauMaleAlpha, Lycanthrope2 nouvelleFemelleAlpha) {
        if (!dissoute) {
            throw new IllegalStateException("La meute n'est pas dissoute. Impossible de la rétablir.");
        }

        if (nouveauMaleAlpha.getRangHierarchie() != RangHierarchie.α || nouvelleFemelleAlpha.getRangHierarchie() != RangHierarchie.α) {
            throw new IllegalArgumentException("Les deux membres du nouveau couple doivent avoir le rang α.");
        }

        if (nouveauMaleAlpha.isSexe() || !nouvelleFemelleAlpha.isSexe()) {
            throw new IllegalArgumentException("Le couple alpha doit être composé d'un mâle et d'une femelle.");
        }

        if (nouveauMaleAlpha.getCategorieAge() != CategorieAge.ADULTE || nouvelleFemelleAlpha.getCategorieAge() != CategorieAge.ADULTE) {
            throw new IllegalArgumentException("Les membres du couple alpha doivent être adultes.");
        }

        // Réinitialisation de la meute
        this.maleAlpha = nouveauMaleAlpha;
        this.femelleAlpha = nouvelleFemelleAlpha;
        this.membres = new ArrayList<>();
        this.membres.add(nouveauMaleAlpha);
        this.membres.add(nouvelleFemelleAlpha);
        this.taille = 2;
        this.dissoute = false;

        nouveauMaleAlpha.setMeute(this);
        nouvelleFemelleAlpha.setMeute(this);
        System.out.println("La meute a été rétablie avec succès !");
    }




    // Getters et Setters
    public Lycanthrope2 getMaleAlpha() {
        return maleAlpha;
    }

    public Lycanthrope2 getFemelleAlpha() {
        return femelleAlpha;
    }

    public List<Lycanthrope2> getMembres() {
        return membres;
    }

    public int getTaille() {
        return taille;
    }

    public boolean isSaisonDesAmours() {
        return saisonDesAmours;
    }

    public void mettreAJourSaisonDesAmours(boolean estSaison) {
        this.saisonDesAmours = estSaison;
    }

    public int getSeuilForceOmega() {
        return seuilForceOmega;
    }

    /**
     * Indique si la meute est dissoute.
     *
     * @return true si la meute est dissoute, false sinon.
     */
    public boolean isDissoute() {
        return dissoute;
    }


    /**
     * Indique le nombre de Lycantrope2 malde
     *
     * @return le nombre de malade dans la Meute
     */
    public int getNombreMalades(){
        int nombreMalades = 0;
        for(Lycanthrope2 membre : membres){
            if (membre.isMalade()){
                nombreMalades++;
            }
        }
        return nombreMalades;
    }

}
