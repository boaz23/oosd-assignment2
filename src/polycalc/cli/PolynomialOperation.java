package polycalc.cli;

public interface PolynomialOperation {
    String getName();
    boolean shouldExit();
    void run(NumberField numberField);
}