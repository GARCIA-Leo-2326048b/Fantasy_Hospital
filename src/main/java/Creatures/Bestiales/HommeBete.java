package Creatures.Bestiales;

import Creatures.Age;
import Creatures.Creature;
import Maladies.Maladie;
import ServicesMedicaux.ServiceMedical;

public class HommeBete extends Creature implements Bestiale{

    public HommeBete(String nom, String sexe, float poids, float taille, Age age, int moral) {
        super(nom, sexe, poids, taille, age, moral);
    }



}