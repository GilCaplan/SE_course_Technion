package PartB;

public class Power extends Function{
    public double n;//power number
    public Function f;
    public Power(Function f, double n){
        this.n = n;
        this.f = f;
    }

    /**
     * @param x is a real number
     * @return f(x) based on x
     */
    @Override
    public double valueAt(double x) {
        double sum = 1;
        for(int i =1; i< this.n; i++){
            sum *= this.f.valueAt(x);
        }
        return sum;
    }

    /**
     * @return (f(x))^n
     */
    @Override
    public String toString() {
        if(f instanceof X && n == 0)
            return "1";
        return "("+f.toString()+")^" + this.n;
    }

    /**
     * @return new function that is a derivative of the current function, f'(x) = n*f(x)'*(f(x)^(n-1))
     */
    @Override
    public Function derivative() {
        return new MultiProduct(new Constant(this.n), new Power(this.f, n-1), this.f.derivative());//(n-1)*f'*f
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
