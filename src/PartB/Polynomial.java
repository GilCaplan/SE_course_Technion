package PartB;

public class Polynomial extends Function{
    private double[] an;

    /**
     * Additional constructor as we have an option for the Polynomial to be a taylor polynomial and the toString is
     * different for a taylor polynomial therefore the constructor is needed.
     * @param an is a series that represents a polynomial
     */
    public Polynomial(double... an){
        this.an = new double[an.length];
        for(int i=0; i< an.length; i++)
            this.an[i] = an[i];
        if(an.length == 0){
            this.an = new double[1];
            this.an[0] = 0;
        }
    }

    /**
     * @param x is a real number
     * @return f(x) value
     */
    @Override
    public double valueAt(double x) {
        double sum = 0;
        for (int i=0; i < this.an.length; i++)
            sum += this.an[i]*pow(x, i);
        return sum;
    }

    /**
     * @return a0*f(x) + a1*f(x) + ... + an*f(x) - double check f(x) contains x^n
     */
    @Override
    public String toString() {
        if(an.length == 1) {
            if ((double) ((int) this.an[0]) == this.an[0])
                return "(" + (int) this.an[0] + ")";
            return "(" + an[0] + ")";
        }

        StringBuilder fStr = new StringBuilder();
        if(this.an[0] != (double)0 && !Double.isNaN(this.an[0])) {
            if((double)((int)this.an[0]) == this.an[0])
                fStr.append((int)this.an[0]).append(" + ");
            else
                fStr.append(this.an[0]).append(" + ");
        }
        int val;
        if(this.an[1] != (double)0 && !Double.isNaN(this.an[1])) {
            if(this.an[1] != (double) 1) {
                val = ((int) this.an[1]);
                if (val == this.an[1])
                    fStr.append(val);
                else
                    fStr.append(this.an[1]);
            }
            fStr.append("x + ");
        }

        for (int i=2; i < this.an.length; i++) {
            if (this.an[i] != 0 && !Double.isNaN(this.an[i])) {
                val = ((int)this.an[i]);
                if(this.an [i] != (double)1) {
                    if (val == this.an[i])
                        fStr.append(val);
                    else
                        fStr.append(this.an[i]);
                }
                fStr.append("x^").append(i).append(" + ");
            }
        }
        if(fStr.length() == 0)
            return "(0)";
        return "(" + fStr.substring(0, fStr.length() - 3).replaceAll("\\+ -", "- ")+ ")";
    }


    /**
     * @return a0*f'(x) + a1*f'(x) + ... + an*f'(x)
     */
    @Override
    public Polynomial derivative() {
        double[] derivatives = new double[this.an.length - 1];
        for(int i=0; i< derivatives.length; i++)
            derivatives[i] = an[i+1] * (i+1);
        return new Polynomial(derivatives);
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

}
