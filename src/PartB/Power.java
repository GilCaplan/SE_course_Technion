package PartB;

public class Power extends Function {
    private final int n;//power number
    private final Function f;
    private final boolean taylor;


    public Power(Function f, int n) {
        this.n = n;
        this.f = f;
        this.taylor = false;
    }

    public Power(Function f, int n, boolean taylor) {
        this.n = n;
        this.f = f;
        this.taylor = taylor;
    }

    /**
     * @param x is a real number
     * @return f(x) based on x
     */
    @Override
    public double valueAt(double x) {
        if (this.f instanceof X)
            return ((X) this.f).getNum() * pow(x, this.n);
        double val = this.f.valueAt(x);
        return pow(val, this.n);
    }

    /**
     * @return (f ( x))^n
     */
    @Override
    public String toString() {
        if (f instanceof X) {//return "ax^n"
            double value = ((X) this.f).getNum();

            String num;
            if ((double) ((int) value) == value)
                num = String.valueOf((int) value);
            else
                num = String.valueOf(value);
            String result;
            if (this.n == 0)
                result = num;
            else if (this.n == 1) {
                if(num.equals("1"))
                    result = "x";
                else result = num + "x";
            }
            else if(num.equals("1"))
                result = "x^" + this.n;
            else
                result = num + "x^" + this.n;
            if(taylor)
                return "("+result+")";
            return result;
        }
        if (n == 0)
            return "1";
        if (n == 1)
            return f.toString();
        return"("+f.toString()+"^"+this.n +")";
    }

    /**
     * @return new function that is a derivative of the current function, f'(x) = n*f(x)'*(f(x)^(n-1))
     */
    @Override
    public Function derivative() {
        Function derivative = this.f.derivative();
        if(this.f instanceof X) {
            if (this.n == 2)
                return new X(this.n * ((X)f).getNum());
            return new Power(new X(this.n * ((X)f).getNum()), n-1);

        }
        return new MultiProduct(new Constant(this.n), new Power(this.f, n-1), derivative);//(n-1)*f'*f
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
    public Function taylorPolynomial(int n) {
        return super.taylorPolynomial(n);
    }

    public int getN() {
        return this.n;
    }
}
