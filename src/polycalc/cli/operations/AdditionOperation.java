package polycalc.cli.operations;

import polycalc.logic.Polynomial;

public class AdditionOperation extends PolynomialPairOperationBase<Polynomial> {
    @Override
    public String getName() {
        return "Addition";
    }

    @Override
    protected Polynomial doOperation(Polynomial p1, Polynomial p2) {
        return p1.add(p2);
    }
}
