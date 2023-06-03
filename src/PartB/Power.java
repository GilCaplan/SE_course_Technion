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
        return pow(this.f.valueAt(x), this.n);
    }

    /**
     * @return (f(x))^n
     */
    @Override
    public String toString() {
        if (this.n == 0)
            return "1";
        return"("+this.f.toString()+"^"+this.n +")";
    }

    /**
     * @return new function that is a derivative of the current function, f'(x) = n*f(x)'*(f(x)^(n-1))
     */
    @Override
    public Function derivative() {
        return new MultiProduct(new Constant(this.n), new Power(this.f, n-1), this.f.derivative());//(n-1)*f'*f
    }
}
