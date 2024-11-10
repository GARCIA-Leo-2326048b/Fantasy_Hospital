package ServicesMedicaux;

import Creatures.Creature;

import java.util.ArrayList;
import java.util.List;

public class ServiceMedical {
    private String nom;
    private float superficie;
    private final int NB_CREAT_MAX;
    private List<Creature> creatures;
    private int nbCreatures;
    private float budget;

    public ServiceMedical(String nom, float superficie, int nbCreatures, float budget ) {
        this.nom = nom;
        this.superficie = superficie;
        this.NB_CREAT_MAX = 20;
        this.nbCreatures = nbCreatures;
        this.budget = budget;
        this.creatures = new ArrayList<>();
    }



}
