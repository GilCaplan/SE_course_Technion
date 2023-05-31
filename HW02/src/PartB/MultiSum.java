package PartB;

public class MultiSum extends Function{

    private final Function[] functions;

    /**
     * Constructor that requires a minimum of 2 functions and builds a MultiSum object
     * while being defined by 2+ functions
     * @param f1 function number 1
     * @param f2 function number 2
     * @param functions function number 3 to number n
     */
    public MultiSum(Function f1, Function f2, Function... functions){
        this.functions = new Function[functions.length + 2];
        this.functions[0] = f1;
        this.functions[1] = f2;
        for(int i=2; i < this.functions.length;i++)
            this.functions[i] = functions[i-2];
    }

    /**
     * @param x is a real number
     * @return f1(x)+...+fn(x)
     */
    @Override
    public double valueAt(double x) {
        double sum=0;
        for(Function function : functions)
            sum += function.valueAt(x);
        return sum;
    }

    /**
     * @return f1(x)+...+fn(x)
     */
    @Override
    public String toString() {
        StringBuilder str= new StringBuilder();
        for(Function function : functions)
            str.append(function.toString()).append(" + ");
        return "(" + str.substring(0, str.length() - 3) + ")";//delete the + at the end;
    }

    /**
     * @return f1'(x)+...+f2'(x)
     */
    @Override
    public Function derivative() {
        int len = this.functions.length;
        Function[] derivative = new Function[len-2];
        for(int i=2; i < len; i++)
            derivative[i-2] = this.functions[i].derivative();
        return new MultiSum(this.functions[0].derivative(), this.functions[1].derivative(), derivative);
    }

    @Override
    public double bisectionMethod(double a, double b) {
        return super.bisectionMethod(a,b);
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
