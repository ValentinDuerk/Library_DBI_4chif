package at.htlleonding.logic;

import java.time.LocalDate;

public class AuthorDTO {
    private Integer id;

    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private LocalDate dateDeath;

    public AuthorDTO(String firstName, String lastName, LocalDate dateBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
    }

    public AuthorDTO(String firstName, String lastName, LocalDate dateBirth, LocalDate dateDeath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.dateDeath = dateDeath;
    }

    public AuthorDTO() {
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

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public LocalDate getDateDeath() {
        return dateDeath;
    }

    public void setDateDeath(LocalDate dateDeath) {
        this.dateDeath = dateDeath;
    }
}
