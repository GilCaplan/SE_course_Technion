package PartB;

public class MultiProduct extends Function{
    private final Function[] functions;

    /**
     * Constructor that requires a minimum of 2 functions and builds a MultiProduct object
     * while being defined by 2+ functions
     * @param f1 function number 1
     * @param f2 function number 2
     * @param functions function number 3 to number n
     */
    public MultiProduct(Function f1, Function f2, Function... functions){
        this.functions = new Function[functions.length+2];
        this.functions[0] = f1;
        this.functions[1] = f2;
        for(int i=2; i < this.functions.length; i++)
            this.functions[i] = functions[i-2];
    }

    /**
     * @param x is a real number
     * @return value of f(x)
     */
    @Override
    public double valueAt(double x) {
        double sum = 1;
        for(Function function : this.functions)
            sum *= function.valueAt(x);
        return sum;
    }

    /**
     * @return f1(x)*f2(x)*...*fn(x)
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Function function : this.functions)
            str.append(function.toString()).append(" * ");
        return "(" + str.substring(0, str.length() - 3) + ")";//get rid of the + at the end
    }


    /**
     * @return derivative of the function for every i between 0 to n -> fi'(x)* (f1(x)+...+fn(x) - fi(x))
     */
    @Override
    public Function derivative() {
        Function[] derivative = new Function[this.functions.length];
        Function[] mulProduct;
        int cnt;
        for(int i=0; i < this.functions.length; i++){
            mulProduct = new Function[this.functions.length];
            cnt = 1;
            for(int j=0; j < this.functions.length; j++) {
                if (i != j)
                    mulProduct[cnt++] = this.functions[j];//fj
                else
                    mulProduct[0] = this.functions[i].derivative();//(fi)'
            }
            derivative[i] = new MultiProduct(mulProduct[0], mulProduct[1], takeOffFirstTwo(mulProduct));
        }
        return new MultiSum(derivative[0], derivative[1], takeOffFirstTwo(derivative));
    }
}
