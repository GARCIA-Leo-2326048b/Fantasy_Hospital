package Creatures.VIP;

import Creatures.Age;
import Creatures.Creature;

public class Nain extends Creature implements VIP{

    public Nain(String nom, String sexe, float poids, float taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }
}
