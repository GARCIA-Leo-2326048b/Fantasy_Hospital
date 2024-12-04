package TestMeute;

import Lycanthrope.*;
import Meute.Meute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test JUnit pour la classe {@link Meute}.
 * Cette classe teste toutes les méthodes de la classe Meute, sauf les getters et setters.
 * Certains tests ne peuvent pas être vérifié directement, mais on vérifie l'affichage dans la console
 */
public class TestMeute {

    private Meute meute;
    private Lycanthrope2 maleAlpha;
    private Lycanthrope2 femelleAlpha;
    private Lycanthrope2 lycanthrope1;
    private Lycanthrope2 lycanthrope2;

    /**
     * Initialisation de la meute et de ses membres pour les tests.
     */
    @BeforeEach
    public void setUp() {
        maleAlpha = new Lycanthrope2("Male Alpha",
                false,
                CategorieAge.ADULTE,
                80,
                RangHierarchie.α,
                0.6);

        femelleAlpha = new Lycanthrope2("Femelle Alpha",
                true,
                CategorieAge.ADULTE,
                70,
                RangHierarchie.α,
                0.7);

        meute = new Meute(maleAlpha, femelleAlpha, 30);

        lycanthrope1 = new Lycanthrope2("Lycan1",
                false,
                CategorieAge.ADULTE,
                60,
                RangHierarchie.β,
                0.5);

        lycanthrope2 = new Lycanthrope2("Lycan2",
                true,
                CategorieAge.ADULTE,
                50,
                RangHierarchie.γ,
                0.4);

        meute.ajouterLycanthrope(lycanthrope1);
        meute.ajouterLycanthrope(lycanthrope2);
    }

    /**
     * Test de la méthode {@link Meute#ajouterLycanthrope(Lycanthrope2)}.
     */
    @Test
    public void testAjouterLycanthrope() {
        Lycanthrope2 lycanthrope3 = new Lycanthrope2("Lycan3",
                false,
                CategorieAge.ADULTE,
                40,
                RangHierarchie.δ,
                0.3);

        meute.ajouterLycanthrope(lycanthrope3);

        assertTrue(meute.getMembres().contains(lycanthrope3));
        assertEquals(5, meute.getTaille());
    }

    /**
     * Test de la méthode {@link Meute#enleverLycanthrope(Lycanthrope2)}.
     */
    @Test
    public void testEnleverLycanthrope() {
        meute.enleverLycanthrope(lycanthrope1);

        assertFalse(meute.getMembres().contains(lycanthrope1));
        assertEquals(3, meute.getTaille());
    }

    /**
     * Test de la méthode {@link Meute#reproduire()}.
     */
    @Test
    public void testReproduire() {
        meute.mettreAJourSaisonDesAmours(true);
        meute.reproduire();

        long jeunesAjoutes = meute.getMembres().stream()
                .filter(m -> m.getCategorieAge() == CategorieAge.JEUNE)
                .count();
        assertTrue(jeunesAjoutes > 0);
    }

    /**
     * Test de la méthode {@link Meute#gererConflitsHierarchiques(Lycanthrope2, Lycanthrope2)}.
     */
    @Test
    public void testGererConflitsHierarchiques() {
        meute.gererConflitsHierarchiques(lycanthrope1, lycanthrope2);
        assertEquals(RangHierarchie.β, lycanthrope1.getRangHierarchie());
        assertEquals(RangHierarchie.γ, lycanthrope2.getRangHierarchie());
    }

    /**
     * Test de la méthode {@link Meute#redefinirCoupleAlpha()}.
     */
    @Test
    public void testRedefinirCoupleAlpha() {
        meute.enleverLycanthrope(maleAlpha);
        meute.redefinirCoupleAlpha();
        assertEquals(meute.getMaleAlpha(), lycanthrope1);
        assertEquals(meute.getFemelleAlpha(), femelleAlpha);
        assertFalse(meute.isDissoute());
    }

    /**
     * Test de la méthode {@link Meute#dissoudreMeute()}.
     */
    @Test
    public void testDissoudreMeute() {
        meute.dissoudreMeute();

        assertTrue(meute.isDissoute());
        assertEquals(0, meute.getTaille());
        assertTrue(meute.getMembres().isEmpty());
    }

    /**
     * Test de la méthode {@link Meute#declarerLycanthropesOmega()}.
     */
    @Test
    public void testDeclarerLycanthropesOmega() {
        meute.declarerLycanthropesOmega();

        List<Lycanthrope2> omega = meute.getMembres().stream()
                .filter(m -> m.getRangHierarchie() == RangHierarchie.ω)
                .toList();

        assertTrue(omega.isEmpty()); // Aucun lycanthrope ne remplit le critère de force < seuil
    }

    /**
     * Test de la méthode {@link Meute#hurlementCollectif(TypeHurlement)}.
     */
    @Test
    public void testHurlementCollectif() {
        meute.hurlementCollectif(TypeHurlement.DOMINATION);

        // Pas de vérification directe possible, mais on vérifie que la méthode n'échoue pas
    }

    /**
     * Test de la méthode {@link Meute#interactionEntreMeutes(Meute, String)}.
     */
    @Test
    public void testInteractionEntreMeutes() {
        Meute autreMeute = new Meute(
                new Lycanthrope2("Male Alpha 2",
                        false,
                        CategorieAge.ADULTE,
                        90,
                        RangHierarchie.α,
                        0.8),

                new Lycanthrope2("Femelle Alpha 2",
                        true,
                        CategorieAge.ADULTE,
                        85,
                        RangHierarchie.α,
                        0.7),

                30
        );

        meute.interactionEntreMeutes(autreMeute, "alliance");

        // Pas de vérification implicite directe possible, mais on vérifie que la méthode n'échoue pas
    }
}
