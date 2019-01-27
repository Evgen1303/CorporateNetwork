package co.norse.hr.mainservice.dto;

public class OfficeDTO {

    private String name;
    private int city;

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

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }
}
