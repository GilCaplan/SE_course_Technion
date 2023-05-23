package PartB;

public class Sum extends Function{//sum of 2 functions
    private Function f1, f2;
    public Sum(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    /**
     * @param x
     * @return
     */
    @Override
    public int valueAt(int x) {
        return f1.valueAt(x) + f2.valueAt(x);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Function derivative() {
        return new Sum(f1.derivative(), f2.derivative());
    }

    /**
     * @param a
     * @param b
     * @param epsilon
     * @return
     */
    @Override
    public int bisectionMethod(int a, int b, double epsilon) {
        return super.bisectionMethod(a, b, epsilon);
    }

    /**
     * @param a
     * @param b
     * @return
     */
    @Override
    public int bisectionMethod(int a, int b) {
        return super.bisectionMethod(a, b);
    }

    /**
     * @param a
     * @param epsilon
     * @return
     */
    @Override
    public int newtonRaphsonMethod(int a, double epsilon) {
        return super.newtonRaphsonMethod(a, epsilon);
    }

    /**
     * @param a
     * @return
     */
    @Override
    public int newtonRaphsonMethod(int a) {
        return super.newtonRaphsonMethod(a);
    }

    /**
     * @param n
     * @return
     */
    @Override
    public Function taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
