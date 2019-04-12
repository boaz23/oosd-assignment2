package polycalc;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RationalScalar implements Scalar {
    private int a;
    private int b;

    @Override
    public Scalar add(Scalar s) {
        throw new NotImplementedException();
    }

    @Override
    public Scalar mul(Scalar s) {
        throw new NotImplementedException();
    }

    @Override
    public Scalar pow(Scalar s) {
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
