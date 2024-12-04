
import static org.junit.jupiter.api.Assertions.*;

import Creatures.Age;
import Creatures.Bestiales.Orque;
import Creatures.Creature;
import Creatures.Demoralisantes.Elfe;
import Creatures.MortVivantes.Zombie;
import Creatures.TypeCreature;
import Creatures.VIP.Nain;
import Maladies.Maladie;
import Maladies.MaladieType;
import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class CreatureTest {

    private Creature creature;
    private ServiceMedical serviceMedical;
    private Maladie maladie;
    private List<Creature> autresCreatures;

    @BeforeEach
    void setUp() {
        // Initialisation des objets nécessaires pour les tests
        creature = new Orque("NomCreature", "M", 70, 1.75, Age.adulte, 50);
        serviceMedical = new ServiceMedical("Service",12.5, Budget.faible, TypeCreature.ORQUE);
        serviceMedical.ajouterCreature(creature);
        autresCreatures = new ArrayList<>();
    }

    @Test
    public void testGetters() {
        assertEquals("NomCreature", creature.getNom());
        assertEquals(50.0, creature.getMoral(), 0.01);
        assertEquals(70.0, creature.getPoids(), 0.01);
        assertEquals(1.75, creature.getTaille(), 0.01);
        assertEquals(Age.adulte, creature.getAge());
    }

    @Test
    public void testDiminuerMoral() {
        creature.diminuerMoral(10);
        assertEquals(40.0, creature.getMoral(), 0.01); // Moral est diminué de 10

        creature.diminuerMoral(50); // Le moral devrait être limité à 0
        assertEquals(0.0, creature.getMoral(), 0.01);
    }

    @Test
    public void testAttendre() {
        creature.attendre(serviceMedical);
        assertEquals(30.0, creature.getMoral(), 0.01);
    }
    @Test
    public void testAttendreTriage() {

        Orque creature2 = new Orque("NomCreature", "M", 70, 1.75, Age.adulte, 50);
        serviceMedical.ajouterCreature(creature2);
        creature.attendre(serviceMedical);
        assertEquals(40.0, creature.getMoral(), 0.01);
    }

    @Test
    public void testAttendreVIP() {
        ServiceMedical serviceNain = new ServiceMedical("Service",12.5, Budget.faible, TypeCreature.NAIN);
        Nain creatureVIP = new Nain("NomCreature", "M", 70, 1.75, Age.adulte, 50);
        serviceNain.ajouterCreature(creatureVIP);
        creatureVIP.attendre(serviceNain);
        assertEquals(20.0, creatureVIP.getMoral(), 0.01);
    }

    @Test
    public void testHurler() {
        creature.hurler();
        assertEquals(1, creature.getNombreHurlement()); // Le nombre de hurlements doit être incrémenté
    }

    @Test
    public void testTomberMaladeNouvelleMaladie() {
        Maladie nouvelleMaladie = new Maladie(MaladieType.FOMO);
        creature.tomberMalade(nouvelleMaladie);
        assertTrue(creature.getMaladies().contains(nouvelleMaladie));
    }
    @Test
    public void testTomberMaladeExistante() {
        maladie = new Maladie(MaladieType.DRS);
        creature.tomberMalade(maladie);
        Maladie maladieExistante = new Maladie(MaladieType.DRS);
        creature.tomberMalade(maladieExistante);
        assertEquals(1, creature.getMaladies().size());
    }

    @Test
    public void testGuerir() {
        maladie = new Maladie(MaladieType.DRS);
        Maladie maladie2 = new Maladie(MaladieType.DRS);
        creature.tomberMalade(maladie);
        creature.guerir(maladie2);
        assertFalse(creature.getMaladies().contains(maladie)); // La maladie doit être retirée de la liste
        assertEquals(60, creature.getMoral()); // Le moral doit augmenter de 10
    }

    @Test
    public void testContaminer() {
        maladie = new Maladie(MaladieType.DRS);

        creature.tomberMalade(maladie);
        Creature victime = new Orque("Victime", "M",70, 1.75, Age.vieux, 50);
        autresCreatures.add(victime);
        
        creature.contaminer(maladie, autresCreatures);
        
        assertTrue(victime.getMaladies().contains(maladie)); // La victime doit être malade après contamination
    }

    @Test
    public void testTrepasser() {
        maladie = new Maladie(MaladieType.DRS);
        creature.tomberMalade(maladie);
        creature.trepasser(maladie,serviceMedical);
        assertFalse(serviceMedical.getCreatures().contains(creature));
    }
    @Test
    public void testTrepasserDemoralisante() {

        Elfe creature1 = new Elfe("NomCreature", "M", 70, 1.75, Age.adulte, 50);
        ServiceMedical serviceElfe = new ServiceMedical("Service",12.5, Budget.faible, TypeCreature.ELFE);
        Elfe creature2 = new Elfe("NomCreature", "M", 70, 1.75, Age.adulte, 50);

        serviceElfe.ajouterCreature(creature2);
        serviceElfe.ajouterCreature(creature1);

        maladie = new Maladie(MaladieType.DRS);
        creature1.tomberMalade(maladie);

        creature1.trepasser(maladie,serviceElfe);

        assertFalse(serviceElfe.getCreatures().contains(creature1));
        assertEquals(40.0,creature2.getMoral(),0.01);
    }

    @Test
    public void testTrepasserMortVivant() {

        Zombie creature1 = new Zombie("NomCreature", "M", 70, 1.75, Age.adulte, 50);
        ServiceMedical serviceZombie = new ServiceMedical("Service",12.5, Budget.faible, TypeCreature.ZOMBIE);

        serviceZombie.ajouterCreature(creature1);

        maladie = new Maladie(MaladieType.DRS);
        creature1.tomberMalade(maladie);

        creature1.trepasser(maladie,serviceZombie);

        assertTrue(serviceZombie.getCreatures().contains(creature1));
    }
}
