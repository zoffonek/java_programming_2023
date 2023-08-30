import java.time.LocalTime;

public class Meeting {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;

    private String priority;

    public static LocalTime earliestTime = LocalTime.of(9,0);

    public Meeting(String description, LocalTime startTime, LocalTime endTime, String priority ) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Meeting time: "+this.startTime +" - "+this.endTime +"\nPriority: "+this.priority +"\nDescription: "+this.description;
    }

    public String getPriority() {
        return this.priority;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }
    public LocalTime getEndTime() {
        return this.endTime;
    }



}
