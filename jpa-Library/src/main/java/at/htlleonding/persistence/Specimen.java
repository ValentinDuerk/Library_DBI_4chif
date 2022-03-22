package at.htlleonding.persistence;

import javax.persistence.*;

@Entity
public class Specimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String Date;
}
