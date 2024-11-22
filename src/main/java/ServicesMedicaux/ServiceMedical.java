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
    private TypeCreature typeCreature;


    public ServiceMedical(String nom, double superficie,  Budget budget, TypeCreature typeCreature) {
        this.nom = nom;
        this.superficie = superficie;
        this.CAPACITE_MAX = 15;
        this.creatures = new ArrayList<>();
        verifierTypeCreatureDansListeCreatures();
        this.budget = budget;
        this.typeCreature = typeCreature;
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
        if (creature.getClass().getSimpleName().equalsIgnoreCase(getTypeCreature().toString())) {
            if (creatures.size() < CAPACITE_MAX ) {
                creatures.add(creature);
                System.out.println(creature.getNom() + " a été ajouté(e) au service " + nom);
            } else {
                System.out.println("Le service " + nom + " est plein !");
            }
        }
    }

    public void enleverCreature(Creature creature) {
        creatures.remove(creature);
        System.out.println(creature.getNom() + " a été retiré(e) du service " + nom);
    }

    public void soignerCreatures(Maladie maladie) {
        for (Creature creature : creatures) {
            creature.guerir(maladie);
            try {
                Thread.sleep(1000); // Pause d'1 seconde
            } catch (InterruptedException e) {
                System.out.println("Pause interrompue !");
            }
        }
    }

    public void afficherCaracteristiques() {
        System.out.println("Service : " + nom + "Type de créature : " + typeCreature + "Superficie : " + superficie + "m², Capacité : " + CAPACITE_MAX);
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

    public TypeCreature getTypeCreature() {
        return typeCreature;
    }

    public void setTypeCreature(TypeCreature typeCreature) {
        this.typeCreature = typeCreature;
    }

    public void verifierTypeCreatureDansListeCreatures() {
        for (Creature creature : creatures) {
            if(!creature.getClass().getSimpleName().equalsIgnoreCase(getTypeCreature().toString())){
                throw new IllegalArgumentException("Il y a une créature d'un type non-correspondant à celui du service médical.");
            }
        }
    }
}
