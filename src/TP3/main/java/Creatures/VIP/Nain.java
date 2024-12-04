package Creatures.VIP;

import Creatures.Age;
import Creatures.Creature;

public class Nain extends Creature implements VIP{

    public Nain(String nom, String sexe, double poids, double taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }
    public Nain(String nom, String sexe, Age age) {
        super(nom,sexe,age);
    }
}
