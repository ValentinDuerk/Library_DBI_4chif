package at.htlleonding.persistence;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class AuditTrail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String table;

    @Enumerated(EnumType.STRING)
    private Action action;

    @Column
    private Integer contentId = null;

    @Column
    private String oldValue = null;

    @Column(nullable = false)
    private String newValue;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public AuditTrail(String user, LocalDate date, String table, Action action, Integer contentId, String oldValue, String newValue) {
        this.user = user;
        this.date = date;
        this.table = table;
        this.action = action;
        this.contentId = contentId;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public AuditTrail() {
    }
}
