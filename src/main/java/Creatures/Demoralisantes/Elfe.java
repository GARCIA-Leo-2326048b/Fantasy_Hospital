package Creatures.Demoralisantes;

import Creatures.Age;
import Creatures.Creature;

import java.util.List;

public class Elfe extends Creature implements Demoralisante {
    public Elfe(String nom, String sexe, float poids, float taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }



    @Override
    public void demoraliser(List<Creature> autresCreatures) {

    }
}
