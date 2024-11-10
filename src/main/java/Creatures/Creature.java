package Creatures;

import Maladies.Maladie;

import java.util.ArrayList;
import java.util.List;

public abstract class  Creature {

    private String nom;
    private String sexe;
    private float poids;
    private float taille;
    private int age;
    private int moral;
    private List<Maladie> maladies;


    public Creature (String nom,String sexe,float poids,float taille,int age,int moral) {
        this.nom = nom;
        this.age=age;
        this.sexe = sexe;
        this.moral = moral;
        this.maladies = new ArrayList<>();
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


    public List<Maladie> getMaladies() {
        return maladies;
    }


    public void attendre(){
        moral -= 10;
        if (moral < 0) moral = 0;
        System.out.println(nom + " attend, le moral diminue à " + moral + ".");

    }

    public void hurler(){

        System.out.println(nom + " hurle de désespoir !");


    }
    public void sEmporter(){

        System.out.println(nom + " s'emporte de rage !");

    }
    public void tomberMalade(Maladie maladie){
        maladies.add(maladie);

    }
    public void guerir(Maladie maladie){
        maladies.remove(maladie);
        moral += 10;
        if (moral > 100) moral = 100;
        System.out.println(nom + " a été soigné de " + maladie.getNomComplet() + ". Moral: " + moral);

    }
    public void trepasser(){

    }

    public void actionCreature(Maladie maladie){
        //gere les actions des cratures
        if (moral == 0){
            hurler();
        }
        if(maladies.size() > 5){
            trepasser();
        }
        if(maladies.size() < 5){
            tomberMalade(maladie);
        }
        //

    }
}
