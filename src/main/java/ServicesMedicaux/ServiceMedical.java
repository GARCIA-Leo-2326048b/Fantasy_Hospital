package ServicesMedicaux;

import Creatures.Creature;
import Creatures.TypeCreature;
import Maladies.Maladie;

import java.util.ArrayList;
import java.util.List;

public class ServiceMedical {
    private String nom;
    private double superficie;
    private final int CAPACITE_MAX;
    private List<Creature> creatures;
    private Budget budget;


    public ServiceMedical(String nom, double superficie,  Budget budget) {
        this.nom = nom;
        this.superficie = superficie;
        this.CAPACITE_MAX = 15;
        this.creatures = new ArrayList<>();
        this.budget = budget;
    }

    public String getNom() {
        return nom;
    }

    public double getSuperficie() {
        return superficie;
    }

    public int getCAPACITE_MAX() {
        return CAPACITE_MAX;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public Budget getBudget() {
        return budget;
    }

    public void ajouterCreature(Creature creature) {
        if (creatures.size() < CAPACITE_MAX ) {
            creatures.add(creature);
            System.out.println(creature.getNom() + " a été ajouté(e) au service " + nom);
        } else {
            System.out.println("Le service " + nom + " est plein !");
        }
    }

    public void enleverCreature(Creature creature) {
        creatures.remove(creature);
        System.out.println(creature.getNom() + " a été retiré(e) du service " + nom);
    }

    public void soignerCreatures(Maladie maladie) {
        for (Creature creature : creatures) {
            creature.guerir(maladie);
        }
    }

    public void afficherCaracteristiques() {
        System.out.println("Service : " + nom + ", Superficie : " + superficie + "m², Capacité : " + CAPACITE_MAX);
        System.out.println("Budget : " + budget);
        for (Creature creature : creatures) {
            System.out.println(" - " + creature.getNom() + ", Moral : " + creature.getMoral());
            // Parcours de la liste des maladies de chaque créature
            System.out.println("   Maladies :");
            for (Maladie maladie : creature.getMaladies()) {
                System.out.println("     * " + maladie.getNomComplet() + " ( " + maladie.getNomAbrege() + "), Niveau : " + maladie.getNiveau() + "/" + maladie.getNiveauMax());
            }
        }
    }

    public void reviserBudget(Budget nouveauBudget){
        budget = nouveauBudget;
    }
}
