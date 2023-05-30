package PartB;

public class Product extends Function{
    private final Function f1;
    private final Function f2;

    public Product(Function f1, Function f2) {
        this.f1=f1;
        this.f2=f2;
    }

    /**
     * @param x is a real number
     * @return f1(x) * f2(x)
     */
    @Override
    public double valueAt(double x) {
        return this.f1.valueAt(x) * this.f2.valueAt(x);
    }

    /**
     * @return the string of the product
     */
    @Override
    public String toString() {
        if(this.f2 instanceof X && this.f1 instanceof Constant)
            return "(" + (int)((Constant) this.f1).getConstant() + "x)";
        return "(" + this.f1 +" * " + this.f2 + ")";
    }

    /**
     * @return calculate (numerator)'*(denominator) and then (numerator)*(denominator)'
     */
    @Override
    public Function derivative() {
        Function d1, d2;
        d1 = new Product(this.f1.derivative(), this.f2);
        d2 = new Product(this.f2.derivative(), this.f1);
        return new Sum(d1, d2);
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
