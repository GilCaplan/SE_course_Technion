package PartB;

public class X extends Function{
    private final String x;
    private final double num;
    public X(){
        this.x = "x";
        this.num = 1;
    }

    public X(double num){
        this.x = "x";
        this.num = num;
    }
    public String getX() {
        return this.x;
    }

    /**
     * @param x is a real number
     * @return value of x * num
     */
    @Override
    public double valueAt(double x) {
        return x * this.num;
    }

    /**
     * @return string version of x
     */
    @Override
    public String toString() {
        if(this.num == 1)
            return "x";
        if((double)((int)this.num) == this.num)
            return (int)this.num+"x";
        return this.num+"x";
    }

    /**
     * @return derivative which is a constant number
     */
    @Override
    public Function derivative() {
        return new Constant(this.num);
    }

    public double getNum() {
        return this.num;
    }
}
