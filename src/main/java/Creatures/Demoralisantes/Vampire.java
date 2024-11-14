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





}
