package PartB;

public class Constant extends Function{

    public Constant(double v) {
        super();
    }

    /**
     * @param x
     * @return
     */
    @Override
    public int valueAt(int x) {
        return 0;
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
    public int bisectionMethod(int a, int b, int epsilon) {
        return 0;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    @Override
    public int bisectionMethod(int a, int b) {
        return 0;
    }

    /**
     * @param a
     * @param epsilon
     * @return
     */
    @Override
    public int newtonRaphsonMethod(int a, int epsilon) {
        return 0;
    }

    /**
     * @param a
     * @return
     */
    @Override
    public int newtonRaphsonMethod(int a) {
        return 0;
    }

    /**
     * @param n
     * @return
     */
    @Override
    public Function taylorPolynomial(int n) {
        return null;
    }
}
