import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Calendar {

    private ArrayList<ArrayList<Meeting>> week = new ArrayList<>();
    public Calendar() {
        this(7);
    }

    public Calendar(int daysAmount) {
        for (int i = 1; i<= daysAmount; i++) {
            this.week.add(new ArrayList<>());
        }
    }


    public boolean addMeeting(int day, String opis, LocalTime startTime, LocalTime endTime, String priority) {
        if (!(Objects.equals(priority, "low") || Objects.equals(priority, "medium") || Objects.equals(priority, "high"))) {
            return false;
        }
        if (!(Meeting.earliestTime.isBefore(startTime) || Meeting.earliestTime.equals(startTime))) {
            return false;
        }
        if (endTime.isBefore(startTime) ) {
            return false;
        }
        if(day > this.week.size() || day < 1) return false;
        Meeting newMeeting = new Meeting(opis, startTime, endTime, priority);
        this.week.get(day -1).add(newMeeting);
        return true;
    }

    public boolean deleteMeeting(int day, int meetingNumber) {
        int daysAmount = week.get(day -1).size();
        if (daysAmount == 0 || meetingNumber> daysAmount || meetingNumber <0) return false;
        this.week.get(day -1).remove(meetingNumber-1);
        return true;
    }

    public ArrayList<Meeting> displayMeetings(int day, Predicate<Meeting> predicate) {
        ArrayList<Meeting> meetingsData = week.get(day -1);
        return meetingsData.stream().filter(predicate).collect(Collectors.toCollection(ArrayList::new));
    }

    public int meetingsCounterDay(int day) {
        return this.week.get(day -1).size();
    }
}
