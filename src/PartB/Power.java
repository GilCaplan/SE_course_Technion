package PartB;

public class Power extends Function{
    public int n;//power number
    public Function f;
    public Power(Function f, int n){
        this.n = n;
        this.f = f;
    }

    /**
     * @param x is a real number
     * @return f(x) based on x
     */
    @Override
    public double valueAt(double x) {
        double val = this.f.valueAt(x);
        return pow(val, this.n);
    }

    /**
     * @return (f(x))^n
     */
    @Override
    public String toString() {
        if(n == 0)
            return "1";
        if(n == 1)
            return f.toString();
        if(f instanceof X)
            return "x^" + this.n;
        return "("+f.toString()+"^" + this.n + ")";
    }

    /**
     * @return new function that is a derivative of the current function, f'(x) = n*f(x)'*(f(x)^(n-1))
     */
    @Override
    public Function derivative() {
        Function derivative = this.f.derivative();
        if(this.f instanceof X && this.n == 2){
            return new Product(new Constant(this.n), new X());
        }
        return new MultiProduct(new Constant(this.n), new Power(this.f, n-1), derivative);//(n-1)*f'*f
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
