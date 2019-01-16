package co.norse.hr.mainservice.dto;

public class EmployeeDto {
    private Long companyId;
    private String firstName;
    private String lastName;
    private int birthday;
    private String email;
    private String phone;
    private String roomNumber;
    private String position;
    private String description;
    private Long officeId;

    @Override
    public String toString() {
        return "Employee[" +
                ", companyId=" + companyId.toString() +
                ", firstName='" + firstName + ", " +
                ", lastName='" + lastName + ", " +
                ", birthday=" + birthday +
                ", email='" + email + ", " +
                ", phone='" + phone + ", " +
                ", roomNumber='" + roomNumber + ", " +
                ", position='" + position + ", " +
                ", description='" + description + ", " +
                ", officeId=" + officeId.toString() +
                ']';
    }

    public Long getCompanyId() {
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

    public Long getOfficeId() {
        return officeId;
    }

    public void setCompanyId(Long companyId) {
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

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }
}
