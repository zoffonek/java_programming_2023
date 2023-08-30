import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {

    public static void displayMeetings(ArrayList<Meeting> meetings) {
        if (meetings.size() == 0) System.out.println("No meetings");
        else for (Meeting s: meetings) {
            System.out.println(s.toString()+"\n");
        }
    }
    public static void addNewMeeting(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to hold the meeting (a number from 1 to 7).\n 1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();

        System.out.println("Specify the start time in the format 00:00, minimum from "+ Meeting.earliestTime);
        String startTime = scanLine.nextLine();

        System.out.println("Specify the ending time in the format 00:00");
        String endTime = scanLine.nextLine();

        System.out.println("Give a description of the meeting");
        String description = scanLine.nextLine();

        System.out.println("Give the priority of the meeting [low, medium, high].");
        String priority = scanLine.nextLine();

        boolean actionSuccess = calendar.addMeeting(day, description, LocalTime.parse(startTime), LocalTime.parse(endTime), priority );
        if (actionSuccess) System.out.println("Successfully added a meeting!");
        else System.out.println("Meeting could not be added. Please try again");
    }
    public static void deleteSelectedMeeting(Calendar calendar, Scanner scanInt) {
        System.out.println("Enter the day on which you want to delete the meeting (a number from 1 to 7).\n1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();

        System.out.println("Enter the number - which meeting you want to delete. The number of meetings on a given day:"+ calendar.meetingsCounterDay(day));
        int meetingNumber = scanInt.nextInt();

        boolean actionSuccess = calendar.deleteMeeting(day, meetingNumber);
        if (actionSuccess) {
            System.out.println("Successfully removed");
        } else System.out.println("Meeting could not be deleted");
    }
    public static void displayMeetingsDay(Calendar calendar, Scanner scanInt) {
        System.out.println("Enter the day on which you want to display the meeting (a number from 1 to 7).\n" +
                "1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        Predicate<Meeting> predicate = s -> true;
        ArrayList<Meeting> meetings = calendar.displayMeetings(day, predicate);
        displayMeetings(meetings);
    }
    public static void displayMeetingsDayPriority(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to display the meeting (a number from 1 to 7).\n" +
                "1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        System.out.println("Specify meeting priority [low, medium, high].");
        String priority = scanLine.nextLine();
        Predicate<Meeting> predicate = s -> s.getPriority().equals(priority);
        ArrayList<Meeting> meetings = calendar.displayMeetings(day, predicate);
        displayMeetings(meetings);
    }

    private static void displayMeetingsDayStartTime(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to display the meeting (a number from 1 to 7).\n" +
                "1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        System.out.println("Specify the start time in the format 00:00, minimum from "+ Meeting.earliestTime);
        String startTime = scanLine.nextLine();
        Predicate<Meeting> predicate = s -> s.getStartTime().equals(LocalTime.parse(startTime)) || s.getStartTime().isBefore(LocalTime.parse(startTime));
        ArrayList<Meeting> meetings = calendar.displayMeetings(day, predicate);
        displayMeetings(meetings);
    }

    private static void displayMeetingsDayBetweenTime(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to display the meeting (a number from 1 to 7).\n" +
                "1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        System.out.println("Specify the start time in the format 00:00, minimum from "+ Meeting.earliestTime);
        String startTime = scanLine.nextLine();
        System.out.println("Specify the end time in the format 00:00");
        String endTime = scanLine.nextLine();

        Predicate<Meeting> predicate = s -> (s.getStartTime().isAfter(LocalTime.parse(startTime)) || s.getStartTime().equals(LocalTime.parse(startTime))) ||
                (s.getStartTime().isBefore(LocalTime.parse(endTime)) || s.getStartTime().equals(LocalTime.parse(endTime)));
        ArrayList<Meeting> meetings = calendar.displayMeetings(day, predicate);
        displayMeetings(meetings);
    }
    private static void displayMeetingsDayPriorityStartTime(Calendar calendar, Scanner scanInt, Scanner scanLine) {
        System.out.println("Enter the day on which you want to display the meeting (a number from 1 to 7).\n" +
                "1-Monday, 2-Tuesday, 3-Wednesday, 4-Thursday, 5-Friday, 6-Saturday, 7-Sunday");
        int day = scanInt.nextInt();
        System.out.println("Specify meeting priority [low, medium, high].");
        String priority = scanLine.nextLine();
        System.out.println("Specify the start time in the format 00:00, minimum from "+ Meeting.earliestTime);
        String startTime = scanLine.nextLine();
        Predicate<Meeting> predicate = s -> (s.getStartTime().equals(LocalTime.parse(startTime)) || s.getStartTime().isAfter(LocalTime.parse(startTime))) && s.getPriority().equals(priority);
        ArrayList<Meeting> meetings = calendar.displayMeetings(day, predicate);
        displayMeetings(meetings);
    }

    public static void main(String[] args) {

        System.out.println("The program is used to keep a calendar of weekly meetings.");
        Calendar calendar = new Calendar();
        boolean run = true;
        while (run) {
            System.out.println("""
                    Select a number:\s
                    1-Add a meeting
                    2-Delete a meeting
                    3-Display all meetings on selected day
                    4-Display all meetings on selected day and priority
                    5-Display all meetings on selected day and start time
                    6-Display all meetings on selected day between specific time
                    7-Display all meetings on selected day, priority and start time
                    8-Exit
                    """);
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
                case 6 -> displayMeetingsDayBetweenTime(calendar, scanInt, scanLine);
                case 7 -> displayMeetingsDayPriorityStartTime(calendar, scanInt, scanLine);
                case 8 -> run = false;
            }
        }
    }


}