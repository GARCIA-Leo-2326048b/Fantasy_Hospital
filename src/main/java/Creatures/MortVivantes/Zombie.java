package Creatures.MortVivantes;

import Creatures.Age;
import Creatures.Creature;
import Creatures.Triage.Triage;

public class Zombie extends Creature implements MortVivant, Triage {
    public Zombie(String nom, String sexe, double poids, double taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }
    public Zombie(String nom, String sexe, Age age) {
        super(nom,sexe,age);
    }


}
