public class Date {
    private int day;//1-31
    private int month;//1-12
    private int year;//0-9999

    public Date(int year, int month, int day) {
        this.year = year;
        this.month= month;
        this.day = day;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Date))
            return false;
        Date date = (Date) obj;
        return this.day == date.day && this.month == date.month && this.year == date.year;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return this.day+"/"+this.month+"/"+this.year;
    }
}
