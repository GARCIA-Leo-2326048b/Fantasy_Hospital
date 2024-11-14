package Creatures.Demoralisantes;

import Creatures.Creature;
import Maladies.Maladie;
import ServicesMedicaux.ServiceMedical;

import java.util.List;

public interface Demoralisante {

    default void demoraliser(List<Creature> autresCreatures){
        for (Creature autreCreature : autresCreatures) {
            autreCreature.diminuerMoral();
        }
    }

    default void trepasser(Creature creature, Maladie maladie, ServiceMedical service){
        System.out.println(creature.getNom() +"trepasse !");
        service.enleverCreature(creature);

        demoraliser( service.getCreatures());
    }
}