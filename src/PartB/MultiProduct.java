package PartB;

public class MultiProduct extends Function{
    private final Function[] functions;
    public MultiProduct(Function f1, Function... functions){
        this.functions = new Function[functions.length+1];
        this.functions[0] = f1;
        for(int i=1; i<this.functions.length;i++)
            this.functions[i] = functions[i-1];
    }

    /**
     * @param x is a real number
     * @return value of f(x)
     */
    @Override
    public double valueAt(double x) {
        double sum = 1;
        for(Function function : functions)
            sum *= function.valueAt(x);
        return sum;
    }

    /**
     * @return f1(x)*f2(x)*...*fn(x)
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Function function : functions)
            str.append(function.toString()).append(" * ");
        return "(" + str.substring(0, str.length()- 3) + ")";//get rid of the + at the end
    }


    /**
     * @return derivative of the function fi'(x)* (f1(x)+...+fn(x) - fi(x))
     */
    @Override
    public Function derivative() {//need to check
        Function[] derivative = new Function[this.functions.length];
        Function[] mulProduct;
        for(int i=0; i < this.functions.length; i++){
            mulProduct = new Function[this.functions.length];
            for(int j=0; j < this.functions.length; j++)
                if(i != j)
                    mulProduct[j] = this.functions[j];//fj
                else
                    mulProduct[i] = this.functions[i].derivative();//(fi)'

            derivative[i] = new MultiProduct(mulProduct[0], takeOfffirst(mulProduct));
        }
        return new MultiSum(derivative[0], takeOfffirst(derivative));
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
