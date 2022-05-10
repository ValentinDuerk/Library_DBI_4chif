package at.htlleonding.logic;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SaleDTO {
    private LocalDate saleDate;

    private Set<SalePositionDTO> salePositionDTOs = new HashSet<>();

    private EmployeeDTO employeeDTO;

    private CustomerDTO customerDTO;

    public SaleDTO() {
    }

    public SaleDTO(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Set<SalePositionDTO> getSalePositionDTOs() {
        return salePositionDTOs;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
}
