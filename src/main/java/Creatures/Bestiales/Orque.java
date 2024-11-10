package Creatures.Bestiales;

import Creatures.Creature;

public class Orque extends Creature implements Bestiale {

    public Orque(String nom,String sexe,float poids,float taille,int age,int moral) {
        super(nom, sexe, poids, taille, age,moral);
    }


    @Override
    public void contaminer(Creature autreCreature) {

    }
}