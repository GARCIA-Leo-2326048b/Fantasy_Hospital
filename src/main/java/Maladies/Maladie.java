package Maladies;

public class Maladie {

    private String nomComplet;
    private String nomAbrege;
    private int niveau;
    private final int NIVEAU_MAX;

    public Maladie(String nomComplet, String nomAbrege) {
        this.nomComplet = nomComplet;
        this.nomAbrege = nomAbrege;
        this.niveau = 0;
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
