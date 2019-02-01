package co.norse.hr.mainservice.dto;

public class OfficeDTO {

    private String name;
    private String city;

    @Override
    public String toString() {
        return "OfficeDTO{" +
                "name='" + name + '\'' +
                ", city=" + city +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
