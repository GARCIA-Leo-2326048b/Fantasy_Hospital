package Creatures.MortVivantes;

import Creatures.Creature;

public class Zombie extends Creature implements MortVivant {
    public Zombie(String nom, String sexe, float poids, float taille, int age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }



}
