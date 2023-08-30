import java.time.LocalDate;

public final class House extends ResidentialObject {
    private double plotArea;

    public House(String street, int buildingNumber, String localityName, String postCode, double area, double price, LocalDate offerEndDate, double plotArea) {
        super(street,buildingNumber,localityName, postCode,area,price, offerEndDate);
        this.plotArea = plotArea;
    }

    @Override
    public String toString() {
        return super.toString()+"\nLand area: "+this.plotArea;
    }


}
