package PartB;

public class Difference extends Function{
    //make constructor and attributes, write code and fill in javadoc where needed - Amir
    private final Function firstFun;
    private final Function secondFun;

    public Difference(Function firstFun,Function secondFun){
        this.firstFun=firstFun;
        this.secondFun=secondFun;
    }

    /**
     * @param x is a real number
     * @return the dif in firstFun and secondFun
     */
    @Override
    public double valueAt(double x) {
        double a=this.firstFun.valueAt(x);
        double b=this.secondFun.valueAt(x);
        return a-b;
    }

    /**
     * @return the string of the func
     */
    @Override
    public String toString() {
        String str="";
        str += this.firstFun.toString()+"-";
        str += this.secondFun.toString();
        return str;
    }

    /**
     * @return the derivative of the func
     */
    @Override
    public Function derivative() {
        Function[] derivative=new Function[2];
        derivative[0]=this.firstFun.derivative();
        derivative[1]=this.secondFun.derivative();
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
    public Polynomial taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }
}
