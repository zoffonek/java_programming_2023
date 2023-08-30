import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {

    public static void menu() {
        System.out.println("""
                \nSelect a number:\s
                1-Add a house sale offer
                2-Add an apartment sale offer
                3-View current home sales offers
                4-View current apartment sales offers
                5-Display current offers for the sale of houses in a given town and not less than the specified area
                6-Display current offers for the sale of apartments in a given town, minimum floor and not more expensive than the given price
                7-Exit
                """);
    }
    public static void main(String[] args) {
        System.out.println("The program is used to create and display offers for sale of houses and apartments.");
        OffersList offersList = new OffersList();
        boolean run = true;
        while (run) {
            menu();
            Scanner options = new Scanner(System.in);
            Scanner scanInt = new Scanner(System.in);
            Scanner scanDouble = new Scanner(System.in);
            Scanner scanText = new Scanner(System.in);

            int option = options.nextInt();
            switch (option) {
                case 1 -> addHouse(offersList, scanInt, scanDouble, scanText);
                case 2 -> addApartment(offersList, scanInt, scanDouble, scanText);
                case 3 -> displayCurrentHouses(offersList);
                case 4 -> displayCurrentApartment(offersList);
                case 5 -> displayCurrentHousesLocalityArea(offersList, scanDouble,  scanText);
                case 6 -> displayCurrentApartmentLocalityFloor(offersList, scanInt, scanDouble,  scanText);
                case 7 -> run = false;
            }
        }

    }

    public static void displayCurrentApartmentLocalityFloor(OffersList offersList, Scanner scanInt, Scanner scanDouble, Scanner scanText) {
        System.out.println("Enter locality name: ");
        String localityName = scanText.nextLine();

        System.out.println("Enter the price (displayed will not be greater than the given value): ");
        double price = scanDouble.nextDouble();

        System.out.println("Enter the floor number (displayed will be no less than the given value): ");
        int floorNumber = scanInt.nextInt();

        Predicate<ResidentialObject> predicate = residentialObject -> residentialObject instanceof Apartment && !residentialObject.getOfferEndDate().isBefore(LocalDate.now()) && residentialObject.getLocalityName().equals(localityName) && residentialObject.getPrice()<= price && ((Apartment) residentialObject).getFloorNumber()>= floorNumber;
        ArrayList<ResidentialObject> elements = offersList.getOffersList(predicate);
        displayOffers(elements);
    }

    public static void displayCurrentHousesLocalityArea(OffersList offersList, Scanner scanDouble, Scanner scanText) {
        System.out.println("Enter locality name: ");
        String localityName = scanText.nextLine();

        System.out.println("Enter the area in m2 (displayed will be not less than the given value): ");
        double area = scanDouble.nextDouble();

        Predicate<ResidentialObject> predicate = residentialObject -> residentialObject instanceof House && !residentialObject.getOfferEndDate().isBefore(LocalDate.now()) && residentialObject.getLocalityName().equals(localityName) && residentialObject.getArea()>= area;
        ArrayList<ResidentialObject> elements = offersList.getOffersList(predicate);
        displayOffers(elements);
    }

    public static void displayOffers(ArrayList<ResidentialObject> elements) {
        if (elements.size() == 0) System.out.println("No elements to display");
        else {
            for (ResidentialObject e: elements) {
                System.out.println(e.toString()+"\n");
            }
        }
    }
    public static void displayCurrentHouses(OffersList offersList) {
        Predicate<ResidentialObject> predicate = residentialObject -> residentialObject instanceof House && !residentialObject.getOfferEndDate().isBefore(LocalDate.now());
        ArrayList<ResidentialObject> elements = offersList.getOffersList(predicate);
        displayOffers(elements);

    }

    public static void displayCurrentApartment(OffersList offersList) {
        Predicate<ResidentialObject> predicate = residentialObject -> residentialObject instanceof Apartment && !residentialObject.getOfferEndDate().isBefore(LocalDate.now());
        ArrayList<ResidentialObject> elements = offersList.getOffersList(predicate);
        displayOffers(elements);
    }

    public static void addHouse(OffersList offersList, Scanner scanInt, Scanner scanDouble, Scanner scanText) {
        System.out.println("Enter streets:");
        String street = scanText.nextLine();
        System.out.println("Enter building number: ");
        int buildingNumber = scanInt.nextInt();
        System.out.println("Enter locality name: ");
        String localityName = scanText.nextLine();
        System.out.println("Enter postcode: ");
        String postCode = scanText.nextLine();
        System.out.println("Enter the area in m2: ");
        double area = scanDouble.nextDouble();
        System.out.println("Enter the price: ");
        double price = scanDouble.nextDouble();
        System.out.println("Enter offer end date (in YYYY-MM-DD format): ");
        String offerEndDate = scanText.nextLine();
        System.out.println("Enter plot area: ");
        double plotArea = scanDouble.nextDouble();

        House newHouse = new House(street, buildingNumber, localityName, postCode, area, price, LocalDate.parse(offerEndDate), plotArea);
        offersList.addOffer(newHouse);
        System.out.println("Successfully added!");
    }

    public static void addApartment(OffersList offersList, Scanner scanInt, Scanner scanDouble, Scanner scanText) {
        System.out.println("Enter streets:");
        String street = scanText.nextLine();
        System.out.println("Enter building number: ");
        int buildingNumber = scanInt.nextInt();
        System.out.println("Enter locality name: ");
        String localityName = scanText.nextLine();
        System.out.println("Enter postcode: ");
        String postCode = scanText.nextLine();
        System.out.println("Enter the area in m2: ");
        double area = scanDouble.nextDouble();
        System.out.println("Enter the price: ");
        double price = scanDouble.nextDouble();
        System.out.println("Enter offer end date (in YYYY-MM-DD format): ");
        String offerEndDate = scanText.nextLine();
        System.out.println("Enter apartment number: ");
        int apartmentNumber = scanInt.nextInt();
        System.out.println("Enter floor number: ");
        int floorNumber = scanInt.nextInt();

        Apartment newApartment = new Apartment(street, buildingNumber, localityName, postCode, area, price, LocalDate.parse(offerEndDate), apartmentNumber, floorNumber);
        offersList.addOffer(newApartment);
        System.out.println("Successfully added!");
    }


}