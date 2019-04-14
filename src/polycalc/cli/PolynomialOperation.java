package polycalc.cli;

public interface PolynomialOperation {
    boolean shouldExit();
    void run(NumberField numberField);
}