package PartB;

public class Quotient extends Function{
    private final Function numerator;
    private final Function denominator;

    /**
     * Builds a Quotient (function) object given two functions such that we get f/g
     * @param numerator is a function which represents the numerator
     * @param denominator is a function which represents the denominator
     */
    public Quotient(Function numerator, Function denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * @param x is a real number
     * @return f(x) / g(x) (numerator/denominator) value at coordinates x
     */
    @Override
    public double valueAt(double x) {
        return this.numerator.valueAt(x) / this.denominator.valueAt(x);
    }

    /**
     * @return (nominator)/(denominator) as a string
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
        Function fxTag = this.numerator.derivative(), gxTag = this.denominator.derivative();
        Function numDerivative1 = new Product(fxTag, this.denominator);
        Function numDerivative2 = new Product(gxTag, this.numerator);
        Function denSquared = new Power(this.denominator, 2);
        return new Quotient(new Difference(numDerivative1, numDerivative2), denSquared);
    }
}
