package PartA;

public class Date implements checkType{
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
        if(!(obj instanceof Date) || this.isDateTime() != ((Date) obj).isDateTime())
            return false;
        Date date = (Date) obj;
        return this.day == date.day && this.month == date.month && this.year == date.year;
    }
    @Override
    // year will be y, month will be m and day will be d
    // the hashCode will be in the form of yyyymmdd, so two object will be same if
    // every single data of the date will be the same if
    public int hashCode() {
        return this.year*10000+this.month*100+this.day;
    }

    @Override
    public String toString() {
        String day = String.valueOf(this.day).length()==1 ? "0"+this.day : String.valueOf(this.day);
        String month =String.valueOf(this.month).length()==1 ? "0"+this.month : String.valueOf(this.month);;
        String year = "";
        int yearLen = String.valueOf(this.year).length();
        year = switch (yearLen) {
            case (0) -> "0000";
            case (1) -> "000" + this.year;
            case (2) -> "00" + this.year;
            case (3) -> "0" + this.year;
            default -> String.valueOf(this.year);
        };

        return day+"/"+month+"/"+year;
    }

    public void setMonth(int month) {
        this.month= (month>=1 && month<=12) ? month : 1;
    }

    /**
     * @return
     */
    @Override
    public boolean isDateTime() {
        return false;
    }
}
