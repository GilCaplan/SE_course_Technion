package PartB;

public class Power extends Function{
    public double n;
    public Function f;
    public Power(double n, Function f){
        this.n = n;
        this.f = f;
    }
    /**
     * @param x
     * @return
     */
    @Override
    public int valueAt(int x) {
        int sum = 1;
        for(int i =1; i< this.n; i++){
            sum *= this.f.valueAt(x);
        }
        return sum;
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
        return new MultiProduct(new Constant(this.n-1), this.f, this.f.derivative());//(n-1)*f'*f
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
        return null;
    }
}
