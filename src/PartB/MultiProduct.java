package PartB;

public class MultiProduct extends Function{
    private Function[] functions;
    private Function f;
    private Function[] derivative;
    public MultiProduct(Function... functions){
//        super(null, null);
        this.functions = functions;
        this.derivative = new Function[this.functions.length];
    }



    /**
     * @param x
     * @return
     */
    @Override
    public int valueAt(int x) {
        double sum = 1;
        for(Function function : functions)
            sum *= function.valueAt(x);
        return (int)sum;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        String str = "";
        for (Function function : functions)
            str += function.toString();
        return str;
    }


    /**
     * @return
     */
    @Override
    public Function derivative() {
        for(int i=0; i < this.functions.length; i++){
            this.derivative[i] = this.functions[i].derivative();
//            Function s = new Sum();
        }
        return null;
    }

    /**
     * @param a
     * @param b
     * @param epsilon
     * @return
     */
    public int bisectionMethod(int a, int b, int epsilon) {
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
