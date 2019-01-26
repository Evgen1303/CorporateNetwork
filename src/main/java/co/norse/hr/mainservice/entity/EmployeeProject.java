package co.norse.hr.mainservice.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class EmployeeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "employee_ld", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "project_ld", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    private String position;
    private int start;
    private int end;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeProject that = (EmployeeProject) o;
        return start == that.start &&
                end == that.end &&
                Objects.equals(id, that.id) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(project, that.project) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, project, position, start, end);
    }

    @Override
    public String toString() {
        return "Project[" +
                "id=" + id +
                ", employeeId=" + employee.toString() +
                ", projectId=" + project.toString() +
                ", position=" + position +
                ", start=" + start +
                ", end=" + end +
                ']';
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
    }

    public String getPosition() {
        return position;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
