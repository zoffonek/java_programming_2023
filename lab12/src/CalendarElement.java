import java.time.LocalTime;

abstract sealed class CalendarElement permits Meeting, Task {
    public static final LocalTime EARLIEST_TIME = LocalTime.of(9,0);
    protected String description;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public CalendarElement(String description, LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Time: "+this.startTime +" - "+this.endTime +"\nDecsription: "+this.description;
    };


}
