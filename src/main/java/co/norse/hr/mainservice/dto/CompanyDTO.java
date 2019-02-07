package co.norse.hr.mainservice.dto;

public class CompanyDTO {

    private String name;

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}