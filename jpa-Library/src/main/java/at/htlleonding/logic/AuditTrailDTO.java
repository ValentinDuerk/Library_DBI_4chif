package at.htlleonding.logic;

import at.htlleonding.persistence.Action;
import java.time.LocalDate;

public class AuditTrailDTO {
    private String user;

    private LocalDate date;

    private String table;

    private Action action;

    private Integer contentId;

    private String oldValue;

    private String newValue;

    public AuditTrailDTO(String user, LocalDate date, String table, Action action, Integer contentId, String oldValue, String newValue) {
        this.user = user;
        this.date = date;
        this.table = table;
        this.action = action;
        this.contentId = contentId;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public AuditTrailDTO() {
    }

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
}
