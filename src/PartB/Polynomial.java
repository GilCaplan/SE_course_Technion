package PartB;

public class Polynomial extends Function{
    private final double[] an;
    private final Function[] functions;

    public Polynomial(Function[] functions, double[] an){
        this.functions = functions;
        this.an = an;
    }
    public Polynomial(double... an){
        this.an = new double[an.length];
        this.functions = new Function[an.length];
        for(int i=0; i< an.length; i++){
            this.an[i] = an[i];
            this.functions[i] = new Power(new X(), i);
        }
    }
    public Polynomial(Function[] functions){
        this.functions = functions;
        this.an = new double[functions.length];
        for(int i=0; i<an.length; i++){
            this.an[i] = 1;
        }
    }
    /**
     * @param x is a real number
     * @return f(x) value
     */
    @Override
    public double valueAt(double x) {
        double sum = 0;
        for(int i=0; i< functions.length; i++){
            sum += functions[i].valueAt(x) * an[i];
        }
        return sum;
    }

    /**
     * @return a0*f(x) + a1*f(x) + ... + an*f(x) - double check f(x) contains x^n
     */
    @Override
    public String toString() {
        String fStr = " " + (an[0]!=0 ? an[0] : "");
        for(int i=1; i< functions.length; i++){
            fStr += (an[i]==1 ? "" : an[i]) + "*" + functions[i].toString() + " + ";
        }
        return fStr;
    }

    /**
     * @return a0*f'(x) + a1*f'(x) + ... + an*f'(x)
     */
    @Override
    public Function derivative() {
        Function[] derivative = new Function[functions.length];
        for(int i=0; i< functions.length; i++){
            derivative[i] = functions[i].derivative();
        }
        return new Polynomial(derivative, an);
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
