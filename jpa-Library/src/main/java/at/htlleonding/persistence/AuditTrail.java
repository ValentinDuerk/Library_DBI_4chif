package at.htlleonding.persistence;
import javax.persistence.*;
import java.util.Date;

@Entity
public class AuditTrail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user;
    private Date date;
    private String table;
    private Action action;

}
