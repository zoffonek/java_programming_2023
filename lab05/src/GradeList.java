import java.util.ArrayList;

public class GradeList {

    private final ArrayList<Double> grades;
    GradeList() {
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        this.grades.add(grade);

    }
    public double avg() {
        if (!this.grades.isEmpty()) {
            double sum = 0.0;
            for (Double grade : this.grades) {
                sum += grade;
            }
            return sum/this.grades.size();

        } else {
            return 0.0;
        }
    }

    public double findMax() {
        double max = 0.0;
        if (!this.grades.isEmpty()) {
            for (Double grade : this.grades) {
                if (grade > max) max = grade;
            }
        }
        return max;
    }

    public double findMin() {
        if (!this.grades.isEmpty()) {
            double min = this.grades.get(0);
            for (Double grade : this.grades) {
                if (grade < min) min = grade;
            }
            return min;
        } else {
            return 0.0;
        }
    }

}
