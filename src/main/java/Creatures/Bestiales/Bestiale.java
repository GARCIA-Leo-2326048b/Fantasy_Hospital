package Creatures.Bestiales;

import Creatures.Creature;
import Maladies.Maladie;
import ServicesMedicaux.ServiceMedical;

import java.security.Provider;

public interface Bestiale {

    default void contaminer(Maladie maladie, Creature autreCreature){
        autreCreature.getMaladies().add(maladie);
    }



}
