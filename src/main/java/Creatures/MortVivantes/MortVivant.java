package Creatures.MortVivantes;

import Creatures.Creature;

public interface MortVivant {

    default void regenerer(Creature creature){

        creature.setMoral(100);
        System.out.println(creature.getNom() + " se régénère et retrouve un moral élevé !");

        // Suppression des maladies
        creature.getMaladies().clear();
        System.out.println(creature.getNom() + " a guéri toutes ses maladies !");
    }
}