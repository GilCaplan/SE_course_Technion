package PartB;

public class Negation extends Function{
    private Function originalFunc;
    private Function negatedFunc;
    public Negation(Function f, Negation negatedFunc){
        this.originalFunc = f;
        this.negatedFunc = negatedFunc;
    }
    /**
     * @param x
     * @return
     */
    @Override
    public int valueAt(int x) {
        return -1/this.originalFunc.valueAt(x);
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
        return null;
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
    public Polynomial taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
