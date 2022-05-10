package at.htlleonding.logic;

import at.htlleonding.persistence.*;
import com.arjuna.ats.jta.exceptions.NotImplementedException;
import at.htlleonding.persistence.People.Customer;
import at.htlleonding.persistence.People.Employee;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class LibraryLogic {

    @Inject
    LibraryRepository libraryRepository;

    ModelMapper mapper = new ModelMapper();

    @Transactional
    public void flushAndClear() {
        libraryRepository.flushAndClear();
    }

    @Transactional
    public void addAuthor(AuthorDTO authorDTO) {
        Author author = libraryRepository.getAuthorByFirstNameAndLastName(authorDTO.getFirstName(), authorDTO.getLastName());
        if(author == null) {
            author = new Author();
            mapper.map(authorDTO, author);
            libraryRepository.add(author);
        }
    }

    @Transactional
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = libraryRepository.getAllAuthors();
        List<AuthorDTO> authorDTOs = new ArrayList<>();

        for (var author : authors) {
            AuthorDTO authorDTO = new AuthorDTO();
            mapper.map(author, authorDTO);
            authorDTOs.add(authorDTO);
        }
        return authorDTOs;
    }

    @Transactional
    public AuthorDTO getAuthor(String firstName, String lastName) {
        AuthorDTO authorDTO = new AuthorDTO();
        Author author = libraryRepository.getAuthorByFirstNameAndLastName(firstName, lastName);

        if(author != null)
            mapper.map(author, authorDTO);
        else
            authorDTO = null;

        return authorDTO;
    }

//    @Transactional
//    public void AddMedia(LocalDate publicationDate, GenreDTO genreDTO, TopicDTO[] topicDTOs, AuthorDTO[] authorDTOs) {
//        if(genreDTO == null || topicDTOs.length == 0 || authorDTOs.length == 0)
//            return;
//
//        addGenre(genreDTO);
//        Genre genre = libraryRepository.getGenre(genreDTO.getKeyword());
//
//        Topic[] topics = new Topic[topicDTOs.length];
//        for (int i = 0; i < topicDTOs.length;i++) {
//            addTopic(topicDTOs[i]);
//            topics[i] = libraryRepository.getTopic(topicDTOs[i].getKeyword());
//        }
//
//        Author[] authors = new Author[authorDTOs.length];
//        for (int i = 0; i < authorDTOs.length;i++) {
//            addAuthor(authorDTOs[i]);
//            authors[i] = libraryRepository.getAuthorByFirstNameAndLastName(authorDTOs[i].getFirstName(), authorDTOs[i].getLastName());
//        }
//
//        Media media = libraryRepository.getMediaByGenreTopicsAuthorsAndPublicationDate(genre.getId(),
//                Arrays.stream(topics).mapToInt(x -> x.getId()).toArray(),
//                Arrays.stream(authors).mapToInt(x -> x.getId()).toArray(), publicationDate);
//
//        if(media == null) {
//            media = new Media(publicationDate);
//
//            libraryRepository.add(media, genre);
//
//            for (var topic : topics) {
//                libraryRepository.add(media, topic);
//            }
//
//            for (var author : authors) {
//                libraryRepository.add(media, author);
//            }
//        }
//    }

    @Transactional
    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer = libraryRepository.getCustomerByFirstNameAndLastName(customerDTO.getFirstName(), customerDTO.getLastName());
        if(customer == null) {
            customer = new Customer();
            mapper.map(customerDTO, customer);
            libraryRepository.add(customer);
        }
    }

    @Transactional
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = libraryRepository.getAllCustomer();
        List<CustomerDTO> customerDTOs = new ArrayList<>();

        for (var customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            mapper.map(customer, customerDTO);
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    @Transactional
    public CustomerDTO getCustomer(String firstName, String lastName) {
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = libraryRepository.getCustomerByFirstNameAndLastName(firstName, lastName);

        if(customer != null)
            mapper.map(customer, customerDTO);
        else
            customerDTO = null;

        return customerDTO;
    }


    @Transactional
    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = libraryRepository.getEmployeeByFirstNameAndLastName(employeeDTO.getFirstName(), employeeDTO.getLastName());
        if(employee == null) {
            employee = new Employee();
            mapper.map(employeeDTO, employee);
            libraryRepository.add(employee);
        }
    }

    @Transactional
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = libraryRepository.getAllEmployees();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (var employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            mapper.map(employee, employeeDTO);
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    @Transactional
    public EmployeeDTO getEmployee(String firstName, String lastName) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = libraryRepository.getEmployeeByFirstNameAndLastName(firstName, lastName);

        if(employee != null)
            mapper.map(employee, employeeDTO);
        else
            employeeDTO = null;

        return employeeDTO;
    }

    private void addGenre(GenreDTO genreDTO) throws NotImplementedException {
        throw new NotImplementedException();
    }

    private void addTopic(TopicDTO topicDTO) throws NotImplementedException {
        throw new NotImplementedException();
    }

}
