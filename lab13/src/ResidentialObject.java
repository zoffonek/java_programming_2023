import java.time.LocalDate;

abstract sealed class ResidentialObject permits House, Apartment {
    protected String street;
    protected int buildingNumber;
    protected String localityName;
    protected String postCode;
    protected double area;
    protected double price;
    protected LocalDate offerEndDate;

    public ResidentialObject(String street, int buildingNumber, String localityName, String postCode, double area, double price, LocalDate offerEndDate) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.localityName = localityName;
        this.postCode = postCode;
        this.area = area;
        this.price = price;
        this.offerEndDate = offerEndDate;
    }

    @Override
    public String toString() {
        return "Street: "+this.street +"\nBuilding number: "+this.buildingNumber +"\nLocality: "+this.localityName +"\nPostcode: "+this.postCode +"\nArea: "+this.area +"\nPrice: "+this.price +"\nOffer end date: "+this.offerEndDate;
    }

    public String getLocalityName() {
        return this.localityName;
    }

    public double getArea() {
        return this.area;
    }

    public double getPrice() {
        return this.price;
    }

    public LocalDate getOfferEndDate() {
        return this.offerEndDate;
    }
}
