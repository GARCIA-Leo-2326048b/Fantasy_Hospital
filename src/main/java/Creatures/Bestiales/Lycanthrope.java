package Creatures.Bestiales;

import Creatures.Age;
import Creatures.Creature;

import java.util.ArrayList;

public class Lycanthrope extends Creature implements Bestiale {
    int force;
    int facteurDomination;
    RangDomination rangDomination;
    float niveau;
    FacteurImpetuosite facteurImpetuosite;
// Creer une classe meute

    public Lycanthrope(String nom, String sexe, float poids, float taille, Age age, int moral, int force, int facteurDomination, RangDomination rangDomination, FacteurImpetuosite facteurImpetuosite ) {
        super(nom, sexe, poids, taille, age, moral);
        this.force = force;
        this.facteurDomination = facteurDomination;
        this.rangDomination = rangDomination;
        this.niveau = age.ordinal() * force * rangDomination.ordinal() * facteurDomination  ;
        this.facteurImpetuosite = facteurImpetuosite;
    }



    public int getForce() {
        return force;
    }


    public int getFacteurDomination() {
        return facteurDomination;
    }



    public RangDomination getRangDomination() {
        return rangDomination;
    }



    public float getNiveau() {
        return niveau;
    }



    public FacteurImpetuosite getFacteurImpetuosite() {
        return facteurImpetuosite;
    }





    public void afficherCaracteristiques() {
        System.out.println("Nom : " + getNom());
        System.out.println("Sexe : " + getSexe());
        System.out.println("Age : " + getAge());
        System.out.println("Poids : " + getPoids());
        System.out.println("Taille : " + getTaille());
        System.out.println("Moral : " + getMoral());
        System.out.println("Force : " + force);
        System.out.println("Facteur de Domination : " + facteurDomination);
        System.out.println("Rang de domination : " + rangDomination);
        System.out.println("Niveau : " + niveau);
        System.out.println("Facteur d'Impétuosité : " + facteurImpetuosite);
        //System.out.println("Meute : " + meute);
    }

    // Creer une methode getMeute()



}