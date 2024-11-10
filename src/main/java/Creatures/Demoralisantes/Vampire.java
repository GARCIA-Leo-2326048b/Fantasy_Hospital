package Creatures.Demoralisantes;

import Creatures.Bestiales.Bestiale;
import Creatures.Creature;
import Creatures.MortVivantes.MortVivant;

public class Vampire extends Creature implements Demoralisante, Bestiale, MortVivant {
    public Vampire(String nom, String sexe, float poids, float taille, int age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }

    // Implémentation de la méthode demoraliser de l'interface Demoralisante
    @Override
    public void demoraliser(Creature autreCreature) {

    }


    @Override
    public void contaminer(Creature autreCreature) {

    }

    @Override
    public void regenerer() {

    }
}
