package polycalc;

public class RealScalar extends ScalarBase {
    private double value;

    public static final Scalar Zero = new RealScalar(0);
    public static final Scalar One = new RealScalar(1);

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
    protected Scalar clone() {
        return new RealScalar(this.getValue());
    }

    @Override
    public Scalar add(Scalar s) {
        RealScalar other = (RealScalar)s;
        return new RealScalar(this.getValue() + other.getValue());
    }

    @Override
    public Scalar mul(Scalar s) {
        RealScalar other = (RealScalar)s;
        return new RealScalar(this.getValue() * other.getValue());
    }

    @Override
    public Scalar mul(int n) {
        return new RealScalar(this.getValue() * n);
    }

    @Override
    public Scalar neg() {
        return new RealScalar(-this.getValue());
    }

    @Override
    public boolean equals(Scalar s) {
        RealScalar other = (RealScalar)s;
        return this.getValue() == other.getValue();
    }

    @Override
    Scalar getOne() {
        return One;
    }
}
