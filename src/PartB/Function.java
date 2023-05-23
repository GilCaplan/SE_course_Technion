package PartB;

public abstract class Function {
    public Function derivative;
    public Function currFunc;

    public abstract int valueAt(int x);
    @Override
    public abstract String toString();
    public abstract Function derivative();
    public int bisectionMethod(int a, int b, double epsilon){
        int left=a, right = b;
        while((double)(right - left) > epsilon){
            int mid = (left+right)/2;
            if(this.valueAt(left) * this.valueAt(mid) > 0)
                left = mid;
            else
                right=mid;
        }
        return (left+right)/2;
    }
    public int bisectionMethod(int a, int b){
        return bisectionMethod(a, b, 10^-5);
    }
    public int newtonRaphsonMethod(int a, double epsilon){
        int xk = a;
        while(this.abs(this.valueAt(a)) < epsilon){
            xk = xk - this.valueAt(xk)/this.derivative().valueAt(xk);
            //x(k+1) - f(xk)/f'(xk)
        }
        return xk;
    }


    public int newtonRaphsonMethod(int a){
        return newtonRaphsonMethod(a, 10^-5);
    }
    public Function taylorPolynomial(int n) {
        Function[] derivatives = new Function[n];
        Function[] taylorPol = new Function[n];
        derivatives[0] = this;
        for(int i=1; i<n; i++){
            derivatives[i] = derivatives[i-1].derivative();
        }
        //find at 0.
        for(int i=0; i<n; i++){
//            taylorPol[i] = (derivative[i].Value(0)*x^i)/factorial(i)
        }
        return null;
    }
    public int abs(int a){
        return a>=0?a:-a;
    }
    public int getFactorial(int n){
        int sum = 1;
        for(int i=1; i<n; i++)
            sum*= i;
        return sum;
    }
}
