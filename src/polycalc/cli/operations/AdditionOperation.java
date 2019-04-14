package polycalc.cli.operations;

import polycalc.logic.Polynomial;

public class AdditionOperation extends PairPolynomialOperationBase<Polynomial> {
    @Override
    protected Polynomial doOperation(Polynomial p1, Polynomial p2) {
        return p1.add(p2);
    }
}
