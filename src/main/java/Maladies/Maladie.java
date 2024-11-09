package Maladies;

public class Maladie {

    private String nomComplet;
    private String nomAbrege;
    private int niveau;
    private final int NIVEAU_MAX;

    public Maladie(String nomComplet, String nomAbrege, int niveau) {
        this.nomComplet = nomComplet;
        this.nomAbrege = nomAbrege;
        this.niveau = niveau;
        this.NIVEAU_MAX = 10;
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

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void augmenterNiveau(){

    }

    public void diminuerNiveau(){

    }

    public void maladieLetal(){

    }
}
