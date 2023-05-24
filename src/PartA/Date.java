package PartA;

public class Date {
    private final int day;//1-31
    private int month;//1-12
    private final int year;//0-9999

    public Date(int year, int month, int day) {
        this.year = (year>=0 && year <= 9999) ? year : 0;//check in range
        this.month= (month>=1 && month<=12) ? month : 1;
        this.day = (day >= 1 && day <=31) ? day : 1;
    }

    @Override
    public boolean equals(Object obj) {// Amir check this and see if it needs to be fixed
        if(!(obj instanceof Date))
            return false;
        Date date = (Date) obj;
        return this.day == date.day && this.month == date.month && this.year == date.year;
    }
    @Override
    public int hashCode() {// Amir check this and see if it needs to be changed
        return super.hashCode();
    }//don't think we need to change?

    @Override
    public String toString() {
        return this.day+"/"+this.month+"/"+this.year;//need to change to uppercase?
    }

    public void setMonth(int month) {
        this.month = month;
    }

}
