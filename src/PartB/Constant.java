package PartB;

public class Constant extends Function{
    private final double constant;
    public Constant(double number){
        this.constant = number;
    }

    /**
     * @param x is a real number
     * @return
     */
    @Override
    public double valueAt(double x) {
        return this.constant*x;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(this.constant);
    }

    /**
     * @return
     */
    @Override
    public Function derivative() {
        return new Constant(0);
    }

    @Override
    public double bisectionMethod(double a, double b) {
        return super.bisectionMethod(a, b);
    }

    @Override
    public double bisectionMethod(double a, double b, double epsilon) {
        return super.bisectionMethod(a, b, epsilon);
    }

    @Override
    public double newtonRaphsonMethod(double a) {
        return super.newtonRaphsonMethod(a);
    }

    @Override
    public double newtonRaphsonMethod(double a, double epsilon) {
        return super.newtonRaphsonMethod(a, epsilon);
    }

    @Override
    public Polynomial taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
