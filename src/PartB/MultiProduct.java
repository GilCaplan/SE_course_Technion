package PartB;

public class MultiProduct extends Function{
    private final Function[] functions;
    public MultiProduct(Function... functions){
        this.functions = functions;
    }

    /**
     * @param x is a real number
     * @return value of f(x)
     */
    @Override
    public int valueAt(int x) {
        double sum = 1;
        for(Function function : functions)
            sum *= function.valueAt(x);
        return (int)sum;
    }

    /**
     * @return f1(x)*f2(x)*...*fn(x)
     */
    @Override
    public String toString() {
        String str = "";
        for (Function function : functions)
            str += function.toString()+"*";
        return str.substring(0, str.length()-1);//get rid of the * at the end
    }


    /**
     * @return derivative of the function fi'(x)* (f1(x)+...+fn(x) - fi(x))
     */
    @Override
    public Function derivative() {
        Function[] derivative = new Function[this.functions.length];
        Function[] mulProduct;
        for(int i=0; i < this.functions.length; i++){
            mulProduct = new Function[this.functions.length];
            mulProduct[0] = this.functions[i].derivative();
            int j=1;
            for(; j < this.functions.length; j++){
                if(i != j){
                    mulProduct[j] = this.functions[j];
                }
            }
            derivative[i] = new MultiProduct(mulProduct);
        }
        return new Polynomial(derivative);
    }

    public int bisectionMethod(int a, int b, int epsilon) {
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
