import Creatures.Age;
import Creatures.Bestiales.Orque;
import Creatures.Creature;
import Creatures.TypeCreature;
import HopitalsFantastiques.HopitalFantastique;
import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HopitalFantastiqueTest {
    private HopitalFantastique hopital;
    private ServiceMedical service1, service2;
    private Creature medecin1, creatureNonMedecin;

    @BeforeEach
    void setUp() {
        hopital = new HopitalFantastique("Hopital Magique");

        // Création de services médicaux
        service1 = new ServiceMedical("Service 1", 1000, Budget.faible, TypeCreature.ORQUE);

        // Création de médecins
        medecin1 = new Orque("Dr. Orque", "Homme", Age.vieux);

        // Création d'une créature non médecin
        creatureNonMedecin = new Orque("NomCreature", "M", 70, 1.75, Age.adulte, 50);
    }

    @Test
    void testAjouterService() {
        // Ajouter un service
        hopital.ajouterService(service1);
        assertEquals(1, hopital.getServicesMedicaux().size());

    }

    @Test
    void testAjouterServiceMaxAtteint() {
        for (int i = 1; i <= hopital.getSERVICES_MAX(); i++) {
            hopital.ajouterService(new ServiceMedical("Service " + i, 1000, Budget.faible, TypeCreature.ORQUE));
        }

        // Ajouter un 6ème service devrait échouer
        hopital.ajouterService(new ServiceMedical("Service 6", 1000, Budget.faible, TypeCreature.ORQUE));
        assertEquals(5, hopital.getServicesMedicaux().size());
    }

    @Test
    void testAjouterMedecin() {
        // Ajouter un médecin valide
        hopital.ajouterMedecin(medecin1);
        assertEquals(1, hopital.getMedecins().size());
        assertTrue(hopital.getMedecins().contains(medecin1));

    }

    @Test
    void testAjouterMedecinNonValide() {
        // Essayer d'ajouter une créature qui n'est pas un médecin
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            hopital.ajouterMedecin(creatureNonMedecin);
        });
        assertEquals("NomCreature n'est pas un médecin.", exception.getMessage());
    }

}
