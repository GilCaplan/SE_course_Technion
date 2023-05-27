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
        Function[] deriv=new Function[len];
        for(int i=0;i<len;i++){
            deriv[i]=this.functions[i].derivative();
        }
        return new Polynomial(deriv);
    }

    @Override
    public double bisectionMethod(double a, double b) {
        double min;
        double max;
        if(this.currFunc.valueAt(a)>this.currFunc.valueAt(b)){
            min=b;
            max=a;
        }
        else {
            min=a;
            max=b;
        }
        double mid= (a+b)/2;
        double maxdis=1.0/10000.0;
        double mindis=-1.0/10000.0;
        while (this.currFunc.valueAt(mid)>maxdis||this.currFunc.valueAt(mid)<mindis){
            if (this.currFunc.valueAt(mid)>maxdis){
                mid=(min+mid)/2;
            }
            else {
                mid=(max+mid)/2;
            }
        }
        return mid;
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
