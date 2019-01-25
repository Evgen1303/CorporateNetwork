package co.norse.hr.mainservice.dto;

public class ProjectDto {

    private Long id;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "Project[" +
                ", name=" + name +
                ", description=" + description +
                ']';
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
