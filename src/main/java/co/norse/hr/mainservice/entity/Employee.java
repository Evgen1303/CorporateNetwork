package co.norse.hr.mainservice.entity;

import co.norse.hr.mainservice.annotation.Phone;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;
    private String firstName;
    private String lastName;
    private Long birthday;
    @Email(message = "Not suitable format for Email")
    private String email;
    @Phone
    private String phone;
    private String roomNumber;
    private String position;
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "office_id")
    private Office office;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "employee")
    private Set<EmployeeProject> employeeProjects = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "employee")
    private Set<EmployeeSkill> employeeSkills = new HashSet<>();

    @Override
    public String toString() {
        return "Employee[" +
                "id=" + id +
                ", companyId=" + company.toString() +
                ", firstName='" + firstName + ", " +
                ", lastName='" + lastName + ", " +
                ", birthday=" + birthday +
                ", email='" + email + ", " +
                ", phone='" + phone + ", " +
                ", roomNumber='" + roomNumber + ", " +
                ", position='" + position + ", " +
                ", description='" + description + ", " +
                ", officeId=" + office.toString() +
                ']';
    }

    public Long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getBirthday() {
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

    public Office getOffice() {
        return office;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(Long birthday) {
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

    public void setOffice(Office office) {
        this.office = office;
    }
}