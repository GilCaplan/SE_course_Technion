package PartB;

public class Quotient extends Function{
    private final Function numerator;
    private final Function denominator;
    public Quotient(Function numerator,Function denominator){
        this.numerator=numerator;
        this.denominator=denominator;
        return;
    }



    /**
     * @param x is a real number
     * @return the quotient of numerator and denominator
     */
    @Override
    public double valueAt(double x) {
        double numerator1=this.numerator.valueAt(x);
        double denominator1=this.denominator.valueAt(x);
        return numerator1/denominator1;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        String str = "";
        str+=this.numerator.toString()+"/";
        str+=this.denominator.toString();
        return str;
    }

    /**
     * first calculate the (numerator)'*(denominator) -(numerator)*(denominator)' and save it as a Dif func
     * then calculate the ((denominator)*(denominator)) and save it as a prod func
     * return the functions
     */
    @Override
    public Function derivative() {
        Function[] derivative=new Function[2];
        Function[] numeratorDer=new Function[2];
        Function[] numeratorDer1=new Function[2];
        numeratorDer1[0]=this.numerator;
        numeratorDer1[1]=this.denominator.derivative();
        numeratorDer[0]=new Polynomial(numeratorDer1);
        Function[] numeratorDer2=new Function[2];
        numeratorDer2[0]=this.numerator.derivative();
        numeratorDer2[1]=this.denominator;
        numeratorDer[1]=new Polynomial(numeratorDer2);
        derivative[1]=new Difference(numeratorDer[0],numeratorDer[1]);
        Function[] denominatorDer=new Function[1];
        denominatorDer[0]=this.denominator.derivative();
        derivative[0]=new Product(denominatorDer[0],denominatorDer[0]);
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
