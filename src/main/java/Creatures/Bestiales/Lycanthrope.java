package Creatures.Bestiales;

import Creatures.Creature;

public class Lycanthrope extends Creature implements Bestiale {

    public Lycanthrope(String nom, String sexe, float poids, float taille, int age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }

    @Override
    public void contaminer(Creature autreCreature) {

    }
}