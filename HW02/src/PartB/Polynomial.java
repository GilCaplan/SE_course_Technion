package PartB;

public class Polynomial extends Function{
    private final Function[] functions;
    private final boolean taylor;

    /**
     * Additional constructor as we have an option for the Polynomial to be a taylor polynomial and the toString is
     * different for a taylor polynomial therefore the constructor is needed.
     * @param taylor is a boolean value given if it's a taylor polynomial
     * @param a1 number that make up the Polynomial object
     */
    public Polynomial(boolean taylor, double a1){
        this.functions = new Function[1];
        this.functions[0] = new Constant(a1);
        this.taylor = taylor;
    }

    /**
     * Additional constructor as we have an option for the Polynomial to be a taylor polynomial and the toString is
     * different for a taylor polynomial therefore the constructor is needed.
     * @param taylor is a boolean value given if it's a taylor polynomial
     * @param functions functions that make up the Polynomial object
     */
    public Polynomial(boolean taylor, Function[] functions){
        this.taylor = taylor;
        this.functions = new Function[functions.length];
        for(int i=0; i<functions.length; i++)
            this.functions[i] = functions[i];
    }

    public Polynomial(double... an){
        this.taylor = false;
        this.functions = new Function[an.length];
        for(int i = 0; i< an.length; i++) {
            this.functions[i] = new Power(new X(an[i]), i);
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

        if(this.taylor)//if taylor function, we want the toString to give: "(a0+a1x+a2x^2+...an*x^n)"
            return taylorString(this.functions);

        StringBuilder fStr = new StringBuilder() ;
        for (Function function : this.functions)
            fStr = getStringBuilder(fStr, function);
        return "(" + fStr.substring(0, fStr.length() - 3) + ")";
    }

    public static String taylorString(Function[] functions){
        StringBuilder fStr = new StringBuilder() ;
        for (Function function : functions)
            if(functions.length > 1)
                fStr = getStringBuilder(fStr, function);
        if(fStr.length() == 0)
            return "(0)";
        String res = fStr.substring(0, fStr.length() - 3).replaceAll("[()]", "");
        res = res.replaceAll(" -"," - ");
        return "(" + res + ")";
    }


    /**
     * append the function string version to fStr
     * @param fStr StringBuilder that is a string of all the functions combined
     * @param function that we convert to a string
     * @return updated version of fStr
     */
    private static StringBuilder getStringBuilder(StringBuilder fStr, Function function) {
        String fString = function.toString();
        if(!fString.contains("(0)") && !fString.startsWith("0x") && !fString.startsWith("0")) {
            if (!function.toString().contains("NaN")) {
                if (function.toString().contains("-") && fStr.length() > 2)
                    //get rid of "+ " so we don't get "+ -" on the
                    fStr = new StringBuilder(fStr.substring(0, fStr.length() - 2));
                fStr.append(function).append(" + ");
            }
        }
        return fStr;
    }


    /**
     * @return a0*f'(x) + a1*f'(x) + ... + an*f'(x)
     */
    @Override
    public Polynomial derivative() {
        int check = 0;
        if(functions[0] instanceof Power && ((Power) functions[0]).getN() == 0)
            check = 1;//if we get 0x^1 we just want it to give 0
        if(functions.length - check == 0)
            return new Polynomial(this.taylor, 0);
        Function[] derivative = new Function[functions.length - check];//no point adding 0 to the polynomial
        //if it's followed by more expressions that aren't 0.
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
