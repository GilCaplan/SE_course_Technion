package PartA;

public class Date implements checkType{
    private final int day;//1-31
    private int month;//1-12
    private final int year;//0-3999


    /* Date Constructor where a Date object is represented by year/month/day */
    public Date(int year, int month, int day) {
        this.year = (year>=0 && year <= 9999) ? year : 0;//check in range
        this.month= (month>=1 && month<=12) ? month : 1;
        this.day = (day >= 1 && day <=31) ? day : 1;
    }


    /* checks if two Date objects are equal by content, will return false if one of the objects is from a subclass*/
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Date) || this.isDate() != ((Date) obj).isDate())
            return false;
        Date date = (Date) obj;
        return this.day == date.day && this.month == date.month && this.year == date.year;
    }

    /** year will be y, month will be m and day will be d
     * the hashCode will be in the form of yyyymmdd, so two object will return the same hashcode if they have the same
     * content or the two objects are equal dq.equals(d2) ==  true */
    @Override
    public int hashCode() {
        return this.year*10000+this.month*100+this.day;
    }

    @Override
    public String toString() {
        String day = String.valueOf(this.day).length()==1 ? "0"+this.day : String.valueOf(this.day);
        String month =String.valueOf(this.month).length()==1 ? "0"+this.month : String.valueOf(this.month);;
        String year;
        int yearLen = String.valueOf(this.year).length();
        switch (yearLen) {
            case 0:
                year =  "0000";
                break;
            case 1:
                year = "000" + this.year;
                break;
            case 2:
                year = "00" + this.year;
                break;
            case 3:
                year ="0" + this.year;
                break;
            default:
                year = String.valueOf(this.year);
        }
        return day+"/"+month+"/"+year;
    }

    public void setMonth(int month) {
        this.month= (month>=1 && month<=12) ? month : 1;
    }

    /**
     * @return false since this object is Date and not Datetime
     */
    @Override
    public boolean isDate() {
        return false;
    }
}