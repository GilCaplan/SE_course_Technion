public class DateTime extends Date{
    private int hour;
    private int minute;
    @Override
    public boolean equals(Object obj) {
        boolean flag = super.equals(obj);
        if(!flag || !(obj instanceof DateTime))
            return false;
        DateTime dt = (DateTime) obj;//
        return this.minute == dt.minute && this.hour == dt.hour;
    }

    @Override
    public String toString() {
        return super.toString() + "DateTime {" + "hour=" + hour + ", minute=" + minute + '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
