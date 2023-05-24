package PartB;

public class Polynomial extends Function{
    private double[] an;
    private Function[] functions;

    public Polynomial(Function[] functions, double[] an){
        this.functions = functions;
        this.an = an;
    }
    /**
     * @param x
     * @return
     */
    @Override
    public int valueAt(int x) {
        int sum = 0;
        for(int i=0; i< functions.length; i++){
            sum += functions[i].valueAt(x)*an[i];
        }
        return sum;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        String fStr = " ";
        for(int i=0; i< functions.length; i++){
            fStr += an[i]+"*"+functions[i].toString() + " + ";
        }
        return fStr;
    }

    /**
     * @return
     */
    @Override
    public Function derivative() {
        Function[] derivative = new Function[functions.length];
        double[] bn = new double[functions.length];
        for(int i=0; i< functions.length; i++){
            bn[i]= an[i];
            derivative[i] = functions[i].derivative();
        }
        return new Polynomial(derivative, an);
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
     * @param currFunc
     */

    /**
     * @param n
     * @return
     */
    @Override
    public Polynomial taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
