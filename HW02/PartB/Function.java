package PartB;

public abstract class Function {
    public abstract double valueAt(double x);
    @Override
    public abstract String toString();
    public abstract Function derivative();

    /* Root finding using bisection method using f(x) in segment [a, b] with a bias of up to epsilon */
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
    public double bisectionMethod(double a, double b){//epsilon = 10^(-5)
        return bisectionMethod(a, b, Math.pow(10, -5));
    }
    /* Root finding using the newton Raphson method of f(x) in segment [a, b] with a bias of up to epsilon */
    public double newtonRaphsonMethod(double a, double epsilon){
        double xk = a;
        while(abs(this.valueAt(xk)) >= epsilon)
            xk = xk - (this.valueAt(xk) / this.derivative().valueAt(xk));
            //x(k+1) = xk - f(xk)/f'(xk)
        return xk;
    }


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
