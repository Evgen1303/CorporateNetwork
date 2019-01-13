package co.norse.hr.mainservice.entity;

import javax.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private  String name;
    private  String description;

    public Project(){

    }

    @Override
    public String toString(){
        return "Project[" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description  +
                ']';
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
        }
    public void setDescription(String description) {
        this.description = description;
        }
        }

