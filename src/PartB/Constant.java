package PartB;

public class Constant extends Function{
    //see if constructor and/or attributes need changing
    //write code and fill in javadoc where needed - Amir
    private int constant;
    public Constant(int number){
        this.constant = number;
    }

    public Constant(double v) {
        super();
    }

    /**
     * @param x is a real number
     * @return
     */
    @Override
    public double valueAt(double x) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Function derivative() {
        return null;
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
