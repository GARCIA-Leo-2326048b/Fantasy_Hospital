package Lycanthrope;

import Meute.Meute;

/**
 * La classe Lycanthrope2 possède des caractéristiques spécifiques
 * et des comportements adaptés à sa hiérarchie sociale et ses interactions au sein
 * d'une meute.
 */
public class Lycanthrope2 {

    // Attributs
    private String nom; // Nom du lycanthrope
    private boolean sexe; // false : homme, true : femelle
    private CategorieAge categorieAge; // Catégorie d'âge (jeune, adulte, vieux)
    private int force; // La force physique du lycanthrope
    private int facteurDomination; // Différence entre dominations exercées et subies
    private RangHierarchie rangHierarchie; // Rang hiérarchique (alphabet grec)
    private double niveau; // Niveau global du lycanthrope
    private double facteurImpetuosite; // Tendance à l'agressivité
    private boolean estMalade; // Indique si le lycanthrope est malade
    private Meute meute; // Référence à la meute actuelle
    private boolean estMort; // Indique si le Lycantrope est en vie ou non
    /**
     * Constructeur de la classe Lycanthrope2.
     *
     * @param nom               Le nom du lycanthrope.
     * @param sexe              Le sexe du lycanthrope (false : homme, true : femelle).
     * @param categorieAge      La catégorie d'âge (enum CategorieAge).
     * @param force             La force physique du lycanthrope.
     * @param rangHierarchie    Le rang hiérarchique (enum RangHierarchie).
     * @param facteurImpetuosite Le facteur d'impétuosité (tendance à l'agressivité).
     */
    public Lycanthrope2(String nom, boolean sexe, CategorieAge categorieAge, int force, RangHierarchie rangHierarchie, double facteurImpetuosite) {
        this.nom = nom;
        this.sexe = sexe;
        this.categorieAge = categorieAge;
        this.force = force;
        this.rangHierarchie = rangHierarchie;
        this.facteurDomination = 0; // Initialisé à 0
        this.niveau = calculerNiveau();
        this.facteurImpetuosite = facteurImpetuosite;
        this.estMalade = false; // Par défaut, pas malade
        this.meute = null; // Par défaut, pas de meute
        this.estMort = false;
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
        System.out.println("Appartient à une meute : " + (meute != null ? "Oui" : "Non"));
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
                if (rangHierarchie != RangHierarchie.ω) {
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
     * @param hurlement Le contenu ou type du hurlement entendu.
     */
    public void entendreHurlement(TypeHurlement hurlement) {
        if (estMalade) {
            System.out.println(this.nom + " est malade et ne réagit pas au hurlement.");
            return;
        }

        switch (hurlement) {
            case APPARTENANCE:
                System.out.println(this.nom + " entend un hurlement d'appartenance et répond en hurlant à son tour.");
                hurler(TypeHurlement.APPARTENANCE);
                break;
            case DOMINATION:
                System.out.println(this.nom + " entend un hurlement de domination. Cela le rend agressif.");
                hurler(TypeHurlement.AGRESSIVITE);
                break;
            case SOUMISSION:
                System.out.println(this.nom + " entend un hurlement de soumission et reste neutre.");
                break;
            case AGRESSIVITE:
                System.out.println(this.nom + " entend un hurlement agressif et se prépare à répondre.");
                hurler(TypeHurlement.AGRESSIVITE);
                break;
            default:
                System.out.println(this.nom + " ne reconnaît pas le type de hurlement.");
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
     * Tente de dominer un autre lycanthrope.
     *
     * @param cible Le lycanthrope ciblé.
     * @return true si la domination réussit, false sinon.
     */
    public boolean tenterDomination(Lycanthrope2 cible) {
        if (this.rangHierarchie == RangHierarchie.ω) {
            System.out.println(this.nom + " ne peut pas dominer car il est omega.");
            return false;
        }

        if (cible.rangHierarchie == RangHierarchie.α && cible.sexe) {
            System.out.println("La femelle α " + cible.nom + " ne peut pas être dominée.");
            return false;
        }

        if (this.force + this.facteurImpetuosite < cible.force) {
            System.out.println(this.nom + " échoue à dominer " + cible.nom + " : force insuffisante.");
            cible.reagirAgression();
            this.facteurDomination -= 1;
            return false;
        }

        System.out.println(this.nom + " domine " + cible.nom + " avec succès !");
        this.facteurDomination += 1;
        cible.facteurDomination -= 1;

        RangHierarchie tempRang = this.rangHierarchie;
        this.rangHierarchie = cible.rangHierarchie;
        cible.rangHierarchie = tempRang;

        return true;
    }

    /**
     * Vérifie si le lycanthrope doit perdre un rang à cause d'un faible facteur de domination.
     */
    public void verifierPerteDeRang() {
        if (this.meute == null) {
            System.out.println(this.nom + " n'appartient à aucune meute, vérification impossible.");
            return;
        }

        if (this.facteurDomination < -5) {
            boolean dernierDeSonRang = this.meute.getMembres().stream()
                    .filter(l -> l.getRangHierarchie() == this.rangHierarchie && l.isSexe() == this.sexe)
                    .count() == 1;

            if (!dernierDeSonRang) {
                System.out.println(this.nom + " perd un rang par manque de domination.");
                this.rangHierarchie = decrementerRang(this.rangHierarchie);
                this.facteurDomination = 0; // Réinitialisation
            } else {
                System.out.println(this.nom + " est le dernier de son rang et ne peut pas le perdre.");
            }
        }
    }

    /**
     * Transforme le lycanthrope en humain.
     */
    public void seTransformerEnHumain() {
        System.out.println(this.nom + " tente de se transformer en humain...");
        if (niveau > 70) {
            System.out.println("Transformation réussie !");
            if (meute != null) {
                meute = null;
                System.out.println(this.nom + " quitte la meute.");
            }
        } else {
            System.out.println("Transformation partielle, reste dans la meute.");
        }
    }

    /**
     * Calcule le niveau global du lycanthrope.
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
    public RangHierarchie decrementerRang(RangHierarchie rang) {
        if (rang == RangHierarchie.ω) {
            return RangHierarchie.ω;
        }
        int rangIndex = rang.ordinal();
        return RangHierarchie.values()[rangIndex + 1];
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isSexe() {
        return sexe;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public CategorieAge getCategorieAge() {
        return categorieAge;
    }

    public void setCategorieAge(CategorieAge categorieAge) {
        this.categorieAge = categorieAge;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getFacteurDomination() {
        return facteurDomination;
    }

    public void setFacteurDomination(int facteurDomination) {
        this.facteurDomination = facteurDomination;
    }

    public RangHierarchie getRangHierarchie() {
        return rangHierarchie;
    }

    public void setRangHierarchie(RangHierarchie rangHierarchie) {
        this.rangHierarchie = rangHierarchie;
    }

    public double getNiveau() {
        return niveau;
    }

    public void setNiveau(double niveau) {
        this.niveau = niveau;
    }

    public double getFacteurImpetuosite() {
        return facteurImpetuosite;
    }

    public void setFacteurImpetuosite(double facteurImpetuosite) {
        this.facteurImpetuosite = facteurImpetuosite;
    }

    /**
     * Fait tomber le lycanthrope malade.
     */
    public void devenirMalade() {
        if (!estMalade) {
            estMalade = true;
            System.out.println(this.nom + " est maintenant malade.");
        } else {
            System.out.println(this.nom + " est déjà malade.");
        }
    }

    /**
     * Guérit le lycanthrope.
     */
    public void guerir() {
        if (estMalade) {
            estMalade = false;
            System.out.println(this.nom + " est maintenant guéri.");
        } else {
            System.out.println(this.nom + " est déjà en bonne santé.");
        }
    }


    public Meute getMeute() {
        return meute;
    }

    public void setMeute(Meute meute) {
        this.meute = meute;
    }



    /**
     * Fait mourir le lycanthrope.
     */
    public void mourir() {
        if (!estMort) {
            estMort = true;
            if (meute != null) {
                meute.enleverLycanthrope(this); // Supprime de la meute
            }
            System.out.println(this.nom + " est décédé.");
        } else {
            System.out.println(this.nom + " est déjà mort.");
        }
    }

    /**
     * Vérifie si le lycanthrope est mort.
     *
     * @return true si le lycanthrope est mort, false sinon.
     */
    public boolean isMort() {
        return estMort;
    }

    /**
     * Vérifie si le lycanthrope est malade.
     *
     * @return true si le lycanthrope est malade, false sinon.
     */
    public boolean isMalade() {
        return estMalade;
    }
}
