package PartB;

public abstract class Function {
    public Function derivative;

    public abstract double valueAt(double x);
    @Override
    public abstract String toString();
    public abstract Function derivative();
    public double bisectionMethod(double a, double b, double epsilon){
        double left=a, right = b;
        while((right - left) > epsilon){
            double mid = (left+right)/2;
            if(this.valueAt(left) * this.valueAt(mid) > 0)
                left = mid;
            else
                right=mid;
        }
        return (left+right)/2;
    }
    public double bisectionMethod(double a, double b){
        return bisectionMethod(a, b, 10^-5);
    }
    public double newtonRaphsonMethod(double a, double epsilon){
        double xk = a;
        while(this.abs(this.valueAt(a)) < epsilon){
            xk = xk - this.valueAt(xk) / this.derivative().valueAt(xk);
            //x(k+1) - f(xk)/f'(xk)
        }
        return xk;
    }


    public double newtonRaphsonMethod(double a){
        return newtonRaphsonMethod(a, 10^(-5));
    }


    public Polynomial taylorPolynomial(int n) {
        if(n == 0)
            return null;
        Function[] derivatives = new Function[n];
        Function[] taylorPol = new Function[n];
        Product fn;
        Constant an;
        derivatives[0] = this;
        taylorPol[0] = new Constant(this.valueAt(0));
        for(int i=1; i<n; i++){
            //each derivative is the same as the previous placement.derivative()
            derivatives[i] = derivatives[i-1].derivative();
            an = new Constant(derivatives[i].valueAt(0) / getFactorial(i));//(f'(n)'(0))/i!
            fn = new Product(an, new Power(new X(), i));
            taylorPol[i] = fn;
        }
        return new Polynomial(taylorPol);//we make sure that we have the right format for polynomial
    }
    public double abs(double a){
        return a>=0?a:-a;
    }
    public double getFactorial(int n){
        double sum = 1;
        for(int i=1; i<n; i++)
            sum*= i;
        return sum;
    }
}
