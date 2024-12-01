package Creatures.Bestiales;

import Creatures.Age;
import Creatures.Creature;
import Creatures.Triage.Triage;

public class Orque extends Creature implements Bestiale, Triage {

    public Orque(String nom, String sexe, double poids, double taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age,moral);
    }
    public Orque(String nom, String sexe, Age age) {
        super(nom,sexe,age);
    }


}