package polycalc;

public interface Scalar {
    Scalar add(Scalar s);
    Scalar mul(Scalar s);
    Scalar pow(Scalar s);
    Scalar neg();

    boolean equals(Scalar s);
}
