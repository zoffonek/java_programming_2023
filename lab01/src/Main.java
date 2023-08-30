import java.util.Scanner;

public class Main {

    public static long factorial(int n) {
        if (n < 1) {
            return 1;
        }
        int res = 1;
        for(int i=1; i<=n; i++) {
            res *= i;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Factorial calculation program (n!).");
        boolean run = true;
        while(run) {
            System.out.println("Please enter n: ");
            Scanner scanInt = new Scanner(System.in);
            int n = scanInt.nextInt();

            if(n>=1) {
                long res = factorial(n);
                System.out.println("Result of "+n+"! is: "+res);
            } else {
                System.out.println("'n' must be a natural number!");
            }

            System.out.println("Do you want to exit? (y/n)");
            Scanner scanStr = new Scanner(System.in);
            String quit = scanStr.nextLine();
            if(quit.equals("y")){
                run = false;
            }
        }
    }
}