package Creatures.MortVivantes;

import Creatures.Age;
import Creatures.Creature;

public class Zombie extends Creature implements MortVivant {
    public Zombie(String nom, String sexe, float poids, float taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }



}
