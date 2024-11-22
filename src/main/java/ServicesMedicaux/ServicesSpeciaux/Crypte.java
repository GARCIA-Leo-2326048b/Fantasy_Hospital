package ServicesMedicaux.ServicesSpeciaux;

import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;

public class Crypte extends ServiceMedical {
    private int temperature;
    private int ventilation;

    public Crypte(String nom, double superficie, Budget budget, int temperature, int ventilation) {
        super(nom, superficie, budget);
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
