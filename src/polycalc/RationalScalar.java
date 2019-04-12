package polycalc;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RationalScalar implements Scalar {
    private int a;
    private int b;

    public RationalScalar(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("The denominator cannot be 0.");
        }

        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
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
