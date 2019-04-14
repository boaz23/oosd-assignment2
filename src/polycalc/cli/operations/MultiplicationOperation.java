package polycalc.cli.operations;

import polycalc.logic.Polynomial;

public class MultiplicationOperation extends PolynomialPairOperationBase<Polynomial> {
    @Override
    public String getName() {
        return "Multiplication";
    }

    @Override
    protected Polynomial doOperation(Polynomial p1, Polynomial p2) {
        return p1.mul(p2);
    }
}
