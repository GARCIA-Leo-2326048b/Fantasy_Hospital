import Creatures.Age;
import Creatures.Bestiales.Orque;
import Creatures.Creature;
import Creatures.TypeCreature;
import Creatures.VIP.Reptilien;
import Maladies.MaladieType;
import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;
import Maladies.Maladie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceMedicalTest {

    private ServiceMedical serviceMedical;
    private Creature creature;
    private Budget budget;
    private Maladie maladie;

    @BeforeEach
    public void setUp() {
        // Initialisation des objets réels
        creature = new Orque("NomCreature", "M", 70, 1.75, Age.adulte, 50);
        maladie = new Maladie(MaladieType.DRS); // Création d'une maladie
        serviceMedical = new ServiceMedical("Service 1", 1000, Budget.faible, TypeCreature.ORQUE); // Service médical avec un budget
    }
    @Test
    public void testGetters() {
        assertEquals("Service 1", serviceMedical.getNom());
        assertEquals(1000.0, serviceMedical.getSuperficie(), 0.01);
        assertEquals(70.0, creature.getPoids(), 0.01);
        assertEquals(1.75, creature.getTaille(), 0.01);
        assertEquals(Age.adulte, creature.getAge());
    }
    @Test
    public void testAjouterCreature() {
        // Test ajout de créature valide

        serviceMedical.ajouterCreature(creature);

        // Vérifier si la créature a bien été ajoutée
        assertEquals(1, serviceMedical.getCreatures().size());
    }

    @Test
    public void testAjouterCreatureServicePlein() {
        // Ajouter plusieurs créatures pour tester la capacité maximale
        for (int i = 0; i < serviceMedical.getCAPACITE_MAX(); i++) {
            serviceMedical.ajouterCreature(creature);
        }

        // Tentative d'ajout d'une créature alors que le service est plein
        serviceMedical.ajouterCreature(creature);

        // Vérifier que la créature n'a pas été ajoutée
        assertEquals(serviceMedical.getCAPACITE_MAX(), serviceMedical.getCreatures().size());
    }

    @Test
    public void testEnleverCreature() {
        serviceMedical.ajouterCreature(creature);

        serviceMedical.enleverCreature(creature);

        // Vérifier si la créature a bien été retirée
        assertTrue(serviceMedical.getCreatures().isEmpty());
    }

    @Test
    public void testSoignerCreatures() {
        serviceMedical.ajouterCreature(creature);

        // Simuler que la créature a été soignée
        serviceMedical.soignerCreatures(maladie);

        // Il n'y a pas de vérification automatique ici, on s'assure juste que la méthode s'exécute sans erreurs
    }

    @Test
    public void testReviserBudget() {
        Budget nouveauBudget = Budget.insuffisant; // Nouveau budget

        serviceMedical.reviserBudget(nouveauBudget);

        // Vérifier que le budget a bien été révisé
        assertEquals(Budget.insuffisant, serviceMedical.getBudget());
    }

    @Test
    public void testajouterCreatureMauvaisType() {

        // Test avec une créature du mauvais type
        Creature creatureInvalide = new Reptilien("Creature Invalide", "M", 70, 1.75, Age.adulte, 50);

        // Vérifier qu'une exception est levée
        assertThrows(IllegalArgumentException.class, () -> {
            serviceMedical.ajouterCreature(creatureInvalide);
        });
    }

}
