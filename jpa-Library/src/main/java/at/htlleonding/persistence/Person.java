package at.htlleonding.persistence;

import javax.persistence.*;

@DiscriminatorColumn(name = "PERSON_TYPE")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String eMail;

    @Column(length = 20, nullable = false)
    private String telNumber;

    @ManyToOne
    @JoinColumn(name = "locationID")
    private Location location;

    public Person() {
    }

    public Person(String firstName, String lastName, String eMail, String telNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.telNumber = telNumber;
    }

    public Integer getId() {
        return id;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
