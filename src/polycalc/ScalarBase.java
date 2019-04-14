package polycalc;

abstract class ScalarBase implements Scalar {
    @Override
    public Scalar pow(int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("exponent must be a non-negative number.");
        }

        return powCore(exponent);
    }

    private Scalar powCore(int exponent) {
        Scalar result;
        if (exponent == 0) {
            result = this.getOne();
        }
        else if (exponent == 1) {
            result = this.clone();
        }
        else if (exponent % 2 == 0) {
            Scalar tmp = this.powCore(exponent / 2);
            result = tmp.mul(tmp);
        }
        else {
            result = this.mul(this.powCore(exponent - 1));
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Scalar)) {
            return false;
        }

        return this.equals((Scalar)obj);
    }

    public abstract Scalar clone();
    abstract Scalar getOne();
}
