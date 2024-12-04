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
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class  Creature implements Medecin {

    private String nom;
    private String sexe;
    private double poids;
    private double taille;
    private Age age;
    private int moral;
    private List<Maladie> maladies;
    private int nombreHurlement = 0;
    private boolean estMedecin;



    public Creature (String nom,String sexe,double poids,double taille,Age age,int moral) {
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

    public double getTaille() {
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

    public double getPoids() {
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
            diminuerMoral(10);
        } else if (this instanceof VIP) {
            System.out.println("2");
            diminuerMoral(30);
        }
        else {
            System.out.println("3");
            diminuerMoral(20);
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
        // Vérifie directement si une maladie avec le même nom abrégé existe
        for (Maladie m : this.maladies) {
            if (m.getNomAbrege().equals(maladie.getNomAbrege())) return;
        }
        this.maladies.add(maladie);
        System.out.println(this.nom + " tombe malade !");
    }

    public void guerir(Maladie maladie) {
        // Suppression sûre avec l'Iterator
        Iterator<Maladie> iterator = this.maladies.iterator();
        while (iterator.hasNext()) {
            Maladie m = iterator.next();
            if (m.getNomAbrege().equals(maladie.getNomAbrege())) {
                iterator.remove();
                moral += 10;
                if (moral > 100) moral = 100;
                System.out.println(nom + " a été soigné de " + maladie.getNomComplet() + ". Moral: " + moral);
                return;
            }
        }
    }
    public void contaminer(Maladie maladie, List<Creature> autreCreatures){
        if (autreCreatures.isEmpty()) {
            System.out.println("Aucune créature disponible pour la contamination.");
            return;  // Sortir si la liste est vide
        }
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
