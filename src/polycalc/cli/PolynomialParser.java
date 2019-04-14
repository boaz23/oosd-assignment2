package polycalc.cli;

import polycalc.logic.PolyTerm;
import polycalc.logic.Polynomial;
import polycalc.logic.PolynomialBuilder;
import polycalc.logic.Scalar;

public class PolynomialParser {
    private final NumberField numberField;

    public PolynomialParser(NumberField numberField) {
        this.numberField = numberField;
    }

    public Polynomial parse(String input) {
        PolynomialBuilder builder = new PolynomialBuilder();
        String[] inputPolyTerms = input.split("((?=[-])|[+])");
        for (int i = 0; i < inputPolyTerms.length; i++) {
            String inputPolyTerm = inputPolyTerms[i];
            String[] inputTermParts = inputPolyTerm.split("x\\^");

            Scalar coefficient = this.parseCoefficient(inputTermParts[0]);
            int exponent = this.parseExponent(inputTermParts);
            PolyTerm polyTerm = new PolyTerm(coefficient, exponent);
            builder.addPolyTerm(polyTerm);
        }

        return builder.build();
    }

    private Scalar parseCoefficient(String input) {
        // edge case, the coefficient is implicitly 1
        if (input.equals("")) {
            input = "1";
        }

        return this.numberField.parseScalar(input);
    }

    private int parseExponent(String[] inputTermParts) {
        int exponent;

        // edge case, the exponent is implicitly 0
        if (inputTermParts.length < 2) {
            exponent = 0;
        }
        else {
            exponent = Integer.parseInt(inputTermParts[1]);
        }

        return exponent;
    }
}
