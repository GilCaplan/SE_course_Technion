package PartA;

public class Date {
    private int day;//1-31
    private int month;//1-12
    private int year;//0-9999

    public Date(int year, int month, int day) {
        this.year = (year>=0 && year <= 9999) ? year : 0;//check in range
        this.month= (month>=1 && month<=12) ? month : 1;
        this.day = (day >= 1 && day <=31) ? day : 1;
    }

    @Override
    public boolean equals(Object obj) {// Amir check this and see if it needs to be fixed
        if(!(obj instanceof Date))//need to check if it's not a DateTime object?
            return false;
        Date date = (Date) obj;
        return this.day == date.day && this.month == date.month && this.year == date.year;
    }
    @Override
    public int hashCode() {// Amir check this and see if it needs to be changed
        return super.hashCode();
    }

    @Override
    public String toString() {
        return this.day+"/"+this.month+"/"+this.year;//need to change to uppercase?
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
