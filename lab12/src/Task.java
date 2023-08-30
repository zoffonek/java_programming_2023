import java.time.LocalTime;

public final class Task extends CalendarElement {
    private String status; // planned, confirmed, implemented, done


    public Task(String description, LocalTime startTime, LocalTime endTime, String status) {
        super(description, startTime, endTime);
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString()+"\nStatus: "+this.status;
    }
    public String getStatus() {
        return this.status;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }
    public LocalTime getEndTime() {
        return this.endTime;
    }

}
