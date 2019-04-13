package polycalc;

abstract class ScalarBase implements Scalar {
    @Override
    public Scalar pow(int exponent) {
        if (exponent < 0) {
            throw new UnsupportedOperationException();
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
            Scalar tmp = this.pow(exponent / 2);
            result = tmp.mul(tmp);
        }
        else {
            result = this.mul(this.pow(exponent - 1));
        }

        return result;
    }

    protected abstract Scalar clone();
    abstract Scalar getOne();
}
