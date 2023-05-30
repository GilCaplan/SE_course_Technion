package PartB;

public class Constant extends Function{
    private final double constant;
    public Constant(double number){
        this.constant = number;
    }

    /**
     * @param x is a real number
     * @return constant number as x does not affect the change
     */
    @Override
    public double valueAt(double x) {
        return this.constant;
    }

    /**
     * @return (this.constant)
     */
    @Override
    public String toString() {
        if((double)((int)this.constant) == this.constant)
            return "("+(int)this.constant+")";
        return "("+this.constant+")";
    }

    public double getConstant() {
        return this.constant;
    }

    /**
     * @return Constant 0 as the derivative of a constant number is 0.
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
        return new Polynomial(true, this.constant);
    }
}
