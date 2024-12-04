package TestColonie;

import Colonie.*;
import Lycanthrope.*;
import Meute.Meute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test JUnit pour la classe {@link Colonie}
 * Cette classe teste toutes les méthodes de la classe Colonie, sauf les getters et setters
 * Certains tests ne peuvent pas être vérifié directement, mais on vérifie l'affichage dans la console
 */
public class TestColonie {

    private Colonie colonie;
    private Meute meute1;
    private Meute meute2;
    private Lycanthrope2 lycanthropeSansMeute;

    @BeforeEach
    public void setUp() {
        // Initialisation de la colonie
        colonie = new Colonie("Colonie Alpha");

        // Création des meutes
        Lycanthrope2 maleAlpha1 = new Lycanthrope2(
                "Male Alpha 1",
                false,
                CategorieAge.ADULTE,
                80,
                RangHierarchie.α,
                0.6);

        Lycanthrope2 femelleAlpha1 = new Lycanthrope2(
                "Femelle Alpha 1",
                true,
                CategorieAge.ADULTE,
                70,
                RangHierarchie.α,
                0.7);

        meute1 = new Meute(maleAlpha1, femelleAlpha1, 30);

        Lycanthrope2 maleAlpha2 = new Lycanthrope2(
                "Male Alpha 2",
                false,
                CategorieAge.ADULTE,
                75,
                RangHierarchie.α,
                0.5);

        Lycanthrope2 femelleAlpha2 = new Lycanthrope2(
                "Femelle Alpha 2",
                true,
                CategorieAge.ADULTE,
                65,
                RangHierarchie.α,
                0.6);

        meute2 = new Meute(maleAlpha2, femelleAlpha2, 30);

        // Ajout des meutes dans la colonie

        colonie.ajouterMeute(meute1);
        colonie.ajouterMeute(meute2);

        // Création d'un lycanthrope sans meute
        lycanthropeSansMeute = new Lycanthrope2(
                "Lycan Solitaire",
                false,
                CategorieAge.ADULTE,
                40,
                RangHierarchie.γ,
                0.4);

        // Ajout du lycanthrope solitaire à la colonie
        colonie.ajouterLycanthropeSansMeute(lycanthropeSansMeute);
    }

    @Test
    public void testAjouterMeute() {
        Meute nouvelleMeute = new Meute(
                new Lycanthrope2(
                        "Male Alpha 3",
                        false,
                        CategorieAge.ADULTE,
                        85,
                        RangHierarchie.α,
                        0.7),

                new Lycanthrope2(
                        "Femelle Alpha 3",
                        true,
                        CategorieAge.ADULTE,
                        75,
                        RangHierarchie.α,
                        0.6),

                30
        );

        colonie.ajouterMeute(nouvelleMeute);
        assertTrue(colonie.getMeutes().contains(nouvelleMeute));
    }

    @Test
    public void testSupprimerMeute() {
        colonie.supprimerMeute(meute1);

        assertFalse(colonie.getMeutes().contains(meute1));
        assertTrue(colonie.getLycanthropesSansMeute().containsAll(meute1.getMembres()));
    }

    @Test
    public void testRedistribuerLycanthropesSansMeute() {
        colonie.redistribuerLycanthropesSansMeute();

        assertFalse(colonie.getLycanthropesSansMeute().contains(lycanthropeSansMeute));
        assertTrue(meute1.getMembres().contains(lycanthropeSansMeute) || meute2.getMembres().contains(lycanthropeSansMeute));
    }

    @Test
    public void testActiverSaisonDesAmours() {
        colonie.activerSaisonDesAmours();

        assertTrue(colonie.isSaisonDesAmoursActive());
        assertTrue(meute1.isSaisonDesAmours());
        assertTrue(meute2.isSaisonDesAmours());
    }

    @Test
    public void testDesactiverSaisonDesAmours() {
        colonie.activerSaisonDesAmours();
        colonie.desactiverSaisonDesAmours();

        assertFalse(colonie.isSaisonDesAmoursActive());
        assertFalse(meute1.isSaisonDesAmours());
        assertFalse(meute2.isSaisonDesAmours());
    }

    @Test
    public void testHurlementCollectif() {
        colonie.hurlementCollectif(TypeHurlement.APPARTENANCE);

        // Pas de vérification directe possible, mais on vérifie que la méthode n'échoue pas
    }

    @Test
    public void testCalculerPopulationTotale() {
        int population = colonie.calculerPopulationTotale();

        assertEquals(meute1.getTaille() + meute2.getTaille() + 1, population);
    }

    @Test
    public void testRepartirParRangs() {
        var repartition = colonie.repartirParRangs();

        assertEquals(4, repartition.get(RangHierarchie.α)); // Deux alphas par meute
        assertEquals(1, repartition.getOrDefault(RangHierarchie.γ, 0)); // Lycanthrope sans meute
    }

    @Test
    public void testGererInteractionEntreColonies() {
        Colonie autreColonie = new Colonie("Colonie Beta");

        colonie.gererInteractionEntreColonies(autreColonie, Interaction.Alliance);
        colonie.gererInteractionEntreColonies(autreColonie, Interaction.Rivalite);

        // Pas de vérification implicite directe possible, mais on vérifie que la méthode n'échoue pas
    }

    @Test
    public void testVerifierEtatCrise() {
        // Pas en crise initialement
        assertFalse(colonie.estEnCrise());

        // Déclarer la moitié des lycanthropes malades pour déclencher une crise
        meute1.getMembres().forEach(Lycanthrope2::devenirMalade);
        meute2.getMembres().forEach(Lycanthrope2::devenirMalade);

        colonie.verifierEtatCrise();
        assertTrue(colonie.estEnCrise());

        // Guérir les lycanthropes pour mettre fin à la crise
        meute1.getMembres().forEach(Lycanthrope2::guerir);
        meute2.getMembres().forEach(Lycanthrope2::guerir);

        colonie.verifierEtatCrise();
        assertFalse(colonie.estEnCrise());
    }

    @Test
    public void testDeclarerCrise() {
        colonie.declarerCrise();
        assertTrue(colonie.estEnCrise());
    }

    @Test
    public void testMettreFinCrise() {
        colonie.declarerCrise();
        colonie.mettreFinCrise();
        assertFalse(colonie.estEnCrise());
    }
}
