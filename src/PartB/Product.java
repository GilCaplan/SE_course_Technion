package PartB;

public class Product extends Function{
    private final Function f1;
    private final Function f2;
    private final boolean contains0;

    public boolean isContains0() {
        return contains0;
    }

    public Product(Function f1, Function f2) {
        this.f1=f1;
        this.f2=f2;
        boolean flag1 = f1 instanceof Product && ((Product) f1).isContains0();
        boolean flag2 = f2 instanceof Product && ((Product) f2).isContains0();
        boolean flag3 = this.f1.toString().equals("0") || this.f2.toString().equals("0");
        this.contains0 = flag1 || flag2 || flag3;
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
        if(this.f1.equals(new Constant(0))||this.f2.equals(new Constant(0)))
            return "0";
        if(this.f1.toString().equals("1"))
            return f2.toString();
        if(this.f2.toString().equals("1"))
            return f1.toString();
        return "(" + this.f1 +" * "+this.f2 + ")";
    }

    /**
     * @return calculate (numerator)'*(denominator) and then (numerator)*(denominator)'
     */
    @Override
    public Function derivative() {
        Function d1, d2;
        d1 = new Product(this.f1, this.f2.derivative());
        d2 = new Product(this.f2, this.f1.derivative());
        return new Product(d2, d1);
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
