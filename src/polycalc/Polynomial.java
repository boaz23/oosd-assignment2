package polycalc;

import javafx.util.Pair;
import java.util.*;

public class Polynomial {
    private List<PolyTerm> terms;

    public Polynomial(List<PolyTerm> terms) {
        if (terms == null) {
            throw new IllegalArgumentException("terms is null.");
        }
        if (terms.size() == 0) {
            throw new IllegalArgumentException("terms must be a non-empty list.");
        }

        this.terms = terms;
    }

    public Polynomial add(Polynomial poly) {
        if (poly == null) {
            throw new IllegalArgumentException("poly is null.");
        }

        PolynomialBuilder builder = new PolynomialBuilder();
        this.addTerms(builder);
        poly.addTerms(builder);

        return builder.build();
    }

    private void addTerms(PolynomialBuilder builder) {
        for (int i = 0; i < this.terms.size(); i++) {
            PolyTerm polyTerm = this.terms.get(i);
            builder.addPolyTerm(polyTerm);
        }
    }

    public Polynomial mul(Polynomial poly) {
        if (poly == null) {
            throw new IllegalArgumentException("poly is null.");
        }

        PolynomialBuilder builder = new PolynomialBuilder();
        for (int i = 0; i < this.terms.size(); i++) {
            PolyTerm thisPolyTerm = this.terms.get(i);

            for (int j = 0; j < poly.terms.size(); j++) {
                PolyTerm otherPolyTerm = poly.terms.get(j);

                PolyTerm resultPolyTerm = thisPolyTerm.mul(otherPolyTerm);
                builder.addPolyTerm(resultPolyTerm);
            }
        }

        return builder.build();
    }

    public Scalar evaluate(Scalar scalar) {
        if (scalar == null) {
            throw new IllegalArgumentException("scalar is null.");
        }

        Scalar result = this.terms.get(0).evaluate(scalar);
        for (int i = 1; i < this.terms.size(); i++) {
            Scalar tmp = this.terms.get(i).evaluate(scalar);
            result = result.add(tmp);
        }

        return result;
    }

    public Polynomial derivate() {
        List<PolyTerm> resultPolyTerms = new ArrayList<>();
        for (int i = 0; i < this.terms.size(); i++) {
            PolyTerm polyTerm = this.terms.get(i);
            PolyTerm derivatedPolyTerm = polyTerm.derivate();

            if (!ScalarUtils.isZero(derivatedPolyTerm.getCoefficient())) {
                resultPolyTerms.add(derivatedPolyTerm);
            }
        }

        return new Polynomial(resultPolyTerms);
    }

    @Override
    public String toString() {
        this.terms.sort((a, b) -> Integer.compare(a.getExponent(), b.getExponent()));

        StringBuilder sb = new StringBuilder();

        // append the first term
        sb.append(this.terms.get(0).toString());

        // append the rest
        for (int i = 1; i < this.terms.size(); i++) {
            PolyTerm polyTerm = this.terms.get(i);
            String termString = polyTerm.toString();
            if (termString.charAt(0) != '-') {
                sb.append('+');
            }

            sb.append(polyTerm.toString());
        }

        return sb.toString();
    }

    public boolean equals(Polynomial poly) {
        if (poly == null) {
            throw new IllegalArgumentException("poly is null.");
        }

        boolean result = true;
        Map<Integer, PolyTerm> polyTermMap = new HashMap<>();
        for (int i = 0; i < this.terms.size(); i++) {
            PolyTerm polyTerm = this.terms.get(i);
            int exponent = polyTerm.getExponent();

            polyTermMap.put(exponent, polyTerm);
        }

        for (int i = 0; i < poly.terms.size(); i++) {
            PolyTerm polyTerm = poly.terms.get(i);
            int exponent = polyTerm.getExponent();

            if (!polyTermMap.containsKey(exponent)) {
                result = false;
                break;
            }

            PolyTerm otherPolyTerm = polyTermMap.get(exponent);
            if (!polyTerm.getCoefficient().equals(otherPolyTerm.getCoefficient())) {
                result = false;
                break;
            }
        }

        return result;
    }
}
