package PartB;

public class MultiSum extends Function{

    private final Function[] functions;
    public MultiSum(Function f1, Function... functions){
        this.functions = new Function[functions.length+1];
        this.functions[0] = f1;
        for(int i=1; i<this.functions.length;i++)
            this.functions[i] = functions[i-1];
    }
    /**
     * @param x is a real number
     * @return
     */
    @Override
    public double valueAt(double x) {
        double sum=0;
        for(Function function : functions)
            sum += function.valueAt(x);
        return sum;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        StringBuilder str= new StringBuilder();
        for(Function function : functions)
            str.append(function.toString()).append(" + ");
        return "(" + str.substring(0, str.length() - 3) + ")";//delete the + at the end;
    }

    /**
     * @return
     */
    @Override
    public Function derivative() {
        int len = this.functions.length;
        Function[] derivative = new Function[len-1];
        for(int i=1; i < len; i++)
            derivative[i-1] = this.functions[i].derivative();
        return new MultiSum(this.functions[0].derivative(), derivative);
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
