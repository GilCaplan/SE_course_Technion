package PartB;

public class Sum extends Function{
    private final Function f1, f2;//sum of 2 functions
    public Sum(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    /**
     * @param x is a real number
     * @return f1(x)+f2(x)
     */
    @Override
    public double valueAt(double x) {
        return f1.valueAt(x) + f2.valueAt(x);
    }

    /**
     * @return f1(x) + f2(x)
     */
    @Override
    public String toString() {
        return "(" + f1.toString() + " + " + f2.toString() + ")";
    }

    /**
     * @return if both are constants then 0 otherwise f1'(x)+f2'(x)
     */
    @Override
    public Function derivative() {
        return new Sum(f1.derivative(), f2.derivative());
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
    public Function taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}