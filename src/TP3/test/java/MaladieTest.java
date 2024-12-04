import Maladies.Maladie;
import Maladies.MaladieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaladieTest {
    private Maladie maladie;

    @BeforeEach
    public void setUp() {
        maladie = new Maladie(MaladieType.DRS);
    }
    @Test
    public void testGetters() {
        // Vérifier que le nom complet est correct
        assertEquals("Dépendance aux réseaux sociaux", maladie.getNomComplet(), "Le nom complet de la maladie doit être correct.");

        // Vérifier que le nom abrégé est correct
        assertEquals("DRS", maladie.getNomAbrege(), "Le nom abrégé de la maladie doit être correct.");

        // Vérifier que le niveau initial est 0
        assertEquals(0, maladie.getNiveau(), "Le niveau de la maladie doit être 0 au départ.");

        // Vérifier que le niveau maximum est correctement récupéré
        assertEquals(7, maladie.getNiveauMax(), "Le niveau maximum de la maladie doit être correct.");
    }

    @Test
    public void testEstLetal() {
        maladie.setNiveau(maladie.getNiveauMax());
        // Vérifier que la maladie est maintenant létale
        assertTrue(maladie.estLetal(), "La maladie devrait être létale quand elle atteint le niveau maximal.");
    }
}
