package polycalc;

import polycalc.utils.ScalarUtils;

import java.util.*;

public class Polynomial {
    // terms with a coefficient of zero are allowed in the list,
    // however they don't have to.
    // moreover, two poly terms of the same exponent must NOT
    // be in the list.
    // furthermore, the list must NOT be empty. that means
    // that the constant 0 polynomial should be represent as
    // a single term with a coefficient of zero
    private List<PolyTerm> terms;

    Polynomial(List<PolyTerm> terms) {
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
        this.addPolyTerms(builder);
        poly.addPolyTerms(builder);

        return builder.build();
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
            resultPolyTerms.add(derivatedPolyTerm);
        }

        return new Polynomial(resultPolyTerms);
    }

    @Override
    public String toString() {
        // first sort so that we return a string in ascending exponent order
        this.terms.sort((a, b) -> Integer.compare(a.getExponent(), b.getExponent()));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.terms.size(); i++) {
            PolyTerm polyTerm = this.terms.get(i);
            sb.append(polyTerm);
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

        boolean result = true;
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

    private static class StringBuilder {
        private java.lang.StringBuilder sb;
        private boolean first;

        StringBuilder() {
            this.sb = new java.lang.StringBuilder();
            this.first = true;
        }

        void append(PolyTerm polyTerm) {
            if (!ScalarUtils.isZero(polyTerm.getCoefficient())) {
                String polyTermString = polyTerm.toString();
                if (first) {
                    // If the first term is positive, it should not have
                    // a '+' before it
                    first = false;
                }
                else {
                    // if the coefficient is negative, then '-' sign we'll be the
                    // first character, otherwise, it is positive and we need to
                    // add the '+' sign
                    if (polyTermString.charAt(0) != '-') {
                        sb.append('+');
                    }
                }

                sb.append(polyTermString);
            }
        }

        @Override
        public String toString() {
            return this.sb.toString();
        }
    }
}
