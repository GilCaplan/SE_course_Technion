package PartB;

public class MultiProduct extends Function{
    private Function[] functions;
    public MultiProduct(Function[] functions){
        this.functions = functions;
    }
    /**
     * @param x
     * @return
     */
    @Override
    public int valueAt(int x) {
        int sum = 1;
        for(Function function : functions)
            sum *= function.valueAt(x);
        return sum;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        String str = "";
        for (Function function : functions)
            str += function.toString();
        return str;
    }


    /**
     * @return
     */
    @Override
    public Function derivative() {
        return null;
    }

    /**
     * @param a
     * @param b
     * @param epsilon
     * @return
     */
    public int bisectionMethod(int a, int b, int epsilon) {
        int left=a, right = b;
        while(right - left > epsilon){
            int mid = (left+right)/2;
            if(this.valueAt(left) * this.valueAt(mid) > 0)
                left = mid;
            else
                right=mid;
        }
        return (left+right)/2;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    @Override
    public int bisectionMethod(int a, int b) {
        int left=a, right = b;
        double epsilon = 10^(-5);
        while((double)(right - left) > epsilon){
            int mid = (left+right)/2;
            if(this.valueAt(left) * this.valueAt(mid) > 0)
                left = mid;
            else
                right=mid;
        }
        return (left+right)/2;
    }

    /**
     * @param a
     * @param epsilon
     * @return
     */
    @Override
    public int newtonRaphsonMethod(int a, int epsilon) {
        return 0;
    }

    /**
     * @param a
     * @return
     */
    @Override
    public int newtonRaphsonMethod(int a) {
        return 0;
    }

    /**
     * @param n
     * @return
     */
    @Override
    public Function taylorPolynomial(int n) {
        return null;
    }
}
