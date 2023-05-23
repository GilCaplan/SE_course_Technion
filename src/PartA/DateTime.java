package PartA;

public class DateTime extends Date {
    private int hour;
    private int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
        super(year, month, day);
        this.hour = (hour>=0 && hour<=23) ? hour : 0 ;
        this.minute = (minute>=0 && minute<=59) ? minute : 0;
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = super.equals(obj);
//        if(!flag || !(obj instanceof DateTime))
//            return false;
        DateTime dt = (DateTime) obj;
        return this.minute == dt.minute && this.hour == dt.hour;
    }

    @Override
    public String toString() {
        return super.toString() + " "+ this.hour + ":" + this.minute;
    }

    @Override
    public int hashCode() {// Amir check this and see if it needs to be changed
        return super.hashCode();
    }

}
