import static java.lang.Math.pow;

public class Cylinder {
    private double r; // radius of the cylinder
    private double h; // height of the cylinder

    //    constructors
    Cylinder(double r, double h) {
        this.h = h;
        this.r = r;
    }
    Cylinder() {}

    // setters
    public void setR(double newR) {
        this.r = newR;
    }
    public void setH(double newH) {
        this.h = newH;
    }

    // getters
    public double getH() {
        return this.h;
    }
    public double getR() {
        return this.r;
    }

    //    calculations
    public double calcBaseArea() {
        return Math.PI * pow(this.r, 2);
    }

    public double calcSideArea() {
        return 2 * Math.PI * this.r * this.h;
    }

    public double calcTotalArea() {
        return 2 * calcBaseArea() * calcSideArea();
    }

    public double calcVolume() {
        return calcBaseArea() * this.h;
    }

}
