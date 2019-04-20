package polycalc.cli.number_fields;

import polycalc.cli.NumberField;
import polycalc.logic.RationalScalar;
import polycalc.logic.Scalar;

public class RationalField implements NumberField {
    @Override
    public String getPolynomialSplitRegex(String baseRegex) {
        return "(?!/-)" + baseRegex + "(?<!/)";
    }

    @Override
    public Scalar parseScalar(String s) {
        String[] parts = s.split("/");
        int numerator = Integer.parseInt(parts[0]);
        int denominator = this.parseDenominator(parts);

        return new RationalScalar(numerator, denominator);
    }

    private int parseDenominator(String[] parts) {
        int denominator;

        // edge case, the exponent is implicitly 1
        if (parts.length < 2) {
            denominator = 1;
        }
        else {
            denominator = Integer.parseInt(parts[1]);
        }

        return denominator;
    }
}
