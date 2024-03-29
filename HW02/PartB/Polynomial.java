package PartB;

public class Polynomial extends Function{
    private double[] an;

    /**
     * Additional constructor as we have an option for the Polynomial to be a taylor polynomial and the toString is
     * different for a taylor polynomial therefore the constructor is needed.
     * @param an is a series that represents a polynomial function
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
            sum += this.an[i] * Math.pow(x, i);
        return sum;
    }

    /**
     * @return a0*f(x) + a1*f(x) + ... + an*f(x) - double check f(x) contains x^n
     */
    @Override
    public String toString() {
        if(an.length == 1) {
            if (this.an[0] % 1 == 0)
                return "(" + (int) this.an[0] + ")";
            return "(" + an[0] + ")";
        }

        StringBuilder fStr = new StringBuilder();
        if(this.an[0] != 0) {
            if(this.an[0] % 1 == 0)
                fStr.append((int)this.an[0]).append(" + ");
            else
                fStr.append(this.an[0]).append(" + ");
        }
        if(this.an[1] != 0) {
            if(this.an[1] != 1) {
                if (this.an[1] % 1 == 0)
                    fStr.append((int)this.an[1]);
                else
                    fStr.append(this.an[1]);
            }
            fStr.append("x + ");
        }

        for (int i=2; i < this.an.length; i++) {
            if (this.an[i] != 0) {
                if(this.an [i] != 1) {
                    if (this.an[i] % 1 == 0)
                        fStr.append((int) this.an[i]);
                    else
                        fStr.append(this.an[i]);
                }
                fStr.append("x^").append(i).append(" + ");
            }
        }
        if(fStr.length() == 0)
            return "(0)";
        String res = fStr.substring(0, fStr.length() - 3).replaceAll("\\+ -1x", "- x");
        return "(" + res.replaceAll("\\+ -", "- ")+ ")";
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
}
