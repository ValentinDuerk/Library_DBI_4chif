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
    private Integer ContentId = null;

    @Column
    private String OldValue = null;

    @Column(nullable = false)
    private String NewValue;

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
        return ContentId;
    }

    public void setContentId(Integer contentId) {
        ContentId = contentId;
    }

    public String getOldValue() {
        return OldValue;
    }

    public void setOldValue(String oldValue) {
        OldValue = oldValue;
    }

    public String getNewValue() {
        return NewValue;
    }

    public void setNewValue(String newValue) {
        NewValue = newValue;
    }
}
