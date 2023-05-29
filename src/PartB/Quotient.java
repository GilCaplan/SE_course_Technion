package PartB;

public class Quotient extends Function{
    private final Function numerator;
    private final Function denominator;
    public Quotient(Function numerator, Function denominator){
        this.numerator = numerator;
//        if(denominator.equals(new Constant(0)))
//            throw new RuntimeException("can't divide by 0");
        this.denominator = denominator;
    }

    /**
     * @param x is a real number
     * @return f(x) / g(x) or numerator/denominator at coordinates x
     */
    @Override
    public double valueAt(double x) {
//        if(this.denominator.valueAt(x) == 0)
//            throw new RuntimeException("can't divide by 0");
        return this.numerator.valueAt(x) / this.denominator.valueAt(x);
    }

    /**
     * @return (nominator)/(denominator)
     */
    @Override
    public String toString() {
        return "(" + this.numerator + " / " + this.denominator.toString() + ")";
    }

    /**
     * returns (f'(x)*g(x) - g'(x)*f(x)) / (g(x)^2))
     */
    @Override
    public Function derivative() {
        Function fxTag = numerator.derivative(), gxTag = denominator.derivative();
        Function numDerivative1 = new Product(fxTag, denominator);
        Function numDerivative2 = new Product(gxTag, numerator);
        Function denSquared = new Power(denominator, 2);
        return new Quotient(new Difference(numDerivative1, numDerivative2), denSquared);
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
