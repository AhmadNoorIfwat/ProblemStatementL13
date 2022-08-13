package sg.edu.c346.id21010771.problemstatementl13;

public class StudentEnrolment {

    private String enrolment;
    private String year;

    public StudentEnrolment(String enrolment, String year){
    this.enrolment = enrolment;
    this.year = year;
    }

    public String getEnrolment() {
        return enrolment;
    }

    public void setEnrolment(String enrolment) {
        this.enrolment = enrolment;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "StudentEnrolment: " +
                "\nEnrolment= " + enrolment +
                "\nYear= " + year;
    }
}
