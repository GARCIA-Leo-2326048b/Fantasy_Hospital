package Lycanthrope;

/**
 * La classe Lycanthrope possède des caractéristiques spécifiques
 * et des comportements adaptés à sa hiérarchie sociale et ses interactions au sein
 * d'une meute.
 */
public class Lycanthrope {

    // Attributs
    private String nom; // Nom du lycanthrope
    private boolean sexe; // 0 : homme, 1 : femelle
    private CategorieAge categorieAge; // Catégorie d'âge (jeune, adulte, vieux)
    private int force; // Force physique, minimale pour un lycanthrope classique (autre que ω) est 50
    private int facteurDomination; // Différence entre dominations exercées et subies
    private RangHierarchie rangHierarchie; // Rang dans la hiérarchie (Alphabet grec)
    private double niveau; // Niveau global du lycanthrope
    private double facteurImpetuosite; // Tendance à l'agressivité
    private boolean estMalade; // Indique si le lycanthrope est malade

    /**
     * Constructeur de la classe Lycanthrope.
     *
     * @param nom               Le nom du lycanthrope.
     * @param sexe              Le sexe du lycanthrope (false : homme, true : femelle).
     * @param categorieAge      La catégorie d'âge du lycanthrope (enum CategorieAge).
     * @param force             La force physique du lycanthrope.
     * @param rangHierarchie    Le rang hiérarchique du lycanthrope (enum RangHierarchie).
     * @param facteurImpetuosite Le facteur d'impétuosité (tendance à l'agressivité).
     */
    public Lycanthrope(String nom, boolean sexe, CategorieAge categorieAge, int force, RangHierarchie rangHierarchie, double facteurImpetuosite) {
        this.nom = nom;
        this.sexe = sexe;
        this.categorieAge = categorieAge;
        this.force = force;
        this.rangHierarchie = rangHierarchie;
        this.facteurDomination = 0; // Initialisé à 0
        this.niveau = calculerNiveau();
        this.facteurImpetuosite = facteurImpetuosite;
        this.estMalade = false; // Par défaut, pas malade
    }

    /**
     * Affiche les caractéristiques du lycanthrope.
     */
    public void afficherCaracteristiques() {
        System.out.println("Nom : " + nom);
        System.out.println("Sexe : " + (sexe ? "Femelle" : "Homme"));
        System.out.println("Âge : " + categorieAge);
        System.out.println("Force : " + force);
        System.out.println("Rang : " + rangHierarchie);
        System.out.println("Niveau : " + niveau);
        System.out.println("Facteur Domination : " + facteurDomination);
        System.out.println("Est Malade : " + (estMalade ? "Oui" : "Non"));
    }

    /**
     * Permet au lycanthrope de hurler en fonction du type de hurlement.
     *
     * @param type Le type de hurlement (enum TypeHurlement).
     */
    public void hurler(TypeHurlement type) {
        switch (type) {
            case APPARTENANCE:
                System.out.println("Hurlement : Appartenance à la meute.");
                if (!estMalade) {
                    System.out.println("Le lycanthrope " + this.nom + " exprime son appartenance.");
                } else {
                    System.out.println("Le lycanthrope " + this.nom + " ne peut pas hurler car il est malade.");
                }
                break;

            case DOMINATION:
                System.out.println("Hurlement : Domination.");
                if (rangHierarchie != RangHierarchie.ω && force > 50) {
                    System.out.println("Le lycanthrope " + this.nom + " exprime sa domination !");
                } else {
                    System.out.println("Le lycanthrope " + this.nom + " ne peut pas exprimer sa domination.");
                }
                break;

            case SOUMISSION:
                System.out.println("Hurlement : Soumission.");
                if (facteurDomination < 0) {
                    System.out.println("Le lycanthrope " + this.nom + " exprime sa soumission.");
                } else {
                    System.out.println("Le lycanthrope " + this.nom + " ne se soumet pas.");
                }
                break;

            case AGRESSIVITE:
                System.out.println("Hurlement : Agressivité.");
                if (facteurImpetuosite > 0.7) {
                    System.out.println("Le lycanthrope " + this.nom + " montre son agressivité !");
                } else {
                    System.out.println("Le lycanthrope " + this.nom + " n'est pas assez agressif.");
                }
                break;

            default:
                System.out.println("Type de hurlement inconnu.");
        }
    }

    /**
     * Réagit à un hurlement entendu, si le lycanthrope n'est pas malade.
     *
     * @param hurlement Le contenu du hurlement entendu.
     */
    public void entendreHurlement(String hurlement) {
        if (!estMalade) {
            System.out.println("Le lycanthrope " + this.nom + " entend le hurlement : " + hurlement);
        } else {
            System.out.println("Le lycanthrope " + this.nom + " est malade et ne peut pas entendre.");
        }
    }

    /**
     * Réagit à une tentative de domination échouée.
     */
    public void reagirAgression() {
        this.facteurDomination -= 1;
        System.out.println(this.rangHierarchie + " se montre agressif après une tentative de domination.");
    }

    /**
     * Permet de tenter de dominer un autre lycanthrope.
     *
     * @param cible Le lycanthrope ciblé pour la domination.
     * @return true si la domination est réussie, false sinon.
     */
    public boolean tenterDomination(Lycanthrope cible) {
        if (cible.rangHierarchie == RangHierarchie.α && cible.sexe) {
            System.out.println("La femelle α ne peut pas être dominée.");
            return false;
        }
        if (this.force + this.facteurImpetuosite < cible.force) {
            System.out.println("La cible est trop forte pour être dominée.");
            cible.reagirAgression();
            return false;
        }
        if (this.niveau > cible.niveau || cible.rangHierarchie == RangHierarchie.ω) {
            System.out.println("Domination réussie !");
            this.facteurDomination += 1;
            cible.facteurDomination -= 1;
            RangHierarchie tempRang = this.rangHierarchie;
            this.rangHierarchie = cible.rangHierarchie;
            cible.rangHierarchie = tempRang;
            return true;
        } else {
            System.out.println("Domination échouée. La cible devient agressive !");
            cible.reagirAgression();
            return false;
        }
    }

    /**
     * Vérifie si le lycanthrope doit perdre un rang en fonction de son facteur de domination.
     */
    public void verifierPerteDeRang() {
        if (this.facteurDomination < -5) {
            if (this.rangHierarchie != RangHierarchie.ω) {
                this.rangHierarchie = decrementerRang(this.rangHierarchie);
                System.out.println(this.nom + " perd un rang par manque de domination.");
                this.facteurDomination = 0;
                System.out.println(this.nom + " voit donc son facteur de domination réinitialisé à 0.");
            }
        }
    }

    /**
     * Transforme le lycanthrope en humain avec une chance de quitter la simulation.
     */
    public void seTransformerEnHumain() {
        System.out.println("Le lycanthrope tente de se transformer en humain...");
        if (niveau > 70) {
            System.out.println("Transformation réussie. Le lycanthrope quitte la meute.");
        } else {
            System.out.println("Transformation partielle, reste dans la simulation.");
        }
    }

    /**
     * Calcule le niveau du lycanthrope.
     *
     * @return Le niveau calculé.
     */
    private double calculerNiveau() {
        return force + (facteurDomination * 0.5) + facteurImpetuosite;
    }

    /**
     * Décrémente le rang d'un lycanthrope.
     *
     * @param rang Le rang actuel.
     * @return Le rang décrémenté.
     */
    private RangHierarchie decrementerRang(RangHierarchie rang) {
        if (rang == RangHierarchie.ω) {
            System.out.println(rang + " est déjà au rang le plus bas.");
            return RangHierarchie.ω;
        }
        int rangIndex = rang.ordinal();
        return RangHierarchie.values()[rangIndex + 1];
    }
}
