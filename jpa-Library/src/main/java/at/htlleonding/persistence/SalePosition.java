package at.htlleonding.persistence;

import javax.persistence.*;

@Entity
public class SalePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer prize;
}
