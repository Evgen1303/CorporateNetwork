package co.norse.hr.mainservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
//@SecondaryTable(name = "employee")
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

   /* @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Skill.class)
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Skill skillId;*/

    @NotNull
    private int level;

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /*public Skill getSkillId() {
        return skillId;
    }

    public void setSkillId(Skill skillId) {
        this.skillId = skillId;
    }*/

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
                ", employee=" + employee +
                //", skillId=" + skillId +
                ", level=" + level +
                '}';
    }
}
