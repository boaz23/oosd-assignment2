package polycalc.logic;

abstract class ScalarBase implements Scalar {
    @Override
    public Scalar pow(int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("exponent must be a non-negative number.");
        }

        return this.powCore(exponent);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Scalar)) {
            return false;
        }

        return this.equals((Scalar)obj);
    }

    protected abstract Scalar powCore(int exponent);
    public abstract Scalar clone();
}
