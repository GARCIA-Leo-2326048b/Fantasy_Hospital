package Creatures.Demoralisantes;

import Creatures.Age;
import Creatures.Bestiales.Bestiale;
import Creatures.Creature;
import Creatures.MortVivantes.MortVivant;
import Creatures.VIP.VIP;

import java.util.List;

public class Vampire extends Creature implements Demoralisante, Bestiale, MortVivant, VIP {

    public Vampire(String nom, String sexe, double poids, double taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }
    public Vampire(String nom, String sexe, Age age) {
        super(nom,sexe,age);
    }




}
