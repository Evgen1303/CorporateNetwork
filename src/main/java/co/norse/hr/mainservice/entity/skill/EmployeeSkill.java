package co.norse.hr.mainservice.entity.skill;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeSkill {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private int employeeId;
    private int projectId;
    private int level;

    public EmployeeSkill() {
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "EmployeeSkill{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", projectId=" + projectId +
                ", level=" + level +
                '}';
    }
}
