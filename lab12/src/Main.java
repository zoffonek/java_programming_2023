import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {

    public static void menu() {
        System.out.println("""
                    \nSelect a number:\s
                    1-Add a meeting
                    2-Delete a meeting
                    3-Display all meetings on selected day
                    4-Display all meetings on selected day and priority
                    5-Display all meetings on selected day and start time
                    6-Add a task
                    7-Delete a task
                    8-Display all tasks on selected day
                    9-Display all tasks on selected day and status
                    10-Display all tasks on selected day, status and start time
                    11-Exit
                    """);
    }

    public static void main(String[] args) {
        System.out.println("The program is used to keep a calendar of weekly meetings and tasks.");
        Calendar calendar = new Calendar();
        boolean run = true;
        while (run) {
            menu();
            Scanner options = new Scanner(System.in);
            Scanner scanInt = new Scanner(System.in);
            Scanner scanLine = new Scanner(System.in);

            int option = options.nextInt();
            switch (option) {
                case 1 -> addNewMeeting(calendar, scanInt, scanLine);
                case 2 -> deleteSelectedMeeting(calendar, scanInt);
                case 3 -> displayMeetingsDay(calendar, scanInt);
                case 4 -> displayMeetingsDayPriority(calendar, scanInt, scanLine);
                case 5 -> displayMeetingsDayStartTime(calendar, scanInt, scanLine);

                case 6 -> addNewTask(calendar, scanInt, scanLine);
                case 7 -> deleteSelectedTask(calendar, scanInt);
                case 8 -> displayTasksDay(calendar, scanInt);
                case 9 -> displayTasksDayStatus(calendar, scanInt, scanLine);
                case 10 -> displayTasksDayStatusStartTime(calendar, scanInt, scanLine);

                case 11 -> run = false;
            }
        }
    }

    public static void displayCalendarElements(ArrayList<CalendarElement> calendarElements) {
        if (calendarElements.size() == 0) System.out.println("No elements");
        else {
            for (CalendarElement e: calendarElements) {
                System.out.println(e.toString()+"\n");
            }
        }
    }

    public static void displayCalendarElementsWithId(ArrayList<CalendarElement> calendarElements, int day, Calendar calendar) {
        if (calendarElements.size() == 0) System.out.println("No elements");
        else {
            for (CalendarElement e: calendarElements) {
                System.out.println("Number: "+ calendar.getId(day, e));
                System.out.println(e.toString()+"\n");
            }
        }
    }

    public static void addNewMeeting(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to hold the meeting (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        System.out.println("Specify the start time in the format 00:00, minimum from "+ CalendarElement.EARLIEST_TIME);
        String startTime = scanLine.nextLine();
        System.out.println("Specify the ending time in the format 00:00");
        String endTime = scanLine.nextLine();
        System.out.println("Enter a description of the meeting");
        String description = scanLine.nextLine();
        System.out.println("Enter the priority of the meeting [low, medium, high].");
        String priority = scanLine.nextLine();

        Meeting newMeeting =new Meeting(description, LocalTime.parse(startTime), LocalTime.parse(endTime), priority);
        boolean actionSuccess = calendar.addCalendarElement(newMeeting, day);
        if (actionSuccess) System.out.println("Successfully added a meeting!");
        else System.out.println("Meeting could not be added. Please try again");
    }

    public static void addNewTask(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to hold the task (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        System.out.println("Specify the start time in the format 00:00, minimum from "+ CalendarElement.EARLIEST_TIME);
        String startTime = scanLine.nextLine();
        System.out.println("Specify the ending time in the format 00:00");
        String endTime = scanLine.nextLine();
        System.out.println("Enter a description of the task");
        String description = scanLine.nextLine();
        System.out.println("Enter the status of the task [planned, confirmed, implemented, done].");
        String status = scanLine.nextLine();

        Task task = new Task(description, LocalTime.parse(startTime), LocalTime.parse(endTime), status);
        boolean actionSuccess = calendar.addCalendarElement(task, day);
        if (actionSuccess) System.out.println("Successfully added a task!");
        else System.out.println("Task could not be added. Please try again");
    }

    public static void deleteSelectedMeeting(Calendar calendar, Scanner scanInt) {
        System.out.println("Enter the day on which you want to delete the meeting (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();

        Predicate<CalendarElement> predicate = s -> s instanceof Meeting;
        ArrayList<CalendarElement> meetings = calendar.selectElements(day, predicate);
        System.out.println("This is the meeting list for the day:\n");
        displayCalendarElementsWithId(meetings, day, calendar);

        System.out.println("Enter number - which meeting do you want to delete?");
        int meetingNumber = scanInt.nextInt();

        boolean actionSuccess = calendar.deleteElement(day, meetingNumber);
        if (actionSuccess) {
            System.out.println("Successfully deleted");
        } else System.out.println("The meeting could not be deleted");
    }

    public static void deleteSelectedTask(Calendar calendar, Scanner scanInt) {
        System.out.println("Enter the day on which you want to delete the task (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();


        System.out.println("Enter number - which task do you want to delete?");
        int taskNumber = scanInt.nextInt();

        Predicate<CalendarElement> predicate = task -> task instanceof Task;
        ArrayList<CalendarElement> tasks = calendar.selectElements(day, predicate);
        System.out.println("This is the tasks list for the day:\n");
        displayCalendarElementsWithId(tasks, day, calendar);

        boolean actionSuccess = calendar.deleteElement(day, taskNumber);
        if (actionSuccess) {
            System.out.println("Successfully deleted");
        } else System.out.println("The task could not be deleted");
    }
    public static void displayMeetingsDay(Calendar calendar, Scanner scanInt) {
        System.out.println("Enter the day on which you want to display the meetings (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        Predicate<CalendarElement> predicate = meeting -> meeting instanceof Meeting;
        ArrayList<CalendarElement> meetings = calendar.selectElements(day, predicate);
        displayCalendarElements(meetings);
    }

    public static void displayTasksDay(Calendar calendar, Scanner scanInt) {
        System.out.println("Enter the day on which you want to display the tasks (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        Predicate<CalendarElement> predicate = task -> task instanceof Task;
        ArrayList<CalendarElement> tasks = calendar.selectElements(day, predicate);
        displayCalendarElements(tasks);
    }
    public static void displayMeetingsDayPriority(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to display the meetings (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        System.out.println("Enter the priority of the meeting [low, medium, high].");
        String priority = scanLine.nextLine();
        Predicate<CalendarElement> predicate = element -> {
            if(element instanceof Meeting meeting) {
                return meeting.getPriority().equals(priority);
            } else return false;
        };
        ArrayList<CalendarElement> meetings = calendar.selectElements(day, predicate);
        displayCalendarElements(meetings);
    }

    public static void displayTasksDayStatus(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to display the tasks (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        System.out.println("Enter the status of the task [planned, confirmed, implemented, done].");
        String status = scanLine.nextLine();
        Predicate<CalendarElement> predicate = element -> {
            if(element instanceof Task task) {
                return task.getStatus().equals(status);
            } else return false;
        };
        ArrayList<CalendarElement> tasks = calendar.selectElements(day, predicate);
        displayCalendarElements(tasks);
    }
    private static void displayMeetingsDayStartTime(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to display the meetings (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        System.out.println("Enter the priority of the meeting [low, medium, high].");
        String priority = scanLine.nextLine();
        System.out.println("Specify the start time in the format 00:00, minimum from "+ CalendarElement.EARLIEST_TIME);
        String startTime = scanLine.nextLine();

        Predicate<CalendarElement> predicate = element -> {
            if(element instanceof Meeting meeting) {
                return (meeting.getStartTime().equals(LocalTime.parse(startTime)) || meeting.getStartTime().isAfter(LocalTime.parse(startTime))) && meeting.getPriority().equals(priority);
            } else return false;
        };
        ArrayList<CalendarElement> meetings = calendar.selectElements(day, predicate);
        displayCalendarElements(meetings);
    }

    private static void displayTasksDayStatusStartTime(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to display the tasks (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        System.out.println("Enter the status of the task [planned, confirmed, implemented, done].");
        String status = scanLine.nextLine();
        System.out.println("Enter the ending time in the format 00:00");
        String endTime = scanLine.nextLine();

        Predicate<CalendarElement> predicate = element -> {
            if(element instanceof Task task) {
                return (task.getEndTime().equals(LocalTime.parse(endTime)) || task.getEndTime().isBefore(LocalTime.parse(endTime))) && task.getStatus().equals(status);
            } else return false;
        };
        ArrayList<CalendarElement> tasks = calendar.selectElements(day, predicate);
        displayCalendarElements(tasks);
    }

}
