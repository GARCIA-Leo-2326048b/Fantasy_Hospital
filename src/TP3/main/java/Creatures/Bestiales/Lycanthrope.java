package Creatures.Bestiales;

import Creatures.Age;
import Creatures.Creature;
import Creatures.Triage.Triage;

public class Lycanthrope extends Creature implements Bestiale, Triage {

    public Lycanthrope(String nom, String sexe, double poids, double taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }

    public Lycanthrope(String nom, String sexe, Age age) {
        super(nom,sexe,age);
    }

}