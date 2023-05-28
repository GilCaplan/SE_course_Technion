package PartB;

public class Polynomial extends Function{
    private final Function[] functions;

    public Polynomial(Function[] functions, double[] an){
        this.functions = new Function[an.length];
        for(int i=0; i< an.length; i++){
            this.functions[i] = new Product(new Constant(an[i]), functions[i]);
        }
    }
    public Polynomial(double... an){
        this.functions = new Function[an.length];
        for(int i=0; i< an.length; i++){
            this.functions[i] = new Product(new Constant(an[i]), new Power(new X(), i));
        }
    }
    public Polynomial(Function[] functions){
        this.functions = functions;
        System.arraycopy(functions, 0, this.functions, 0, functions.length);
    }
    /**
     * @param x is a real number
     * @return f(x) value
     */
    @Override
    public double valueAt(double x) {
        double sum = 0;
        for (Function function : functions)
            sum += function.valueAt(x);
        return sum;
    }

    /**
     * @return a0*f(x) + a1*f(x) + ... + an*f(x) - double check f(x) contains x^n
     */
    @Override
    public String toString() {
        String fStr = "" ;
        for(int i=1; i< functions.length; i++){
            fStr += functions[i].toString() + " + ";
        }
        return fStr;
    }

    /**
     * @return a0*f'(x) + a1*f'(x) + ... + an*f'(x)
     */
    @Override
    public Polynomial derivative() {
        Function[] derivative = new Function[functions.length];
        for(int i=0; i< functions.length; i++){
            derivative[i] = functions[i].derivative();
        }
        return new Polynomial(derivative);
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
    public Polynomial taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
