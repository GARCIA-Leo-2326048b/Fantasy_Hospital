package ServicesMedicaux.ServicesSpeciaux;

import Creatures.TypeCreature;
import ServicesMedicaux.Budget;
import ServicesMedicaux.ServiceMedical;

public class CentreDeQuarantaine extends ServiceMedical {
    private int isolation;

    public CentreDeQuarantaine(String nom, double superficie, Budget budget, TypeCreature typeCreature, int isolation) {
        super(nom, superficie, budget,typeCreature);
        this.isolation = isolation;
    }
    public int getIsolation() {
        return isolation;
    }

    public void setIsolation(int isolation) {
        this.isolation = isolation;
    }
}
