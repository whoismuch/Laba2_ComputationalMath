import java.util.Scanner;

public class ReaderFromConsole extends Reader {
    private Reader reader;

    public ReaderFromConsole (Scanner scanner, Reader reader) {
        super(scanner);
        this.reader = reader;
    }

    public void readFromConsole() {
        int numberOfEquation = readNumber(reader.getEquations(),
                "Выберите одно из предложенных нелинейных уравнений (введите номер понравившегося)",
                "Ну вы чего, уравнения под таким номером нет",
                "Номер должен быть целым числом"
        );


        double[] intervalOfEquation = readInterval();
        double accuracyOfEquation = readAccuracy(intervalOfEquation[1] - intervalOfEquation[0]);

        Decision equation = new Decision(numberOfEquation, accuracyOfEquation, intervalOfEquation[0], intervalOfEquation[1]);


        int numberOfSystem = readNumber(reader.getSystems(),
                "Выберите одну из предложенных систем нелинейных уравнений (введите номер понравившейся)",
                "Ну вы чего, системы уравнений под таким номером нет",
                "Номер должен быть целым числом"
        );


        double accuracyOfSystem = readAccuracy(Double.MAX_VALUE);

        Decision system = new Decision(numberOfSystem, accuracyOfSystem, null, null);

        reader.setEquation(equation);
        reader.setSystem(system);
    }

    public int readNumber(String[] array, String startMessage, String boundaryError, String formatError) {
        int decision = 0;

        System.out.println(startMessage);
        for (Integer i=1; i<=array.length; i++) {
            System.out.println(i + ") " + array[i-1]);
        }
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                decision = Integer.parseInt(strDecision);
                if (decision < 1 || decision > array.length) {
                    System.out.println(boundaryError);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(formatError);
                continue;
            }
        }

        return decision;
    }

    public double readAccuracy(double diff) {
        double accuracy = 0;
        System.out.println("Введите предпочитаемую точность вычисления корней:");
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                accuracy = Double.parseDouble(strDecision);
                if (accuracy <= 0) {
                    System.out.println("Точность должна быть положительной");
                    continue;
                }
                if (accuracy >= diff) {
                    System.out.println("Точность не должна превосходить длинну интервала");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Точность - это число");
                continue;
            }
        }

        return accuracy;
    }

    public double[] readInterval(){
        double left = 0;
        System.out.println("Введите левую границу интервала, на котором производится поиск корней");
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                left = Double.parseDouble(strDecision);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Граница интервала - это число");
                continue;
            }
        }

        double right = 0;
        System.out.println("Введите правую границу интервала, на котором производится поиск корней");
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                right = Double.parseDouble(strDecision);
                if (right <= left) {
                    System.out.println("Правая граница должна быть больше левой");
                    continue;
                }
//                if (right - left > 2) {
//                    System.out.println("Какой-то большой у вас интервал, ждать вечность мы не хотим, сузьте, пожалуйста");
//                    continue;
//                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Граница интервала - это число");
                continue;
            }
        }

        double[] result = {left, right};

        return result;
    }


}
