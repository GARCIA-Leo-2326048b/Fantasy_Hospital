package Creatures;

import Maladies.Maladie;

public abstract class CreatureBestiale extends Creature implements Bestiale {

    public CreatureBestiale(String nom,String sexe,float poids,float taille,int age,int moral) {
        super(nom, sexe, poids, taille, age,moral);
    }

    @Override
    public void contaminer(Creature cible) {
        if (!this.getMaladies().isEmpty()) {
            Maladie maladie = this.getMaladies().get(0);
            cible.tomberMalade(maladie);
            System.out.println(this.getNom() + " contamine " + cible.getNom() + " avec " + maladie);
        }
    }
}
