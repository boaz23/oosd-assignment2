package polycalc;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RealScalar implements Scalar {
    private double value;

    public RealScalar(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public Scalar add(Scalar s) {
        throw new NotImplementedException();
    }

    @Override
    public Scalar mul(Scalar s) {
        throw new NotImplementedException();
    }

    @Override
    public Scalar mul(int n) {
        throw new NotImplementedException();
    }

    @Override
    public Scalar pow(int exponent) {
        throw new NotImplementedException();
    }

    @Override
    public Scalar neg() {
        throw new NotImplementedException();
    }

    @Override
    public boolean equals(Scalar s) {
        throw new NotImplementedException();
    }
}
