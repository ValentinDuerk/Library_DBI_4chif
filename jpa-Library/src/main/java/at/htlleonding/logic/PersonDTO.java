package at.htlleonding.logic;

public class PersonDTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private String Email;

    private String telNumber;

    public PersonDTO(String firstName, String lastName, String eMail, String telNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Email = eMail;
        this.telNumber = telNumber;
    }

    public PersonDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
