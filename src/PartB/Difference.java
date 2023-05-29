package PartB;

public class Difference extends Function{
    private final Function firstFun;
    private final Function secondFun;

    public Difference(Function firstFun, Function secondFun){
        this.firstFun = firstFun;
        this.secondFun = secondFun;
    }

    /**
     * @param x is a real number
     * @return firstFun(x) - secondFun(x)
     */
    @Override
    public double valueAt(double x) {
        return this.firstFun.valueAt(x) - this.secondFun.valueAt(x);
    }

    /**
     * @return the string of the func, (f1(x)) - (f2(x))
     */
    @Override
    public String toString() {
        return "("+this.firstFun.toString()+" - "+this.secondFun.toString()+")";
    }

    /**
     * @return the derivative of the func
     */
    @Override
    public Function derivative() {
        Function f1Derivative, f2Derivative;
        f1Derivative = this.firstFun.derivative();
        f2Derivative = this.secondFun.derivative();
        return new Difference(f1Derivative, f2Derivative);
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
