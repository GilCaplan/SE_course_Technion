package PartB;

public class Product extends Function{
    private Function f1;
    private Function f2;
    public Product(Function f1, Function f2) {
        super();
    }

    /**
     * @param x is a real number
     * @return the product of f1 and f2
     */
    @Override
    public double valueAt(double x) {
        double a = this.f1.valueAt(x);
        double b = this.f2.valueAt(x);
        return a*b;
    }

    /**
     * @return the string of the product
     */
    @Override
    public String toString() {
        String str = "";
        str+=this.f1.toString()+"*";
        str+=this.f2.toString();
        return str;
    }

    /**
     * @return calculate (numerator)'*(denominator) and then (numerator)*(denominator)'
     */
    @Override
    public Function derivative() {
        Function[] derivative=new Function[2];
        derivative[0]= new Product(this.f1,this.f2.derivative());
        derivative[1]= new Product(this.f1.derivative(),this.f2);
        return new Polynomial(derivative);
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
