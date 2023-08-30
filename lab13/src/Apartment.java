import java.time.LocalDate;

public final class Apartment extends ResidentialObject {
    private int apartmentNumber;
    private int floorNumber;

    public Apartment(String street, int buildingNumber, String localityName, String postCode, double area, double price, LocalDate offerEndDate, int apartmentNumber, int floorNumber) {
        super(street,buildingNumber,localityName, postCode,area,price, offerEndDate);
        this.apartmentNumber = apartmentNumber;
        this.floorNumber = floorNumber;
    }

    @Override
    public String toString() {
        return super.toString()+"\nApartment number: "+this.apartmentNumber +"\nFloor number: "+this.floorNumber;
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }
}
