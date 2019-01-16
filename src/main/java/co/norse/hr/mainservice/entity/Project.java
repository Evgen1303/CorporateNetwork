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
