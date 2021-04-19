import static java.lang.Math.*;

public interface SolvingSystem {
    public void solveSystem ( );

    public String getDescription ( );

    public Double getRootX ( );

    public Double getRootY ( );

    public double getE();

    public int getNumber();

    public default double getValueOff1 (double x, double y) {
        switch (getNumber()) {
            case (1):
                return 0.1*pow(x,2)+x+0.2*y-0.3;
            case (2):
                return sin(x+0.5)-y-1;
        }
        return 0;

    }

    public default double getValueOff2 (double x, double y) {
        switch (getNumber()) {
            case (1):
                return 0.2*pow(x,2)+y-0.1*x*y-0.7;
            case (2):
                return y+5*pow(x,2)-13;
        }
        return 0;

    }

    public default double getYOff1 (double x) {
        switch (getNumber()) {
            case (1):
                return (0.3-0.1*pow(x,2)-x)/0.2;
            case (2):
                return sin(x+0.5)-1;
        }
        return 0;

    }

    public default double getYOff2 (double x) {
        switch (getNumber()) {
            case (1):
               return (0.7-0.2*pow(x,2))/(1-0.1*x);
            case (2):
                return -5*pow(x,2)+13;
        }
        return 0;

    }





}
