package PartB;

public class Negation extends Function{

    private final Function function;

    public Negation(Function f){
        this.function = f;
    }
    /**
     * @param x is a real number
     * @return -f(x)
     */
    @Override
    public double valueAt(double x) {
        return -1 * this.function.valueAt(x);
    }

    /**
     * @return -f(x)
     */
    @Override
    public String toString() {
        return "(-" + this.function.toString()+")";
    }

    /**
     * @return -f'(x)
     */
    @Override
    public Function derivative() {
        return new Negation(this.function.derivative());
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
