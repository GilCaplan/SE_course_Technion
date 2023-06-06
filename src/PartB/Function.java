package PartB;

/**
 * abstract class which generalises Function
 * the class has useful functions that can be applied to any function belonging to a subclass
 */
public abstract class Function {

    /**
     * given a Function the function will give the value of f(x) at point x
     * @param x is a real number
     * @return f(x)
     */
    public abstract double valueAt(double x);

    /**
     * string version of a function
     * @return string version of a given function object
     */
    @Override
    public abstract String toString();

    /**
     * calculate the derivative function of the current function object.
     * @return a new function which is a derivative of the original function
     */
    public abstract Function derivative();

    /**
     * Root finding using bisection method using f(x) in segment [a, b] with a bias of up to epsilon
     * @param a is a real number
     * @param b is a real number where a < b
     * @param epsilon is usually a small number and the range of where the root is
     * @return find the closest x to where f(x) = 0 with a bias of epsilon
     */
    public double bisectionMethod(double a, double b, double epsilon){
        double left=a, right = b;
        while((right - left) > epsilon){
            double mid = (left+right)/2;
            if((this.valueAt(left) * this.valueAt(mid)) > 0)
                left = mid;
            else
                right=mid;
        }
        return (left+right)/2;
    }

    /**
     * Root finding using bisection method using f(x) in segment [a, b] with a bias of up to 10^-5
     * @param a is a real number
     * @param b is a real number where a < b
     * @return find the closest x to where f(x) = 0 with a bias of 10^-5
     */
    public double bisectionMethod(double a, double b){//epsilon = 10^(-5)
        return bisectionMethod(a, b, Math.pow(10, -5));
    }

    /**
     * Root finding using the newton Raphson method of f(x) in segment [a, b] with a bias of up to epsilon
     * @param a is a real number
     * @param epsilon is usually a small number, a bias of how far our solution is to the actual root
     * @return find the closest x to where f(x) = 0 with a bias of epsilon
     */
    public double newtonRaphsonMethod(double a, double epsilon){
        double xk = a;
        while(abs(this.valueAt(xk)) >= epsilon)
            xk = xk - (this.valueAt(xk) / this.derivative().valueAt(xk));
            //x(k+1) = xk - f(xk)/f'(xk)
        return xk;
    }

    /**
     * Root finding using the newton Raphson method of f(x) in segment [a, b] with a bias of up to 10^-5
     * @param a is a real number
     * @return find the closest x to where f(x) = 0 with a bias of 10^-5
     */
    public double newtonRaphsonMethod(double a){//epsilon = 10^(-5)
        return newtonRaphsonMethod(a, Math.pow(10, -5));
    }

    /**
     * @param n is how many degrees we want to open up the taylor function
     * @return the taylorPolynomial of given function opened up to the power of n */
    public Function taylorPolynomial(int n) {
        Function der = this; //taylorPol len is cnt
        double[] taylorPol = new double[n+1];//coefficients of the taylor Polynomial
        taylorPol[0] = der.valueAt(0);
        for(int i=1; i < n+1; i++){
            der = der.derivative();//each derivative is the same as the previous placement.derivative()
            taylorPol[i] = der.valueAt(0) / getFactorial(i);//(f'(n)'(0))/i!
        }
        return new Polynomial(taylorPol);//we make sure that we have the right format for polynomial
    }

    public static double abs(double a){// |a|
        return a >= 0 ? a : -a;
    }
    public double getFactorial(int n){//returns n!
        double sum = 1;
        for(int i=1; i <= n; i++)
            sum *= i;
        return sum;
    }

    /**
     * @param f is an amount of functions
     * @return a new array of function not including the first two
     */
    public static Function[] takeOffFirstTwo(Function... f) {
        Function[] newFunctions = new Function[f.length - 2];
        for (int i = 0; i < f.length - 2; i++)
            newFunctions[i] = f[i + 2];
        return newFunctions;
    }
}
