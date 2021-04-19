import static java.lang.Math.*;

public class SolvingEquationBySimpleIteration implements SolvingEquation {

    private String description = "bySimpleIteration";
    private double a;
    private double b;
    private double e;
    private int number;
    private double L;
    private Double root;
    //    String[] equations = {"1.73x^3-3.21x^2+x-0.025 = 0", "2.24x+x^(2)+57-ℯ^(-x) = 0", "4.31cos(x)+0.5ln(x)-5 = 0"};


    public SolvingEquationBySimpleIteration (Decision equation) {
        this.a = equation.getIntervalLeftBoundary( );
        this.b = equation.getIntervalRightBoundary( );
        this.e = equation.getAccuracy( );
        this.number = equation.getNumber( );
    }

//    String[] derivatives = {"5.19x^2-6.42x+1", "2.24+2x+e^(-x)", "-4.31*s"};


    public void solve ( ) {
        double max;
        double xi;
        if (getValueOfDerivative(a) > getValueOfDerivative(b)) {
            max = getValueOfDerivative(a);
            xi = a;
        } else {
            max = getValueOfDerivative(b);
            xi = b;
        }
        L = calculateL(max);
        if (abs(getValueOfDerivativeOfFi(a)) > 1 || abs(getValueOfDerivativeOfFi(b)) > 1) {
            root = null;
            return;
        }
        double xi1 = getValueOfFi(xi);
        while (abs(xi1 - xi) > e) {
            xi = xi1;
            xi1 = getValueOfFi(xi);
        }
        root = xi;
    }

    @Override
    public Double getRoot ( ) {
        return root;
    }

    @Override
    public int getNumber ( ) {
        return number;
    }

    @Override
    public double getValueOfFunction (double x) {
        return 0;
    }

    public double calculateL (double max) {

        return -1d / max;
    }

    public double getValueOfDerivative (double x) {
        switch (number) {
            case (1):
                return 5.19d * pow(x, 2) - 6.42d * x + 1;
            case (2):
                return 2.24 + 2 * x + pow(Math.E, -1 * x);
            case (3):
                return -4.31 * sin(x) + 0.5d / x;
        }

        return 0;
    }

    @Override
    public double getE ( ) {
        return e;
    }

    @Override
    public String getDescription ( ) {
        return description;
    }

    public double getValueOfFi (double x) {
        switch (number) {
            case (1):
                return x+L*(1.73d * pow(x, 3) - 3.21d * pow(x, 2) + x - 0.025d);
            case (2):
                return x+L*(2.24 * x + pow(x, 2) + 57 - pow(Math.E, -1 * x));
            case (3):
                return x+L*(4.31 * cos(x) + 0.5 * log(x) - 5);
        }

        return 0;
    }

    public double getValueOfDerivativeOfFi (double x) {
        switch (number) {
            case (1):
                return 1+L*(1.73d*3 * pow(x, 2) - 3.21d*2 * x + 1);
            case (2):
                return 1+L*(2.24  + 2*x + pow(Math.E, -1 * x));
            case (3):
                return 1+L*(-4.31 * sin(x) + 0.5/x);
        }

        return 0;
    }

}
