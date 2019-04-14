package polycalc.cli.operations;

import polycalc.cli.NumberField;
import polycalc.cli.PolynomialOperation;
import polycalc.cli.PolynomialParser;
import polycalc.logic.Polynomial;

import java.util.Scanner;

public class DerivateOperation implements PolynomialOperation {
    @Override
    public String getName() {
        return "Derivate";
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public void run(NumberField numberField) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please insert the polynomial");
        String inputPolynomial = scanner.nextLine();

        PolynomialParser parser = new PolynomialParser(numberField);
        Polynomial polynomial = parser.parse(inputPolynomial);

        System.out.println("The derivative polynomial is:");
        System.out.println(polynomial.derivate());
    }
}
