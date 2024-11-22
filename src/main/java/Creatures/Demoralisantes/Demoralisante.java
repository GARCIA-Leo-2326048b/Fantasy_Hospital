package Creatures.Demoralisantes;

import Creatures.Creature;

import java.util.List;

public interface Demoralisante {

    default void demoraliser(List<Creature> autresCreatures){
        for (Creature creature : autresCreatures) {
            creature.diminuerMoral(10);
        }
    }

}