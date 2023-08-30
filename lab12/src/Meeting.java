import java.time.LocalTime;

public final class Meeting extends CalendarElement {
    private String priority; // low, medium, high

    public Meeting(String description, LocalTime startTime, LocalTime endTime, String priority ) {
        super(description, startTime, endTime);
        this.priority = priority;

    }

    @Override
    public String toString() {
        return super.toString()+"\nPriority: "+this.priority;
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
