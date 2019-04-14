package polycalc.logic;

import polycalc.utils.ScalarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Polynomial {
    // Terms with a coefficient of zero are NOT allowed in the list.
    // Moreover, only one poly term of a specific exponent is allowed.
    // Furthermore, the list can be empty.
    // The only way to represent the constant polynomial 0,
    // is an empty list
    private List<PolyTerm> terms;

    Polynomial(List<PolyTerm> terms) {
        if (terms == null) {
            throw new IllegalArgumentException("terms is null.");
        }

        this.terms = terms;
    }

    public Polynomial add(Polynomial poly) {
        if (poly == null) {
            throw new IllegalArgumentException("poly is null.");
        }

        PolynomialBuilder builder = new PolynomialBuilder();
        this.addPolyTerms(builder);
        poly.addPolyTerms(builder);

        return builder.build();
    }

    public Polynomial mul(Polynomial poly) {
        if (poly == null) {
            throw new IllegalArgumentException("poly is null.");
        }

        Polynomial result;

        // optimization
        if (this.terms.size() == 0 | poly.terms.size() == 0) {
            result = new Polynomial(new ArrayList<>(0));
        }
        else {
            PolynomialBuilder builder = new PolynomialBuilder();
            for (int i = 0; i < this.terms.size(); i++) {
                PolyTerm thisPolyTerm = this.terms.get(i);

                for (int j = 0; j < poly.terms.size(); j++) {
                    PolyTerm otherPolyTerm = poly.terms.get(j);

                    PolyTerm resultPolyTerm = thisPolyTerm.mul(otherPolyTerm);
                    builder.addPolyTerm(resultPolyTerm);
                }
            }

            result = builder.build();
        }

        return result;
    }

    public Scalar evaluate(Scalar scalar) {
        if (scalar == null) {
            throw new IllegalArgumentException("scalar is null.");
        }

        Scalar result;

        // this is a constant zero and we have no way
        // to produce a scalar of zero otherwise
        if (this.terms.size() == 0) {
            result = scalar.mul(0);
        }
        else {
            result = this.terms.get(0).evaluate(scalar);
            for (int i = 1; i < this.terms.size(); i++) {
                Scalar tmp = this.terms.get(i).evaluate(scalar);
                result = result.add(tmp);
            }
        }

        return result;
    }

    public Polynomial derivate() {
        PolynomialBuilder builder = new PolynomialBuilder();
        for (int i = 0; i < this.terms.size(); i++) {
            PolyTerm polyTerm = this.terms.get(i);
            PolyTerm derivatedPolyTerm = polyTerm.derivate();
            builder.addPolyTerm(derivatedPolyTerm);
        }

        return builder.build();
    }

    @Override
    public String toString() {
        // first sort so that we return a string in ascending exponent order
        this.terms.sort((a, b) -> Integer.compare(a.getExponent(), b.getExponent()));

        StringBuilder sb = new StringBuilder();
        if (this.terms.size() > 0) {
            // If the first term is positive, it should not have
            // a '+' before it. That's why we need to treat it
            // specifically
            PolyTerm polyTerm = this.terms.get(0);
            sb.append(polyTerm);

            for (int i = 1; i < this.terms.size(); i++) {
                polyTerm = this.terms.get(i);
                String polyTermString = polyTerm.toString();

                // if the coefficient is negative, then '-' sign we'll be the
                // first character, otherwise, it is positive and we need to
                // add the '+' sign
                if (polyTermString.charAt(0) != '-') {
                    sb.append('+');
                }

                sb.append(polyTermString);
            }
        }

        String s = sb.toString();

        // since zeros are never added to the string
        // and the polynomial is a constant zero,
        // we need to manually change the return string
        if (s.equals("")) {
            s = "0";
        }

        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Polynomial)) {
            return false;
        }

        return this.equals((Polynomial)obj);
    }

    public boolean equals(Polynomial poly) {
        if (poly == null) {
            throw new IllegalArgumentException("poly is null.");
        }

        // if the number of terms (without a coefficient of zero)
        // is different, then they can't be equal
        boolean result = this.terms.size() == poly.terms.size();

        // otherwise (either they both have no terms or both have at least one),
        // act only if they both have at least 1 term
        if (result & this.terms.size() > 0) {
            Map<Integer, PolyTerm> polyTermsMap = new HashMap<>();
            this.addPolyTerms(polyTermsMap);

            for (int i = 0; i < poly.terms.size() & result; i++) {
                PolyTerm polyTerm = poly.terms.get(i);
                int exponent = polyTerm.getExponent();

                if (!polyTermsMap.containsKey(exponent)) {
                    // The exponent doesn't exist in our (this) list,
                    // that means the coefficient is zero
                    result = ScalarUtils.isZero(polyTerm.getCoefficient());
                }
                else {
                    // The exponent exists in our (this) list
                    // compare the two coefficients
                    PolyTerm otherPolyTerm = polyTermsMap.get(exponent);
                    if (!polyTerm.getCoefficient().equals(otherPolyTerm.getCoefficient())) {
                        result = false;
                    }
                }
            }
        }

        return result;
    }

    private void addPolyTerms(PolynomialBuilder builder) {
        for (int i = 0; i < this.terms.size(); i++) {
            PolyTerm polyTerm = this.terms.get(i);
            builder.addPolyTerm(polyTerm);
        }
    }

    private void addPolyTerms(Map<Integer, PolyTerm> polyTermMap) {
        for (int i = 0; i < this.terms.size(); i++) {
            PolyTerm polyTerm = this.terms.get(i);
            int exponent = polyTerm.getExponent();

            polyTermMap.put(exponent, polyTerm);
        }
    }
}
