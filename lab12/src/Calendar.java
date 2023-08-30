import java.util.ArrayList;
import java.util.function.Predicate;
public class Calendar {

    private ArrayList<ArrayList<CalendarElement>> week = new ArrayList<>();
    public Calendar() {
        this(7);
    }

    public Calendar(int daysAmount) {
        for ( int i=1; i<=daysAmount; i++) {
            this.week.add(new ArrayList<>());
        }
    }

    public boolean addCalendarElement(Meeting meeting, int day) {
        this.week.get(day -1).add(meeting);
        return true;
    }

    public boolean addCalendarElement(Task task, int day) {
        this.week.get(day -1).add(task);
        return true;
    }

    public int getId(int day, CalendarElement element) {
        return this.week.get(day -1).indexOf(element);
    }

    public boolean deleteElement(int day, int elementNumber) {
        int daysAmount = week.get(day -1).size();
        if (elementNumber >= daysAmount || elementNumber < 0) return false;
        this.week.get(day -1).remove(elementNumber);
        return true;
    }
    public ArrayList<CalendarElement> selectElements(int day, Predicate<CalendarElement> predicate) {
        ArrayList<CalendarElement> elementy = new ArrayList<>();
        for (CalendarElement s: week.get(day -1)) {
            if(predicate.test(s)) {
                elementy.add(s);
            }
        }
        return elementy;
    }
}
