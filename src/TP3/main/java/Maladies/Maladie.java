package Maladies;

public class Maladie {

    private String nomComplet;
    private String nomAbrege;
    private int niveau;
    private final int NIVEAU_MAX;

    public Maladie(MaladieType type) {
        this.nomComplet = type.getNomComplet();
        this.nomAbrege = type.getNomAbrege();
        this.niveau = 0;
        this.NIVEAU_MAX = type.getNiveauMax();
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public String getNomAbrege() {
        return nomAbrege;
    }

    public int getNiveau() {
        return niveau;
    }
    public int getNiveauMax() {
        return NIVEAU_MAX;
    }
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void augmenterNiveau(){
        niveau ++;
    }

    public void diminuerNiveau(){
        niveau--;
    }

    public boolean estLetal(){
        return niveau==NIVEAU_MAX;
    }

}
