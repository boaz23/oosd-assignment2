package polycalc.cli.operations;

import polycalc.cli.NumberField;
import polycalc.cli.PolynomialOperation;

public class ExitOperation implements PolynomialOperation {
    @Override
    public boolean shouldExit() {
        return true;
    }

    @Override
    public void run(NumberField numberField) {
        throw new UnsupportedOperationException();
    }
}
