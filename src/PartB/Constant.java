package PartB;

public class Constant extends Function{
    //see if constructor and/or attributes need changing
    //write code and fill in javadoc where needed - Amir
    private int constant;
    public Constant(int number){
        this.constant = number;
    }

    public Constant(double v) {
        super();
    }

    /**
     * @param x is a real number
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

    @Override
    public int bisectionMethod(int a, int b, double epsilon) {
        return super.bisectionMethod(a, b, epsilon);
    }

    @Override
    public int bisectionMethod(int a, int b) {
        return 0;
    }

    @Override
    public int newtonRaphsonMethod(int a, double epsilon) {
        return super.newtonRaphsonMethod(a, epsilon);
    }

    @Override
    public int newtonRaphsonMethod(int a) {
        return 0;
    }

    @Override
    public Polynomial taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
