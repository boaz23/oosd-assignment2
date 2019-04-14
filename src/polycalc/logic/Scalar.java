package polycalc.logic;

public interface Scalar {
    Scalar add(Scalar s);
    Scalar mul(Scalar s);
    Scalar mul(int n);
    Scalar pow(int exponent);
    Scalar neg();

    boolean equals(Scalar s);
    String toString();
    Scalar clone();
}
