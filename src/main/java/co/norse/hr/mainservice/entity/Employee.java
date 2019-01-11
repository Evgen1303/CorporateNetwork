package co.norse.hr.mainservice.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private int companyId;
    private String firstName;
    private String lastName;
    private int birthday;
    private String email;
    private String phone;
    private String roomNumber;
    private String position;
    private String description;
    private int officeId;

    @Override
    public String toString() {
        return "Employee[" +
                "id=" + id +
                ", companyId=" + companyId +
                ", firstName='" + firstName + ", " +
                ", lastName='" + lastName + ", " +
                ", birthday=" + birthday +
                ", email='" + email + ", " +
                ", phone='" + phone + ", " +
                ", roomNumber='" + roomNumber + ", " +
                ", position='" + position + ", " +
                ", description='" + description + ", " +
                ", officeId=" + officeId +
                ']';
    }

    public int getId() {
        return id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
}