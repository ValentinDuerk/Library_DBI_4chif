package at.htlleonding.logic;

import java.time.LocalDate;

public class LendOutDTO {
    private Integer id;

    private LocalDate lendOutDate;

    private LocalDate returnDate;

    private Integer extensions;

    private SpecimenDTO specimenDTO;

    private CustomerDTO customerDTO;

    public LendOutDTO() {
    }

    public LendOutDTO(LocalDate lendOutDate, LocalDate returnDate) {
        this.lendOutDate = lendOutDate;
        this.returnDate = returnDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public String getCustomerLastName() {
        if(customerDTO != null)
            return customerDTO.getLastName();
        else
            return null;
    }

    public String getCustomerFirstName() {
        if(customerDTO != null)
            return customerDTO.getFirstName();
        else
            return null;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public SpecimenDTO getSpecimenDTO() {
        return specimenDTO;
    }

    public void setSpecimenDTO(SpecimenDTO specimenDTO) {
        this.specimenDTO = specimenDTO;
    }

    public LocalDate getLendOutDate() {
        return lendOutDate;
    }

    public void setLendOutDate(LocalDate lendOutDate) {
        this.lendOutDate = lendOutDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getExtensions() {
        return extensions;
    }

    public void setExtensions(Integer extensions) {
        this.extensions = extensions;
    }
}

