package Maladies;

// Définition de l'enum pour les types de maladies
public enum MaladieType {
    // Liste des maladies avec leurs caractéristiques
    MDC("Maladie débilitante chronique", "MDC", 10),
    FOMO("Syndrome fear of missing out", "FOMO", 8),
    DRS("Dépendance aux réseaux sociaux", "DRS", 7),
    PEC("Porphyrie érythropoïétique congénitale", "PEC", 6),
    ZPL("Zoopathie paraphrénique lycanthropique", "ZPL", 9);

    // Propriétés des maladies
    private final String nomComplet;
    private final String nomAbrege;
    private final int niveauMax;

    // Constructeur de l'enum
    MaladieType(String nomComplet, String nomAbrege, int niveauMax) {
        this.nomComplet = nomComplet;
        this.nomAbrege = nomAbrege;
        this.niveauMax = niveauMax;
    }

    // Méthodes pour accéder aux propriétés
    public String getNomComplet() {
        return nomComplet;
    }

    public String getNomAbrege() {
        return nomAbrege;
    }

    public int getNiveauMax() {
        return niveauMax;
    }
}
