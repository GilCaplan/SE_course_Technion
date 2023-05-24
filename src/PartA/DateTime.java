package PartA;

public class DateTime extends Date implements checkType {
    private int hour;
    private int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
        super(year, month, day);
        this.hour = (hour>=0 && hour<=23) ? hour : 0 ;
        this.minute = (minute>=0 && minute<=59) ? minute : 0;
    }

    public void setHour(int hour) {
        this.hour = (hour>=0 && hour<=23) ? hour : 0 ;
    }

    public void setMinute(int minute) {
        this.minute = (minute>=0 && minute<=59) ? minute : 0;
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = super.equals(obj);//use .equals from superclass Date
        if(!flag || !(obj instanceof DateTime))
            return false;
        DateTime dt = (DateTime) obj;
        return this.minute == dt.minute && this.hour == dt.hour;
    }

    @Override
    public String toString() {
        String hour = String.valueOf(this.hour).length()==1?"0"+this.hour:String.valueOf(this.hour);
        String minute = String.valueOf(this.minute).length()==1?"0"+this.minute:String.valueOf(this.minute);
        return super.toString() + " "+ hour + ":" + minute;
    }

    @Override
    public int hashCode() {// Amir check this and see if it needs to be changed
        return super.hashCode();
    }//don't think we need to change?

    @Override
    public boolean isDateTime() {
        return true;
    }
}
