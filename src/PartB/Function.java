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
    public double bisectionMethod(double a, double b){
        return bisectionMethod(a, b, 1e-5);
    }
    /* Root finding using the newton Raphson method of f(x) in segment [a, b] with a bias of up to epsilon */
    public double newtonRaphsonMethod(double a, double epsilon){
        double xk = a;
        while(abs(this.valueAt(a)) <= epsilon)
            xk = xk - (this.valueAt(xk) / this.derivative().valueAt(xk));
            //x(k+1) = xk - f(xk)/f'(xk)
        return xk;
    }


    public double newtonRaphsonMethod(double a){
        return newtonRaphsonMethod(a, 1e-5);
    }

    /**
     * @param n is how many degrees we want to open up the taylor function
     * @return the taylorPolynomial of given function opened up to the power of n */
    public Function taylorPolynomial(int n) {
        Function[] derivatives = new Function[n+1]; //taylorPol len is cnt
        Function[] taylorPol = new Function[n+1];
        Power fn;
        double an;
        derivatives[0] = this;
        taylorPol[0] = new Constant(derivatives[0].valueAt(0));
        for(int i=1; i <= n; i++){
            //each derivative is the same as the previous placement.derivative()
            derivatives[i] = derivatives[i-1].derivative();
            if(derivatives[i].valueAt(0) != 0) {
                an = derivatives[i].valueAt(0) / getFactorial(i);//(f'(n)'(0))/i!
                fn = new Power(new X(an), i);
                taylorPol[i] = fn;
            }
            else taylorPol[i] = new Constant(0);
        }
        return new Polynomial(true, taylorPol);//we make sure that we have the right format for polynomial
    }

    public static double abs(double a){
        return a >= 0 ? a : -a;
    }
    public double getFactorial(int n){
        double sum = 1;
        for(int i=1; i<=n; i++)
            sum*= i;
        return sum;
    }
    public static double pow(double x, int n) {
        double sum;
        if (n == 0)
            return 1;

        if (n < 0) {
            x = 1 / x;
            n *= -1;
        }

        sum = 1;
        while (n > 0) {
            if (n % 2 == 1)
                sum *= x;
            n /= 2;
            x *= x;
        }
        return sum;
    }

    //take off the first element of the array and return the rest of the array as a new array
    public static Function[] takeOffFirst(Function... f){
        Function[] newFunctions = new Function[f.length-1];
        for(int i=0; i < f.length - 1; i++)
            newFunctions[i] = f[i+1];
        return newFunctions;
    }

    public static Function[] takeOffFirstTwo(Function... f){
        Function[] newFunctions = new Function[f.length-2];
        for(int i=0; i < f.length - 2; i++)
            newFunctions[i] = f[i+2];
        return newFunctions;
    }
}
