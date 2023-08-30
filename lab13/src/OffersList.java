import java.util.ArrayList;
import java.util.function.Predicate;

public class OffersList {

    private ArrayList<ResidentialObject> offersList = new ArrayList<>();

    public OffersList() {

    };

    public ArrayList<ResidentialObject> getOffersList() {
        return this.offersList;
    }

    public ArrayList<ResidentialObject> getOffersList(Predicate<ResidentialObject> predicate) {
        ArrayList<ResidentialObject> residentialObjects = new ArrayList<>();
        for (ResidentialObject object: this.offersList) {
            if(predicate.test(object)) {
                residentialObjects.add(object);
            }
        }
        return residentialObjects;

    }


    public boolean addOffer(House newHouse) {
        this.offersList.add(newHouse);
        return true;
    }

    public boolean addOffer(Apartment newApartment) {
        this.offersList.add(newApartment);
        return true;
    }


}
