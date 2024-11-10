package Creatures.MortVivantes;

import Creatures.Creature;
import Creatures.Demoralisantes.Demoralisante;

public class Zombie extends Creature implements MortVivant {
    public Zombie(String nom, String sexe, float poids, float taille, int age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }

    @Override
    public void regenerer() {

    }
}
