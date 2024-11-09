package ServicesMedicaux;

import Creatures.Creature;

public class ServiceMedical {
    private String nom;
    private float superficie;
    private final int NB_CREAT_MAX;
    private Creature[] creatures;
    private int nbCreatures;
    private float budget;

    public ServiceMedical(String nom, float superficie, int nbCreatures, float budget , Creature[] creatures) {
        this.nom = nom;
        this.superficie = superficie;
        this.NB_CREAT_MAX = 20;
        this.nbCreatures = nbCreatures;
        this.budget = budget;
        this.creatures = creatures;
    }



}
