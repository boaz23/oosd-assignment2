package polycalc.cli;

import polycalc.cli.number_fields.RationalField;
import polycalc.cli.number_fields.RealField;
import polycalc.cli.operations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {
    private final HashMap<Integer, PolynomialOperation> operations;
    private final HashMap<String, NumberField> numberFields;

    private Calculator() {
        this.operations = new HashMap<Integer, PolynomialOperation>() {{
            put(1, new AdditionOperation());
            put(2, new MultiplicationOperation());
            put(3, new EvaluationOperation());
            put(4, new DerivateOperation());
            put(5, new ExitOperation());
        }};

        this.numberFields = new HashMap<String, NumberField>() {{
            put("Q", new RationalField());
            put("R", new RealField());
        }};
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            for (Map.Entry<Integer, PolynomialOperation> operationEntry : calculator.operations.entrySet()) {
                PolynomialOperation operation = operationEntry.getValue();
                System.out.println(operationEntry.getKey() + ". " + operation.getName());
            }

            int operationOption = scanner.nextInt();

            // move the scanner to the next line, so
            // that the next time it will ask for input
            scanner.nextLine();

            PolynomialOperation operation = calculator.operations.get(operationOption);
            if (operation.shouldExit()) {
                break;
            }

            System.out.println("Please select the scalar field");
            System.out.println("Rational (Q) or Real (R)");
            String fieldOption = scanner.nextLine();
            NumberField numberField = calculator.numberFields.get(fieldOption);

            operation.run(numberField);
        }
    }
}
