package PartB;

public abstract class Function {
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
    public abstract Function taylorPolynomial(int n);
    public int abs(int a){
        return a>=0?a:-a;
    }
}
