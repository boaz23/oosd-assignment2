package polycalc.cli.operations;

import polycalc.cli.NumberField;
import polycalc.cli.PolynomialOperation;
import polycalc.cli.PolynomialParser;
import polycalc.logic.Polynomial;

import java.util.Scanner;

public abstract class PairPolynomialOperationBase<TResult> implements PolynomialOperation {
    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public void run(NumberField numberField) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please insert the first polynomial");
        String inputP1 = scanner.nextLine();

        System.out.println("Please insert the second polynomial");
        String inputP2 = scanner.nextLine();

        PolynomialParser parser = new PolynomialParser(numberField);
        Polynomial p1 = parser.parse(inputP1);
        Polynomial p2 = parser.parse(inputP2);

        System.out.println("The solution is:");
        System.out.println(this.doOperation(p1, p2));
    }

    protected abstract TResult doOperation(Polynomial p1, Polynomial p2);
}
