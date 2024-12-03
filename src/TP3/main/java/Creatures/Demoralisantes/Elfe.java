package Creatures.Demoralisantes;

import Creatures.Age;
import Creatures.Creature;
import Creatures.VIP.VIP;

public class Elfe extends Creature implements Demoralisante, VIP {

    public Elfe(String nom, String sexe, double poids, double taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }

    public Elfe(String nom, String sexe, Age age) {
        super(nom,sexe,age);
    }
}
