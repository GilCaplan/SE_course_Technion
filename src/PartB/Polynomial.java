package PartB;

public class Polynomial extends Function{
    private final Function[] functions;
    private boolean taylor;

    public Polynomial(boolean taylor, double a1){
        this.functions = new Function[1];
        this.functions[0] = new Constant(a1);
        this.taylor = taylor;
    }
    public Polynomial(boolean taylor, Function[] functions){
        this.taylor = taylor;
        this.functions = new Function[functions.length];
        for(int i=0; i<functions.length; i++)
            this.functions[i] = functions[i];
    }
    public Polynomial(double... an){
        this.taylor = false;
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
        for (Function function : functions) {
            if(function.toString().contains("-") && fStr.length() > 2)
                fStr = new StringBuilder(fStr.substring(0, fStr.length()-2));
             fStr.append(function).append(" + ");
        }
        if(this.taylor)
            return "(" + fStr.substring(0, fStr.length() - 3).replaceAll("[()]", "").replaceAll(" -"," - ") + ")";
        return "(" + fStr.substring(0, fStr.length() - 3) + ")";
    }

    /**
     * @return a0*f'(x) + a1*f'(x) + ... + an*f'(x)
     */
    @Override
    public Polynomial derivative() {
        int check = 0;
        if(functions[0] instanceof Power && ((Power) functions[0]).getN() == 0)
            check = 1;
        if(functions.length - check == 0)
            return new Polynomial(this.taylor, 0);
        Function[] derivative = new Function[functions.length - check];
        for(int i=0; i < derivative.length; i++)
            derivative[i] = functions[i+check].derivative();
        return new Polynomial(this.taylor, derivative);
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
