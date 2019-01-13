package co.norse.hr.mainservice.entity;

import javax.persistence.*;

@Entity
public class EmployeeProject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private  int employee_id;
    private  int project_id;
    private  String position;
    private int start;
    private int end;

    public EmployeeProject(){}

    @Override
    public String toString(){
        return "Project[" +
                "id=" + id +
                ", employee_id=" + employee_id +
                ", project_id=" + project_id  +
                ", position=" + position  +
                ", start=" + start  +
                ", end=" + end  +
                ']';
    }

    public int getId() {
        return id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public int getProject_id() {
        return project_id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
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
