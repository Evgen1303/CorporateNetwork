package co.norse.hr.mainservice.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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


    protected Employee() {}

    public Employee(int id, int companyId, String firstName, String lastName, int birthday, String email,
                    String phone, String roomNumber, String position, String description, int officeId) {
        this.id = id;
        this.companyId = companyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.roomNumber = roomNumber;
        this.position = position;
        this.description = description;
        this.officeId = officeId;
    }

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

}