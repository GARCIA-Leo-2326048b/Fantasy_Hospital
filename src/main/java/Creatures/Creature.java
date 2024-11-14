package Creatures;

import Creatures.Bestiales.Bestiale;
import Creatures.Demoralisantes.Demoralisante;
import Creatures.MortVivantes.MortVivant;
import Creatures.Triage.Triage;
import Creatures.VIP.VIP;
import Maladies.Maladie;
import ServicesMedicaux.ServiceMedical;

import java.util.ArrayList;
import java.util.List;

public abstract class  Creature {

    private String nom;
    private String sexe;
    private float poids;
    private float taille;
    private Age age;
    private int moral;
    private List<Maladie> maladies;
    private int nombreHurlement = 0;



    public Creature (String nom,String sexe,float poids,float taille,Age age,int moral) {
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

    public Age getAge() {
        return age;
    }

    public float getPoids() {
        return poids;
    }


    public String getNom() {
        return nom;
    }

    public void diminuerMoral( int points){
        moral -= points;
        if (moral < 0) moral = 0;
    }


    public List<Maladie> getMaladies() {
        return maladies;
    }


    public void attendre(ServiceMedical service){
        if(this instanceof Triage || service.getCreatures().size()>1){
            diminuerMoral(5);
        } else if (this instanceof VIP) {
            diminuerMoral(20);
        }
        else {
            diminuerMoral(10);
        }
        System.out.println(nom + " attend, le moral diminue à " + moral + ".");
        if(moral == 0){hurler();}
    }

    public void hurler(){
        if (moral == 0 && nombreHurlement < 10) {
            System.out.println(nom + " hurle de désespoir !");
            nombreHurlement ++;
        }
        else if (nombreHurlement == 10) {
            nombreHurlement = 0;
            sEmporter();
        }

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
    public void trepasser(Maladie maladie, ServiceMedical service) {
        System.out.println(nom + "trepasse !");
        service.enleverCreature(this);

        if (this instanceof Demoralisante) {
            if (!service.getCreatures().isEmpty()) {
                ((Demoralisante) this).demoraliser(service.getCreatures());
            }
        }
        if (this instanceof Bestiale) {
            Creature victime = service.getCreatures().isEmpty() ? null : service.getCreatures().get(0);
            if (victime != null) {
                ((Bestiale) this).contaminer(maladie, victime);
            }
        }
        if (this instanceof MortVivant) {
            ((MortVivant) this).regenerer(this, service);
        }
    }
}
