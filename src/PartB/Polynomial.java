package PartB;

public class Polynomial extends Function{
    private final Function[] functions;

    public Polynomial(double... an){
        int cnt=0;
        //cnt how many none 0's there are
        for (double v : an) if (v != 0) cnt++;
        this.functions = new Function[cnt];
        int j=0;

        if (an[0] != 0) {
            this.functions[j] = new Power(new X(an[0]), 0);
            j++;
        }

        for(int i=1; i< an.length; i++) {
            if (an[i] != 0) {
                this.functions[j] = new Power(new X(an[i]), i);
                j++;
            }
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
        if(functions.length == 1)
            return functions[0].toString();
        StringBuilder fStr = new StringBuilder() ;
        for (Function function : functions)
            fStr.append(function.toString()).append(" + ");
        return "(" + fStr.substring(0, fStr.length() - 3) + ")";
    }

    /**
     * @return a0*f'(x) + a1*f'(x) + ... + an*f'(x)
     */
    @Override
    public Polynomial derivative() {
        int check = functions[0] instanceof Constant ? 1 : 0;
        if(functions[0] instanceof Power && ((Power) functions[0]).getN() == 0)
            check = 1;
        Function[] derivative = new Function[functions.length - check];
        for(int i=0; i < derivative.length; i++)
            derivative[i] = functions[i+check].derivative();
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
    public Function taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
