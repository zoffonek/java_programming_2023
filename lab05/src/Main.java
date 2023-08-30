import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("The program is used to store partial grades, add new ones, calculate the grade point average and find the minimum and the maximum grade.");
        GradeList gradeList1 = new GradeList();
        boolean run = true;
        while (run) {
            System.out.println("\nChoose a number: \n1-Add a new grade\n2-Calculate the grade point average\n3-View the highest grade\n4-View the lowest grade\n5-Exit");
            Scanner newGrades = new Scanner(System.in);
            Scanner options = new Scanner(System.in);
            int option = options.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("Enter the new grade: ");
                    double newGrade = newGrades.nextDouble();
                    gradeList1.addGrade(newGrade);
                }
                case 2 -> {
                    if (gradeList1.avg() == 0.0) {
                        System.out.println("No grades");
                    } else {
                        System.out.println("The grade point average: "+ gradeList1.avg());
                    }
                }
                case 3 -> {
                    if (gradeList1.findMax() == 0.0) {
                        System.out.println("No grades");
                    } else {
                        System.out.println("The highest grade: "+ gradeList1.findMax());
                    }
                }
                case 4 -> {
                    if (gradeList1.findMin() == 0.0) {
                        System.out.println("No grades");
                    } else {
                        System.out.println("The lowest grade: "+ gradeList1.findMin());
                    }
                }
                case 5 -> run = false;
            }
        }

    }
}