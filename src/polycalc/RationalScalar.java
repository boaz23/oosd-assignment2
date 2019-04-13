package polycalc;

public class RationalScalar extends ScalarBase {
    private int a;
    private int b;

    public static final Scalar Zero = new RationalScalar(0, 1);
    public static final Scalar One = new RationalScalar(1, 1);

    public RationalScalar(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("The denominator cannot be 0.");
        }

        if (a == 0) {
            b = 1;
        }

        // if they're both negative or the denominator is negative,
        // change both's sign
        if ((a < 0 & b < 0) | (a > 0 & b < 0)) {
            a *= -1;
            b *= -1;
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
    public Scalar clone() {
        return new RationalScalar(this.getA(), this.getB());
    }

    @Override
    public Scalar add(Scalar s) {
        if (s == null) {
            throw new IllegalArgumentException("s is null.");
        }

        RationalScalar other = (RationalScalar)s;
        return new RationalScalar((this.getA() * other.getB()) + (this.getB() * other.getA()),
                                  this.getB() * other.getB());
    }

    @Override
    public Scalar mul(Scalar s) {
        if (s == null) {
            throw new IllegalArgumentException("s is null.");
        }

        RationalScalar other = (RationalScalar)s;
        return new RationalScalar(this.getA() * other.getA(),
                                  this.getB() * other.getB());
    }

    @Override
    public Scalar mul(int n) {
        return this.mul(new RationalScalar(n, 1));
    }

    @Override
    public Scalar neg() {
        return new RationalScalar(-this.getA(), this.getB());
    }

    @Override
    public String toString() {
        return this.getA() + "/" + this.getB();
    }

    @Override
    public boolean equals(Scalar s) {
        RationalScalar other = (RationalScalar)s;
        return this.getA() == other.getA() &
               this.getB() == other.getB();
    }

    @Override
    Scalar getOne() {
        return One;
    }
}
