package Creatures;

import Creatures.Bestiales.Bestiale;
import Creatures.Demoralisantes.Demoralisante;
import Creatures.MortVivantes.MortVivant;
import Creatures.Triage.Triage;
import Creatures.VIP.VIP;
import Maladies.Maladie;
import Medecins.Medecin;
import ServicesMedicaux.ServiceMedical;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class  Creature implements Medecin {

    private String nom;
    private String sexe;
    private float poids;
    private float taille;
    private Age age;
    private int moral;
    private List<Maladie> maladies;
    private int nombreHurlement = 0;
    private boolean estMedecin;



    public Creature (String nom,String sexe,float poids,float taille,Age age,int moral) {
        this.nom = nom;
        this.age=age;
        this.sexe = sexe;
        this.moral = moral;
        this.maladies = new ArrayList<>();
        this.poids = poids;
        this.taille = taille;
        this.estMedecin = false;

    }

    public Creature(String nom,String sexe,Age age){
        this.nom = nom;
        this.age=age;
        this.sexe = sexe;
        this.estMedecin = true;
    }

    public int getNombreHurlement() {
        return nombreHurlement;
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

    public Age getAge() {
        return age;
    }

    public float getPoids() {
        return poids;
    }

    public void setNombreHurlement(int nombreHurlement) {
        this.nombreHurlement = nombreHurlement;
    }

    public String getNom() {
        return nom;
    }



    public List<Maladie> getMaladies() {
        return maladies;
    }

    public boolean estMedecin() {
        return estMedecin;
    }



    public void diminuerMoral( int points){
        moral -= points;
        if (moral < 0) moral = 0;
    }


    public void attendre(ServiceMedical service){
        if(this instanceof Triage && service.getCreatures().size()>1){
            diminuerMoral(5);
        } else if (this instanceof VIP) {
            diminuerMoral(20);
        }
        else {
            diminuerMoral(10);
        }
        System.out.println(nom + " attend, le moral diminue à " + moral + ".");
    }

    public void hurler(){
        System.out.println(nom + " hurle de désespoir !");
        nombreHurlement ++;
    }
    public void sEmporter(){

        System.out.println(nom + " s'emporte de rage !");
    }
    public void tomberMalade(Maladie maladie){
        if (estMedecin()) {
            throw new IllegalStateException(nom + " est un médecin et ne peut pas tomber malade.");
        }
        maladies.add(maladie);

    }
    public void guerir(Maladie maladie){
        maladies.remove(maladie);
        moral += 10;
        if (moral > 100) moral = 100;
        System.out.println(nom + " a été soigné de " + maladie.getNomComplet() + ". Moral: " + moral);

    }
    public void contaminer(Maladie maladie, List<Creature> autreCreatures){
        Random random = new Random();

        // Choisir une victime aléatoire
        Creature victime = autreCreatures.get(random.nextInt(autreCreatures.size()));

        // Contaminer la victime
        victime.tomberMalade(maladie);
        System.out.println(this.nom + " a contaminé " + victime.getNom() + " de " + maladie.getNomComplet());
    }


    public void trepasser(Maladie maladie, ServiceMedical service) {
        System.out.println(nom + "trepasse !");
        service.enleverCreature(this);

        if (this instanceof Demoralisante) {
            ((Demoralisante) this).demoraliser(service.getCreatures());
        }
        if (this instanceof Bestiale) {
            this.contaminer(maladie, service.getCreatures());
        }
        if (this instanceof MortVivant) {
            ((MortVivant) this).regenerer(this, service);
        }
    }
}
