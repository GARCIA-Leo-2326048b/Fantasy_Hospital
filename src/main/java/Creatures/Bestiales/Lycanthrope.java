package Creatures.Bestiales;

import Creatures.Creature;
import Maladies.Maladie;
import ServicesMedicaux.ServiceMedical;

public class Lycanthrope extends Creature implements Bestiale {

    public Lycanthrope(String nom, String sexe, float poids, float taille, int age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }


}