package PartB;

public class Difference extends Function{
    private final Function f1;
    private final Function f2;

    public Difference(Function f1, Function f2){
        this.f1 = f1;
        this.f2 = f2;
    }

    /**
     * @param x is a real number
     * @return f1(x) - f2(x)
     */
    @Override
    public double valueAt(double x) {
        return this.f1.valueAt(x) - this.f2.valueAt(x);
    }

    /**
     * @return the string of the func, (f1(x)) - (f2(x))
     */
    @Override
    public String toString() {
        return "(" + this.f1.toString()+" - " + this.f2.toString()+")";
    }

    /**
     * @return the derivative of the func
     */
    @Override
    public Function derivative() {
        return new Difference(this.f1.derivative(), this.f2.derivative());
    }
}
