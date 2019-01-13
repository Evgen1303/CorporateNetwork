package co.norse.hr.mainservice.entity;

import javax.persistence.*;

@Entity
public class Office {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
}
