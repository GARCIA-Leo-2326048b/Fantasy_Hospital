package ServicesMedicaux.ServicesSpeciaux;

import Creatures.TypeCreature;
import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;

public class Crypte extends ServiceMedical {
    private int temperature;
    private int ventilation;

    public Crypte(String nom, double superficie, Budget budget, TypeCreature typeCreature, int temperature, int ventilation) {
        super(nom, superficie, budget, typeCreature);
        this.temperature = temperature;
        this.ventilation = ventilation;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getVentilation() {
        return ventilation;
    }

    public void setVentilation(int ventilation) {
        this.ventilation = ventilation;
    }


}
