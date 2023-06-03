package PartB;

public class Constant extends Function{
    private final double constant;
    public Constant(double number){
        this.constant = number;
    }

    /**
     * @param x is a real number
     * @return constant number as x does not affect the change
     */
    @Override
    public double valueAt(double x) {
        return this.constant;
    }

    /**
     * @return (this.constant)
     */
    @Override
    public String toString() {
        if(this.constant % 1 == 0)
            return "("+(int)this.constant+")";
        return "("+this.constant+")";
    }

    /**
     * @return Constant 0 as the derivative of a constant number is 0.
     */
    @Override
    public Function derivative() {
        return new Constant(0);
    }
}
