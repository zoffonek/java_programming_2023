import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Program to calculate the base, side and total area of a cylinder. You can enter height and radius of the cylinder base");
        Cylinder cylinder = new Cylinder();
        boolean run = true;
        while (run) {
            System.out.println("\nChoose a number: \n1-Set radius of the base\n2-Set height\n3-Show height and radius\n4-Show areas and volume\n5-Exit");
            Scanner cylinderValues = new Scanner(System.in);
            Scanner options = new Scanner(System.in);
            int option = options.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("Please enter cylinder radius: ");
                    double newR = cylinderValues.nextDouble();
                    cylinder.setR(newR);
                }
                case 2 -> {
                    System.out.println("Please enter cylinder height: ");
                    double newH = cylinderValues.nextDouble();
                    cylinder.setH(newH);
                }
                case 3 -> System.out.println("Height: " + cylinder.getH() + "\nRadius: " + cylinder.getR());
                case 4 -> System.out.println("Volume: " + cylinder.calcVolume() + "\nAreas:\nBase " + cylinder.calcBaseArea() + "\nSide " + cylinder.calcSideArea() + "\nTotal: " + cylinder.calcTotalArea());
                case 5 -> run = false;
            }
        }
    }
}