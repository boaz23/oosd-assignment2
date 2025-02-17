package polycalc.logic;

import java.text.DecimalFormat;

public class RealScalar extends ScalarBase {
    private double value;

    public static final Scalar Zero = new RealScalar(0.0);
    public static final Scalar One = new RealScalar(1.0);

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
    public Scalar clone() {
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
    protected Scalar powCore(int exponent) {
        return new RealScalar(Math.pow(this.getValue(), exponent));
    }

    @Override
    public Scalar neg() {
        return new RealScalar(-this.getValue());
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.###");
        return format.format(this.getValue());
    }

    @Override
    public boolean equals(Scalar s) {
        RealScalar other = (RealScalar)s;
        return this.getValue() == other.getValue();
    }
}
