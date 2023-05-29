package PartB;

public abstract class Function {
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
        return bisectionMethod(a, b, 1.0/10000);
    }
    public double newtonRaphsonMethod(double a, double epsilon){
        double xk = a;
        while(abs(this.valueAt(a)) < epsilon){
            xk = xk - this.valueAt(xk) / this.derivative().valueAt(xk);
            //x(k+1) = xk - f(xk)/f'(xk)
        }
        return xk;
    }


    public double newtonRaphsonMethod(double a){
        return newtonRaphsonMethod(a, pow(10, -5));
    }


    public Function taylorPolynomial(int n) {
        int cnt = 0;
        Function der = this;
        while(der.derivative().valueAt(0) != 0){
            der = der.derivative();
            cnt++;
        }//cnt until what power the taylorPol should be.

        if(cnt == 0)
            return new Constant(this.valueAt(0));//need to check this line

        Function[] derivatives = new Function[cnt];
        Function[] taylorPol = new Function[cnt];
        Product fn;
        Constant an;
        derivatives[0] = this;
        taylorPol[0] = new Constant(this.valueAt(0));
        for(int i=1; i<n; i++){
            //each derivative is the same as the previous placement.derivative()
            derivatives[i] = derivatives[i-1].derivative();
            if(derivatives[i].valueAt(0) == 0)
                return new Polynomial(taylorPol);
            an = new Constant(derivatives[i].valueAt(0) / getFactorial(i));//(f'(n)'(0))/i!
            fn = new Product(an, new Power(new X(), i));//need Amir to fill in Product class
            taylorPol[i] = fn;
        }
        return new Polynomial(taylorPol);//we make sure that we have the right format for polynomial
    }
    public static double abs(double a){
        return a>=0?a:-a;
    }
    public double getFactorial(int n){
        double sum = 1;
        for(int i=1; i<n; i++)
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
}
