package PartB;

public class Quotient extends Function{
    private Function numerator;
    private Function denominator;
    public Quotient(Function numerator,Function denominator){
        this.numerator=numerator;
        this.denominator=denominator;
        return;
    }



    /**
     * @param x is a real number
     * @return
     */
    @Override
    public double valueAt(double x) {
        double numerator1=this.numerator.valueAt(x);
        double denominator1=this.denominator.valueAt(x);
        return numerator1/denominator1;
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
        Function[] numeratorDev=new Function[2];
        Function[] denominatorDev=new Function[1];

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
