package polycalc.cli;

import polycalc.logic.Scalar;

public interface NumberField {
    Scalar parseScalar(String s);
}
