package TFI.Models;

public class Dentist {
    private int id;
    private int licenseNumber;
    private String firstName;
    private String lastName;

    public Dentist(int licenseNumber, String firstName, String lastName) {
        this.licenseNumber = licenseNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }
    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "licenseNumber=" + licenseNumber +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                '}';
    }
}
