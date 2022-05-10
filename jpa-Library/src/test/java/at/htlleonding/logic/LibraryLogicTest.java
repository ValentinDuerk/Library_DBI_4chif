package at.htlleonding.logic;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
class LibraryLogicTest {

    @Inject
    LibraryLogic target;

//    private void createAuthorDTOs() {
//        AuthorDTO authorDTO1 = new AuthorDTO("Franz", "Franzes", LocalDate.of(1980, 01, 01));
//        AuthorDTO authorDTO2 = new AuthorDTO("Johann", "Johnson", LocalDate.of(1971, 3, 20));
//        AuthorDTO authorDTO3 = new AuthorDTO("Fritz", "Franzes", LocalDate.of(1971, 3, 20));
//
//        this.target.addAuthor(authorDTO1);
//        this.target.addAuthor(authorDTO2);
//        this.target.addAuthor(authorDTO3);
//    }
//
//    @TestTransaction
//    @Test
//    public void createAuthorDTOs_getAllAuthors_get3AuthorDTOs() {
//        createAuthorDTOs();
//
//        target.flushAndClear();
//
//        List<AuthorDTO> authorDTOs = target.getAllAuthors();
//        Assertions.assertNotNull(authorDTOs);
//        Assertions.assertEquals(3, authorDTOs.size());
//    }
//    @TestTransaction
//    @Test
//    public void createAuthorDTOs_getAuthor_getFranzFranzes() {
//        createAuthorDTOs();
//
//        target.flushAndClear();
//
//        AuthorDTO authorDTO = target.getAuthor("Franz", "Franzes");
//        Assertions.assertNotNull(authorDTO);
//        Assertions.assertEquals("Franz", authorDTO.getFirstName());
//        Assertions.assertEquals("Franzes", authorDTO.getLastName());
//    }

    /*
    Add rentable items to the library, of each media type, with multiple authors and attributes.
    Verify that these items can be rented.
    */
    @Test
    @TestTransaction
    public void addPaperBookWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addThreeCopiesOfPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addNewspaperWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addAudioBookWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addEBookWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addJournalWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }


    /*
     Add a library customer.
     Add a library employee.
     */
    @Test
    @TestTransaction
    public void addLibraryCustomer_isAvailable()
    {
        CustomerDTO customerDTO1 = new CustomerDTO("Clara", "Charlotte", "clara@hotmail.com", "660", false);
        target.addCustomer(customerDTO1);

        target.flushAndClear();

        CustomerDTO customerDTO2 = target.getCustomer("Clara", "Charlotte");

        Assertions.assertNotNull(customerDTO2);
        Assertions.assertEquals("Clara", customerDTO2.getFirstName());
        Assertions.assertEquals("Charlotte", customerDTO2.getLastName());
        Assertions.assertEquals("clara@hotmail.com", customerDTO2.getEmail());
        Assertions.assertEquals("660", customerDTO2.getTelNumber());
        Assertions.assertEquals(false, customerDTO2.isEmployee());
    }

    @Test
    @TestTransaction
    public void addLibraryEmployee_isAvailable()
    {
        EmployeeDTO employeeDTO1 = new EmployeeDTO("Rick", "Richard", "rick@hotmail.com", "660", 1800);
        target.addEmployee(employeeDTO1);

        target.flushAndClear();

        EmployeeDTO employeeDTO2 = target.getEmployee("Rick", "Richard");

        Assertions.assertNotNull(employeeDTO2);
        Assertions.assertEquals("Rick", employeeDTO2.getFirstName());
        Assertions.assertEquals("Richard", employeeDTO2.getLastName());
        Assertions.assertEquals("rick@hotmail.com", employeeDTO2.getEmail());
        Assertions.assertEquals("660", employeeDTO2.getTelNumber());
        Assertions.assertEquals(1800, employeeDTO2.getSalary());
    }

    /*
     Rent out, bring back, reserve and prolong.
     Verify state of rented items and customer's rent list.
    */
    @Test
    @TestTransaction
    public void customerRentsRentableItem_ItemIsRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsOneOfThreeCopiesOfRentableItem_TwoRentableItemsRemain_CustomerHasRent()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsThreeOfThreeCopiesOfRentableItem_TryRentAnother_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerOneItemOfEachMediaType_ItemsAreRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setItemForSale_customerTriesToRent_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setItemForOnDisplay_customerTriesToRent_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsSingleAvailableItem_RentNotPossible_BringsBackItem_RentPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void rentOutItemToCustomerA_customerBmakesReservation_CustomerAreturnsItem_RentPossibleOnlyForCustomerB()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentThreeTimes_customerCanOnlyProlongTwoTimes_rentalEndDate6weeksAhead()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentTwoTimes_EmployeeProlongsOneTime_rentalEndDate8weeksAhead()
    {
        Assertions.fail("Not implemented yet");
    }

    /*
      - Declare a library item to be for sale, it cannot be rented anymore.
      - Sell one library item to a customer, create invoice. Item cannot be rented anymore.
      - Sell some items of multiple books.
     */
    @Test
    @TestTransaction
    public void setItemForSale_cannotBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setOneOfTwoItemsForSale_onlyOneCanBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setThreeDifferentItemsForSale_CustomerBuys2_InvoiceHasTwoItems_OnlyOneItemForRent()
    {
        Assertions.fail("Not implemented yet");
    }
}