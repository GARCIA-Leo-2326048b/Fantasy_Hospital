package TestLycanthrope2;

import Lycanthrope.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test JUnit pour la classe {@link Lycanthrope2}
 * Cette classe teste toutes les méthodes de la classe Lycanthrope2, sauf les getters et setters
 * Certains tests ne peuvent pas être vérifié directement, mais on vérifie l'affichage dans la console
 */
public class TestLycanthrope2 {

    private Lycanthrope2 lycanthrope1;
    private Lycanthrope2 lycanthrope2;

    /**
     * Initialisation des objets Lycanthrope2 pour les tests
     */
    @BeforeEach
    public void setUp() {
        lycanthrope1 = new Lycanthrope2("Lycan1",
                false,
                CategorieAge.JEUNE,
                50,
                RangHierarchie.γ,
                0.5);

        lycanthrope2 = new Lycanthrope2("Lycan2",
                true,
                CategorieAge.ADULTE,
                60,
                RangHierarchie.β,
                0.7);
    }

    /**
     * Test de la méthode {@link Lycanthrope2#hurler(TypeHurlement)}.
     */
    @Test
    public void testHurler() {
        lycanthrope1.hurler(TypeHurlement.APPARTENANCE);
        lycanthrope1.hurler(TypeHurlement.AGRESSIVITE);
        lycanthrope1.hurler(TypeHurlement.DOMINATION);
        lycanthrope1.hurler(TypeHurlement.SOUMISSION);
    }

    /**
     * Test de la méthode {@link Lycanthrope2#entendreHurlement(TypeHurlement)}.
     */
    @Test
    public void testEntendreHurlement() {
        lycanthrope1.entendreHurlement(TypeHurlement.APPARTENANCE);
        lycanthrope1.entendreHurlement(TypeHurlement.AGRESSIVITE);
        lycanthrope1.entendreHurlement(TypeHurlement.DOMINATION);
        lycanthrope1.entendreHurlement(TypeHurlement.SOUMISSION);
    }

    /**
     * Test de la méthode {@link Lycanthrope2#reagirAgression()}.
     */
    @Test
    public void testReagirAgression() {
        lycanthrope1.reagirAgression();
        assertEquals(-1, lycanthrope1.getFacteurDomination());
    }

    /**
     * Test de la méthode {@link Lycanthrope2#tenterDomination(Lycanthrope2)}
     * lorsque la domination réussit.
     */
    @Test
    public void testTenterDominationReussie() {
        lycanthrope1.setForce(70);
        assertTrue(lycanthrope1.tenterDomination(lycanthrope2));
        assertEquals(RangHierarchie.β, lycanthrope1.getRangHierarchie());
        assertEquals(RangHierarchie.γ, lycanthrope2.getRangHierarchie());
    }

    /**
     * Test de la méthode {@link Lycanthrope2#tenterDomination(Lycanthrope2)}
     * lorsque la domination échoue.
     */
    @Test
    public void testTenterDominationEchoue() {
        lycanthrope1.setForce(40);
        assertFalse(lycanthrope1.tenterDomination(lycanthrope2));
        assertEquals(-1, lycanthrope1.getFacteurDomination());
    }

    /**
     * Test de la méthode {@link Lycanthrope2#seTransformerEnHumain()}
     * lorsque la transformation réussit.
     */
    @Test
    public void testSeTransformerEnHumainReussie() {
        lycanthrope1.setNiveau(80);
        lycanthrope1.seTransformerEnHumain();
        assertNull(lycanthrope1.getMeute());
    }

    /**
     * Test de la méthode {@link Lycanthrope2#seTransformerEnHumain()}
     * lorsque la transformation échoue.
     */
    @Test
    public void testSeTransformerEnHumainEchoue() {
        lycanthrope1.setNiveau(50);
        lycanthrope1.seTransformerEnHumain();
        assertNull(lycanthrope1.getMeute());
    }

    /**
     * Test de la méthode {@link Lycanthrope2#devenirMalade()}.
     */
    @Test
    public void testDevenirMalade() {
        lycanthrope1.devenirMalade();
        assertTrue(lycanthrope1.isMalade());
        lycanthrope1.devenirMalade(); // Deuxième appel
        assertTrue(lycanthrope1.isMalade());
    }

    /**
     * Test de la méthode {@link Lycanthrope2#guerir()}.
     */
    @Test
    public void testGuerir() {
        lycanthrope1.devenirMalade();
        lycanthrope1.guerir();
        assertFalse(lycanthrope1.isMalade());
    }

    /**
     * Test de la méthode {@link Lycanthrope2#mourir()}.
     */
    @Test
    public void testMourir() {
        lycanthrope1.mourir();
        assertTrue(lycanthrope1.isMort());
        assertNull(lycanthrope1.getMeute());
    }

    /**
     * Test de la méthode {@link Lycanthrope2#veillir()}.
     */
    @Test
    public void testVeillir() {
        lycanthrope1.veillir();
        assertEquals(CategorieAge.ADULTE, lycanthrope1.getCategorieAge());
        assertEquals(60, lycanthrope1.getForce());
        lycanthrope1.veillir();
        assertEquals(CategorieAge.VIEUX, lycanthrope1.getCategorieAge());
        assertEquals(55, lycanthrope1.getForce());
        lycanthrope1.veillir();
        assertTrue(lycanthrope1.isMort());
    }

    /**
     * Test de la méthode {@link Lycanthrope2#decrementerRang(RangHierarchie)}.
     */
    @Test
    public void testDecrementerRang() {
        assertEquals(RangHierarchie.β, lycanthrope1.decrementerRang(RangHierarchie.α));
        assertEquals(RangHierarchie.ω, lycanthrope1.decrementerRang(RangHierarchie.ω));
    }

    /**
     * Test du comportement de {@link Lycanthrope2#hurler(TypeHurlement)}
     * lorsque le lycanthrope est malade ou mort.
     */
    @Test
    public void testHurlerMaladeOuMort() {
        lycanthrope1.devenirMalade();
        lycanthrope1.hurler(TypeHurlement.DOMINATION);
        assertTrue(lycanthrope1.isMalade());
        lycanthrope1.mourir();
        lycanthrope1.hurler(TypeHurlement.DOMINATION);
        assertTrue(lycanthrope1.isMort());
    }
}
