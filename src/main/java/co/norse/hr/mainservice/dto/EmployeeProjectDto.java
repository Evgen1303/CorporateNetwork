package co.norse.hr.mainservice.dto;

public class EmployeeProjectDto {

    private Long employee;
    private Long project;

    private String position;
    private int start;
    private int end;

    @Override
    public String toString() {
        return "Project[" +
                ", employeeId=" + employee.toString() +
                ", projectId=" + project.toString() +
                ", position=" + position +
                ", start=" + start +
                ", end=" + end +
                ']';
    }

    public Long getEmployee() {
        return employee;
    }

    public Long getProject() {
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

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public void setProject(Long project) {
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
