package PartB;

public class Sum extends Function{
    private final Function f1, f2;//sum of 2 functions
    public Sum(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    /**
     * @param x is a real number
     * @return f1(x)+f2(x)
     */
    @Override
    public double valueAt(double x) {
        return this.f1.valueAt(x) + this.f2.valueAt(x);
    }

    /**
     * @return f1(x) + f2(x)
     */
    @Override
    public String toString() {
        return "(" + f1.toString() + " + " + this.f2.toString() + ")";
    }

    /**
     * @return if both are constants then 0 otherwise f1'(x)+f2'(x)
     */
    @Override
    public Function derivative() {
        return new Sum(this.f1.derivative(), this.f2.derivative());
    }
}
