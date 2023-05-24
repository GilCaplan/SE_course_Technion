package PartB;

public class Sum extends Function{
    private final Function f1, f2;//sum of 2 functions
    public Sum(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    /**
     * @param x is a real number
     * @return f1(x)+f2(x)
     */
    @Override
    public int valueAt(int x) {
        return f1.valueAt(x) + f2.valueAt(x);
    }

    /**
     * @return f1(x) + f2(x)
     */
    @Override
    public String toString() {
        return f1.toString() + " + " + f2.toString();
    }

    /**
     * @return if both are constants then 0 otherwise f1'(x)+f2'(x)
     */
    @Override
    public Function derivative() {
        if(f1 instanceof Constant && f2 instanceof Constant)
            return new Constant(0);
        return new Sum(f1.derivative(), f2.derivative());
    }

    @Override
    public int bisectionMethod(int a, int b, double epsilon) {
        return super.bisectionMethod(a, b, epsilon);
    }

    @Override
    public int bisectionMethod(int a, int b) {
        return super.bisectionMethod(a, b);
    }

    @Override
    public int newtonRaphsonMethod(int a, double epsilon) {
        return super.newtonRaphsonMethod(a, epsilon);
    }

    @Override
    public int newtonRaphsonMethod(int a) {
        return super.newtonRaphsonMethod(a);
    }

    /**
     * @param n
     * @return
     */
    @Override
    public Polynomial taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
