package PartB;

public class MultiSum extends Function{

    private final Function[] functions;
    public MultiSum(Function... functions){
        if(functions.length < 2)
            throw new RuntimeException("Runtime error: need to be more than 2 functions");
        this.functions = functions;
    }
    /**
     * @param x is a real number
     * @return
     */
    @Override
    public double valueAt(double x) {
        int sum=0;
        for(Function function:functions){
            sum+=function.valueAt(x);
        }
        return sum;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        String str="";
        for(Function function:functions){
            str += function.toString()+"+";
        }
        return str.substring(0, str.length()-1);//delete the + at the end;
    }

    /**
     * @return
     */
    @Override
    public Function derivative() {
        int len=this.functions.length;
        Function[] derivative =new Function[len];
        for(int i=0;i<len;i++){
            derivative[i]=this.functions[i].derivative();
        }
        return new Polynomial(derivative);
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
    public Polynomial taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
