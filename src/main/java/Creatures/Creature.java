package Creatures;

import Maladies.Maladie;

public abstract class  Creature {

    private String nom;
    private String sexe;
    private float poids;
    private float taille;
    private int age;
    private int moral;
    private Maladie[] maladies;


    public Creature (String nom,String sexe,float poids,float taille,int age,int moral,Maladie[] maladies) {
        this.nom = nom;
        this.age=age;
        this.sexe = sexe;
        this.moral = moral;
        this.maladies = maladies;
        this.poids = poids;
        this.taille = taille;

    }

    public String getSexe() {
        return sexe;
    }

    public float getTaille() {
        return taille;
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public int getAge() {
        return age;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public String getNom() {
        return nom;
    }


    public Maladie[] getMaladies() {
        return maladies;
    }

    public void setMaladies(Maladie[] maladies) {
        this.maladies = maladies;
    }

    public void attendre(){

    }

    public void hurler(){

    }
    public void seEmporter(){

    }
    public void tomberMalade(){

    }
    public void guerir(){

    }
    public void trepasser(){

    }
}
