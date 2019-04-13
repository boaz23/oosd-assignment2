package polycalc;

public class PolyTerm {
    private Scalar coefficient;
    private int exponent;

    public PolyTerm(Scalar coefficient, int exponent) {
        if (coefficient == null) {
            throw new IllegalArgumentException("coefficient is null.");
        }
        if (exponent < 0) {
            throw new IllegalArgumentException("exponent must be a non-negative number.");
        }

        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Scalar getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Scalar coefficient) {
        if (coefficient == null) {
            throw new IllegalArgumentException("coefficient is null.");
        }

        this.coefficient = coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public boolean canAdd(PolyTerm polyTerm) {
        if (polyTerm == null) {
            throw new IllegalArgumentException("polyTerm is null.");
        }

        return this.getExponent() == polyTerm.getExponent();
    }

    public PolyTerm add(PolyTerm polyTerm) {
        if (polyTerm == null) {
            throw new IllegalArgumentException("pt is null.");
        }
        if (!this.canAdd(polyTerm)) {
            throw new UnsupportedOperationException("The polyterms must be of the same power.");
        }

        Scalar newCoefficient = this.getCoefficient().add(polyTerm.getCoefficient());
        return new PolyTerm(newCoefficient, this.getExponent());
    }

    public PolyTerm mul(PolyTerm polyTerm) {
        if (polyTerm == null) {
            throw new IllegalArgumentException("polyTerm is null.");
        }

        Scalar newCoefficient = this.getCoefficient().mul(polyTerm.getCoefficient());
        int newExponent = this.getExponent() + polyTerm.getExponent();
        return new PolyTerm(newCoefficient, newExponent);
    }

    public Scalar evaluate(Scalar scalar) {
        if (scalar == null) {
            throw new IllegalArgumentException("scalar is null.");
        }

        return this.getCoefficient().mul(scalar.pow(this.getExponent()));
    }

    public PolyTerm derivate() {
        Scalar newCoefficient = this.getCoefficient().mul(this.getExponent());
        return new PolyTerm(newCoefficient, this.getExponent() - 1);
    }

    public boolean equals(PolyTerm polyTerm) {
        if (polyTerm == null) {
            throw new IllegalArgumentException("polyTerm is null.");
        }

        return this.getExponent() == polyTerm.getExponent() &
               this.getCoefficient().equals(polyTerm.getCoefficient());
    }

    @Override
    public String toString() {
        String s = this.getCoefficient().toString();
        if (this.getExponent() > 0) {
            s += "x^" + this.getExponent();
        }

        return s;
    }
}
