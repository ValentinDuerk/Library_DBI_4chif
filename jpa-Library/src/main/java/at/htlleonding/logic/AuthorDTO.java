package at.htlleonding.logic;

import java.time.LocalDate;

public class AuthorDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;

    public AuthorDTO(String firstName, String lastName, LocalDate dateBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
    }

    public AuthorDTO() {
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

}
