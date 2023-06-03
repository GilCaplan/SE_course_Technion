package PartB;

public class Power extends Function {
    private final int n;//power number
    private final Function f;

    /**
     * make a new Power object (function) which is f^n
     * @param f our original function
     * @param n the degree to raise the given function f
     */
    public Power(Function f, int n) {
        this.n = n;
        this.f = f;
    }

    /**
     * @param x is a real number
     * @return f(x) based on x
     */
    @Override
    public double valueAt(double x) {
        double val = this.f.valueAt(x);
        return pow(val, this.n);
    }

    /**
     * @return (f(x))^n
     */
    @Override
    public String toString() {
        if (n == 0)
            return "1";
        return"("+f.toString()+"^"+this.n +")";
    }

    /**
     * @return new function that is a derivative of the current function, f'(x) = n*f(x)'*(f(x)^(n-1))
     */
    @Override
    public Function derivative() {
        return new MultiProduct(new Constant(this.n), new Power(this.f, n-1), this.f.derivative());//(n-1)*f'*f
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
