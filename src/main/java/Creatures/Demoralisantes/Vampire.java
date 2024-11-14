package Creatures.Demoralisantes;

import Creatures.Age;
import Creatures.Bestiales.Bestiale;
import Creatures.Creature;
import Creatures.MortVivantes.MortVivant;

import java.util.List;

public class Vampire extends Creature implements Demoralisante, Bestiale, MortVivant {
    public Vampire(String nom, String sexe, float poids, float taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }

    // Implémentation de la méthode demoraliser de l'interface Demoralisante
    @Override
    public void demoraliser(List<Creature> autresCreatures) {

    }


    @Override
    public void contaminer(Creature autreCreature) {

    }

    @Override
    public void regenerer() {

    }
}
