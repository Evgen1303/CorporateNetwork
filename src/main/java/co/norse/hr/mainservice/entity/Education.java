package co.norse.hr.mainservice.entity;

import javax.persistence.*;

@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;
    private int start;
    private int end;
    private Employee employee_id;

    @Override
    public String toString() {
        return "Education[" +
                "id=" + id +
                ", name=" + name +
                ", description='" + description + ", " +
                ", start='" + start + ", " +
                ", end=" + end +
                ", employee_id='" + employee_id + ", " +
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
        return employee_id;
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

    public void setEmployeeId(Employee employee_id) {
        this.employee_id = employee_id;
    }
}
