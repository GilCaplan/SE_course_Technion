package PartB;

public class Negation extends Function{
    private final Function function;
    public Negation(Function f){
        this.function = new Product(new Constant(-1), f);
    }
    /**
     * @param x is a real number
     * @return -f(x)
     */
    @Override
    public int valueAt(int x) {
        return this.function.valueAt(x);
    }

    /**
     * @return -f(x)
     */
    @Override
    public String toString() {
        return this.function.toString();
    }

    /**
     * @return -f'(x)
     */
    @Override
    public Function derivative() {
        return new Product(new Constant(-1), this.function.derivative());
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

    @Override
    public Polynomial taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
