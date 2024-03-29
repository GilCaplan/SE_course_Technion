package PartB;

public class Product extends Function{
    private final Function f1;
    private final Function f2;

    /**
     * make a Product object (function) that is the product (multiplication) of two functions (f1*f2)
     * @param f1 is a function
     * @param f2 is a function
     */
    public Product(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
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
}
