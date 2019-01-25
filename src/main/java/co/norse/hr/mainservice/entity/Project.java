package co.norse.hr.mainservice.entity;

import javax.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "employee_project2", joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "employee_id",
//            referencedColumnName = "id"))
//    private Set<Employee> employees;




    public Project() {
    }

    @Override
    public String toString() {
        return "Project[" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
