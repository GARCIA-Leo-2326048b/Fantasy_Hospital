package Creatures.Bestiales;

import Creatures.Creature;

public class HommeBete extends Creature implements Bestiale{

    public HommeBete(String nom, String sexe, float poids, float taille, int age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }

    @Override
    public void contaminer(Creature autreCreature) {

    }
}