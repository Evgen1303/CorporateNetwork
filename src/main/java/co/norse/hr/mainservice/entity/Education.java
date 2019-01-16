package co.norse.hr.mainservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id")
    private Long id;
    private String name;
    private String description;
    private int start;
    private int end;
    private Employee employeeId;

    @Override
    public String toString() {
        return "Education[" +
                "id=" + id +
                ", name=" + name +
                ", description='" + description + ", " +
                ", start='" + start + ", " +
                ", end=" + end +
                ", employee_id='" + employeeId + ", " +
                ']';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }
}
