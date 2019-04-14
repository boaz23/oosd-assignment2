package polycalc.cli.operations;

import polycalc.cli.NumberField;
import polycalc.cli.PolynomialOperation;
import polycalc.cli.PolynomialParser;
import polycalc.logic.Polynomial;
import polycalc.logic.Scalar;

import java.util.Scanner;

public class EvaluationOperation implements PolynomialOperation {
    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public void run(NumberField numberField, PolynomialParser parser) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please insert the polynomial");
        String inputPolynomial = scanner.nextLine();

        System.out.println("Please insert the scalar");
        String inputScalar = scanner.nextLine();

        Polynomial polynomial = parser.parse(inputPolynomial);
        Scalar scalar = numberField.parseScalar(inputScalar);

        System.out.println("The solution is:");
        System.out.println(polynomial.evaluate(scalar));
    }
}
