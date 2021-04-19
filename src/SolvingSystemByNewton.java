import static java.lang.Math.*;

public class SolvingSystemByNewton implements SolvingSystem {

    private String description = "byNewton";
    private double e;
    private int number;
    private Double rootX;
    private Double rootY;
    private double x;
    private double y;

    public double getX ( ) {
        return x;
    }

    public double getY ( ) {
        return y;
    }

    public SolvingSystemByNewton (Decision system) {
        this.e = system.getAccuracy( );
        this.number = system.getNumber( );
    }

    public void solveSystem ( ) {
        saveStartValues( );
        double x = this.x;
        double y = this.y;

        double detAx = getValueOff1(x, y)*getValueOfdf2_dy(x,y) - getValueOff2(x,y)*getValueOfdf1_dy(x,y);
        double detAy = getValueOfdf1_dx(x,y)*getValueOff2(x,y) - getValueOfdf2_dx(x,y)*getValueOff1(x,y);
        double detJ = getValueOfdf1_dx(x,y)*getValueOfdf2_dy(x,y) - getValueOfdf2_dx(x,y)*getValueOfdf1_dy(x,y);

        double xi = x - detAx/detJ;
        double yi = y - detAy/detJ;

        while (max(abs(xi-x), abs(yi-y)) > e) {
            x = xi;
            y = yi;

            detAx = getValueOff1(x, y)*getValueOfdf2_dy(x,y) - getValueOff2(x,y)*getValueOfdf1_dy(x,y);
            detAy = getValueOfdf1_dx(x,y)*getValueOff2(x,y) - getValueOfdf2_dx(x,y)*getValueOff1(x,y);
            detJ = getValueOfdf1_dx(x,y)*getValueOfdf2_dy(x,y) - getValueOfdf2_dx(x,y)*getValueOfdf1_dy(x,y);

            xi = x - detAx/detJ;
            yi = y - detAy/detJ;

        }

        rootX = xi;
        rootY = yi;
    }

    @Override
    public String getDescription ( ) {
        return description;
    }

    private void saveStartValues ( ) {
        switch (number) {
            case (1):
                x = 0.25;
                y = 0.75;

                break;
            case (2):
                x = 2;
                y = -0.5;
                break;
        }
    }

    public double getValueOfdf1_dx (double x, double y) {
        switch (number) {
            case (1):
               return 0.2*x+1;
            case (2):
               return cos(x+0.5);
        }
        return 0;
    }

    public double getValueOfdf1_dy (double x, double y) {
        switch (number) {
            case (1):
                return 0.2;
            case (2):
                return -1;
        }
        return 0;
    }

    public double getValueOfdf2_dx (double x, double y) {
        switch (number) {
            case (1):
                return 0.4*x-0.1*y;
            case (2):
                return 10*x;
        }
        return 0;

    }

    public double getValueOfdf2_dy (double x, double y) {
        switch (number) {
            case (1):
                return 1-0.1*x;
            case (2):
                return 1;
        }
        return 0;

    }






    @Override
    public Double getRootX ( ) {
        return rootX;
    }

    @Override
    public double getE ( ) {
        return e;
    }

    @Override
    public int getNumber ( ) {
        return number;
    }

    @Override
    public Double getRootY ( ) {
        return rootY;
    }


}
