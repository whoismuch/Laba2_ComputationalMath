import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;


public class Writer {

    List<SolvingEquation> solvingEquations;
    List<SolvingSystem> solvingSystems;

    public Writer ( ) {
        solvingEquations = new ArrayList<>();
        solvingSystems = new ArrayList<>();
    }

    public void setSolvingEquations (SolvingEquation ... solvingEquations) {
        for (SolvingEquation solvingEquation : solvingEquations) {
            this.solvingEquations.add(solvingEquation);
        }
    }

    public void setSolvingSystems (SolvingSystem ... solvingSystems) {
        for (SolvingSystem solvingSystem : solvingSystems) {
            this.solvingSystems.add(solvingSystem);
        }
    }

    public void writeEquationsRoots () {
        for (SolvingEquation solvingEquation: solvingEquations) {
            double acc = solvingEquation.getE();
            String[] splitter = String.valueOf(acc).split("\\.");
            Integer i = splitter[1].length();
            i+=2;

            if (solvingEquation.getDescription().equals("byBisection") && solvingEquation.getRoot() == null) System.out.println("root " + solvingEquation.getDescription() + " oops, вы выбрали неподходящий интервал (данный метод не находит на нем корни)");
            else if (solvingEquation.getDescription().equals("bySimpleIteration") && solvingEquation.getRoot() == null) System.out.println("root " + solvingEquation.getDescription() + " oops, нарушено условие сходимости, вам следует выбрать другой интервал");
            else System.out.printf("root " + solvingEquation.getDescription()+ ": %." + i.toString() +"f %n", solvingEquation.getRoot());
        }
    }

    public void writeDiff(SolvingEquation solvingEquation1, SolvingEquation solvingEquation2) {
        if (solvingEquation1.getRoot() != null && solvingEquation2.getRoot() != null) {
            double acc = solvingEquation1.getE( );
            String[] splitter = String.valueOf(acc).split("\\.");
            Integer i = splitter[1].length( );
            i += 2;
            Double diff = abs(solvingEquation1.getRoot( ) - solvingEquation2.getRoot( ));
            System.out.printf("difference of roots: %." + i.toString( ) + "f %n", diff);
        }
        else System.out.println("can't calculate the difference");
    }

    public void writeSystemsRoots() {
        for (SolvingSystem solvingSystem: solvingSystems) {
            double acc = solvingSystem.getE();
            String[] splitter = String.valueOf(acc).split("\\.");
            Integer i = splitter[1].length();
            i+=2;

            System.out.printf("rootX " + solvingSystem.getDescription()+ ": %." + i.toString() +"f %n", solvingSystem.getRootX());
            System.out.printf("rootY " + solvingSystem.getDescription()+ ": %." + i.toString() +"f %n", solvingSystem.getRootY());
        }
    }
}
