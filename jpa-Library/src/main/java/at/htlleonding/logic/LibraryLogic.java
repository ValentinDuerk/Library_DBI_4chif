package at.htlleonding.logic;

import at.htlleonding.persistence.*;
import at.htlleonding.persistence.People.Customer;
import at.htlleonding.persistence.People.Employee;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
            authorDTO.setId(author.getId());
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

    @Transactional
    public AuthorDTO getAuthor(int authorId) {
        AuthorDTO authorDTO = new AuthorDTO();
        Author author = libraryRepository.getEntity(Author.class, authorId);

        if(author != null)
            mapper.map(author, authorDTO);
        else
            authorDTO = null;

        return authorDTO;
    }


    @Transactional
    public void addGenre(GenreDTO genreDTO) {
        Genre genre = libraryRepository.getGenre(genreDTO.getKeyword());
        if(genre == null) {
            genre = new Genre();
            mapper.map(genreDTO, genre);
            libraryRepository.add(genre);
            genreDTO.setId(genre.getId());
        }
    }

    @Transactional
    public GenreDTO getGenre(int genreId) {
        GenreDTO genreDTO = new GenreDTO();
        Genre genre = libraryRepository.getEntity(Genre.class, genreId);

        if(genre != null)
            mapper.map(genre, genreDTO);
        else
            genreDTO = null;

        return genreDTO;
    }

    @Transactional
    public void addTopic(TopicDTO topicDTO) {
        Topic topic = libraryRepository.getTopic(topicDTO.getKeyword());
        if(topic == null) {
            topic = new Topic();
            mapper.map(topicDTO,topic);
            libraryRepository.add(topic);
            topicDTO.setId(topic.getId());
        }
    }

    @Transactional
    public TopicDTO getTopic(int topicId) {
        TopicDTO topicDTO = new TopicDTO();
        Topic topic = libraryRepository.getEntity(Topic.class, topicId);

        if(topic != null)
            mapper.map(topic, topicDTO);
        else
            topicDTO = null;

        return topicDTO;
    }

    @Transactional
    public void addPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = libraryRepository.getPublisher(publisherDTO.getName());
        if(publisher == null) {
            publisher = new Publisher();
            mapper.map(publisherDTO,publisher);
            libraryRepository.add(publisher);
            publisherDTO.setId(publisher.getId());
        }
    }

    @Transactional
    public PublisherDTO getPublisher(int publisherId) {
        PublisherDTO publisherDTO = new PublisherDTO();
        Publisher publisher = libraryRepository.getEntity(Publisher.class, publisherId);

        if(publisher != null)
            mapper.map(publisher, publisherDTO);
        else
            publisherDTO = null;

        return publisherDTO;
    }

    @Transactional
    public void addLanguage(LanguageDTO languageDTO) {
        Language language = libraryRepository.getLanguage(languageDTO.getKeyword());
        if(language == null) {
            language = new Language();
            mapper.map(languageDTO,language);
            libraryRepository.add(language);
            languageDTO.setId(language.getId());
        }
    }

    @Transactional
    public LanguageDTO getLanguage(int languageId) {
        LanguageDTO languageDTO = new LanguageDTO();
        Language language = libraryRepository.getEntity(Language.class, languageId);

        if(language != null)
            mapper.map(language, languageDTO);
        else
            languageDTO = null;

        return languageDTO;
    }

    @Transactional
    public void addMedia(MediaDTO mediaDTO) {
        GenreDTO genreDTO = mediaDTO.getGenreDTO();
        Genre genre;
        if(genreDTO != null) {
            addGenre(genreDTO);
            genre = libraryRepository.getEntity(Genre.class, genreDTO.getId());
//            genre = libraryRepository.getGenre(genreDTO.getKeyword());
        }
        else {
            genre = new Genre();
        }

        TopicDTO[] topicDTOs = mediaDTO.getTopicDTOs().toArray(new TopicDTO[0]);
        Topic[] topics = new Topic[topicDTOs.length];
        if(topicDTOs.length > 0) {
            for (int i = 0; i < topicDTOs.length;i++) {
                addTopic(topicDTOs[i]);
                topics[i] = libraryRepository.getEntity(Topic.class, topicDTOs[i].getId());
//                topics[i] = libraryRepository.getTopic(topicDTOs[i].getKeyword());
            }
        }

        AuthorDTO[] authorDTOs = mediaDTO.getAuthorDTOs().toArray(new AuthorDTO[0]);
        Author[] authors = new Author[authorDTOs.length];
        if(authorDTOs.length > 0) {
            for (int i = 0; i < authorDTOs.length;i++) {
                addAuthor(authorDTOs[i]);
                authors[i] = libraryRepository.getEntity(Author.class, authorDTOs[i].getId());
//                authors[i] = libraryRepository.getAuthorByFirstNameAndLastName(authorDTOs[i].getFirstName(), authorDTOs[i].getLastName());
            }
        }

        List<String> topicKeywords = new ArrayList<>();
        for (var topic: topics) {
            topicKeywords.add(topic.getKeyword());
        }

        List<String> authorLastNames = new ArrayList<>();
        for (var author : authors) {
            authorLastNames.add(author.getLastName());
        }

        Media media = libraryRepository.getMediaByGenreTopicsAuthorsAndPublicationDate(genre.getKeyword(),
                topicKeywords, authorLastNames, mediaDTO.getPublicationDate());

        if(media == null) {
            media = new Media(mediaDTO.getPublicationDate());

            libraryRepository.add(media);
            mediaDTO.setId(media.getId());

            if(genre.getId() != 0)
                libraryRepository.add(media, genre);

            for (var topic : topics) {
                libraryRepository.add(media, topic);
            }

            for (var author : authors) {
                libraryRepository.add(media, author);
            }
        }
    }

    @Transactional
    public MediaDTO getMedia(int mediaId) {
        MediaDTO mediaDTO;
        Media media = libraryRepository.getEntity(Media.class, mediaId);

        if(media != null) {
            mediaDTO = new MediaDTO(media.getPublicationDate());
            mediaDTO.setId(media.getId());

            if(media.getAuthors().size() > 0) {
                List<AuthorDTO> authorDTOs = new ArrayList<>();
                for (var author : media.getAuthors() ) {
                    authorDTOs.add(getAuthor(author.getId()));
                }
                mediaDTO.setAuthorDTOs(authorDTOs);
            }

            if(media.getTopics().size() > 0) {
                List<TopicDTO> topicDTOs = new ArrayList<>();
                for (var topic : media.getTopics() ) {
                    topicDTOs.add(getTopic(topic.getId()));
                }
                mediaDTO.setTopicDTOs(topicDTOs);
            }

            if(media.getGenre() != null)
                mediaDTO.setGenreDTO(getGenre(media.getGenre().getId()));
        }
        else
            mediaDTO = null;

        return mediaDTO;
    }

    @Transactional
    public void addPublication(PublicationDTO publicationDTO) {
        MediaDTO mediaDTO = publicationDTO.getMediaDTO();
        Media media;
        if(mediaDTO != null) {
            addMedia(mediaDTO);
            media = libraryRepository.getEntity(Media.class, mediaDTO.getId());
//        media = libraryRepository.getMediaByGenreTopicsAuthorsAndPublicationDate(mediaDTO.getGenre(),
//                    mediaDTO.getTopics(), mediaDTO.getAuthorLastNames(),
//                    mediaDTO.getPublicationDate());
        }
        else {
            media = new Media();
        }

        PublisherDTO publisherDTO = publicationDTO.getPublisherDTO();
        Publisher publisher;
        if(publisherDTO != null) {
            addPublisher(publisherDTO);
            publisher = libraryRepository.getEntity(Publisher.class, publisherDTO.getId());
//            publisher = libraryRepository.getPublisher(publisherDTO.getName());
        }
        else {
            publisher = new Publisher();
        }

        LanguageDTO languageDTO = publicationDTO.getLanguageDTO();
        Language language;
        if(languageDTO != null) {
            addLanguage(languageDTO);
            language = libraryRepository.getEntity(Language.class, languageDTO.getId());
//            language = libraryRepository.getLanguage(languageDTO.getKeyword());
        }
        else {
            language = new Language();
        }

        Publication publication = libraryRepository.getPublication(publicationDTO.getTitle(), publisher.getName(), language.getKeyword());

        if(publication == null) {
            publication = new Publication(publicationDTO.getTitle(), publicationDTO.isTranslation());

            libraryRepository.add(publication);
            publicationDTO.setId(publication.getId());

            if(media.getId() != 0)
                libraryRepository.add(publication, media);

            if(publisher.getId() != 0)
                libraryRepository.add(publication, publisher);

            if(language.getId() != 0)
                libraryRepository.add(publication, language);
        }
    }

    @Transactional
    public PublicationDTO getPublication(int publicationId) {
        PublicationDTO publicationDTO;
        Publication publication = libraryRepository.getEntity(Publication.class, publicationId);

        if(publication != null) {
            publicationDTO = new PublicationDTO(publication.getTitle(), publication.isTranslation());
            publicationDTO.setId(publication.getId());

            if(publication.getMedia() != null)
                publicationDTO.setMediaDTO(getMedia(publication.getMedia().getId()));

            if(publication.getPublisher() != null)
                publicationDTO.setPublisherDTO(getPublisher(publication.getPublisher().getId()));

            if(publication.getLanguage() != null)
                publicationDTO.setLanguageDTO(getLanguage(publication.getLanguage().getId()));
        }
        else
            publicationDTO = null;

        return publicationDTO;
    }

    @Transactional
    public void addSpecimen(SpecimenDTO specimenDTO) {
        PublicationDTO publicationDTO = specimenDTO.getPublicationDTO();
        Publication publication = null;
        if(publicationDTO != null) {
            addPublication(publicationDTO);
            publication = libraryRepository.getEntity(Publication.class, publicationDTO.getId());
//            publication = libraryRepository.getPublication(publicationDTO.getTitle(),
//                    publicationDTO.getPublisher(),
//                    publicationDTO.getLanguage());
        }

        if(publication != null) {
            Specimen specimen = new Specimen(specimenDTO.getPurchaseDate(), specimenDTO.getSpecimenState());

            libraryRepository.add(specimen, publication);

            specimenDTO.setId(specimen.getId());
        }
    }

    @Transactional
    public SpecimenDTO getSpecimen(int specimenId) {
        SpecimenDTO specimenDTO;
        Specimen specimen = libraryRepository.getEntity(Specimen.class, specimenId);

        if(specimen != null) {
            specimenDTO = new SpecimenDTO(specimen.getSpecimenState(), specimen.getPurchaseDate());
            specimenDTO.setId(specimen.getId());

            if(specimen.getPublication() != null)
                specimenDTO.setPublicationDTO(getPublication(specimen.getPublication().getId()));
        }
        else
            specimenDTO = null;

        return specimenDTO;
    }

    @Transactional
    public List<SpecimenDTO> getSpecimensByPublication(String title, String publisher, String language) {
        List<SpecimenDTO> specimenDTOs = new ArrayList<>();

        Publication publication = libraryRepository.getPublication(title, publisher, language);

        if(publication != null) {
            List<Specimen> specimens = libraryRepository.getSpecimensByPublication(publication.getId());

            if(specimens.size() > 0) {
                for (var specimen : specimens) {
                    SpecimenDTO specimenDTO = new SpecimenDTO(specimen.getSpecimenState(), specimen.getPurchaseDate());
                    specimenDTO.setId(specimen.getId());

                    if(specimen.getPublication() != null)
                        specimenDTO.setPublicationDTO(getPublication(specimen.getPublication().getId()));

                    specimenDTOs.add(specimenDTO);
                }
            }
        }
        return specimenDTOs;
    }

    @Transactional
    public SpecimenState setSpecimenState(int specimenId, SpecimenState specimenState) {
        Specimen specimen = libraryRepository.getSpecimen(specimenId);
        SpecimenState result = null;

        if(specimen != null) {
            if(Math.abs(ChronoUnit.YEARS.between(specimen.getPurchaseDate(), LocalDate.now())) >= 7){
                specimen.setSpecimenState(SpecimenState.SoldStock);
                result = SpecimenState.SoldStock;
            } else {
                specimen.setSpecimenState(specimenState);
                result = specimenState;
            }
            libraryRepository.add(specimen);
        }

        return result;
    }


    @Transactional
    public void addLendOut(LendOutDTO lendOutDTO) {
        SpecimenDTO specimenDTO = lendOutDTO.getSpecimenDTO();
        Specimen specimen;
        if(specimenDTO != null) {
            specimen = libraryRepository.getSpecimen(specimenDTO.getId());
        } else {
            specimen = new Specimen();
        }

        CustomerDTO customerDTO = lendOutDTO.getCustomerDTO();
        Customer customer;
        if(customerDTO != null) {
            addCustomer(customerDTO);
            customer = libraryRepository.getEntity(Customer.class, customerDTO.getId());
//            customer = libraryRepository.getCustomerByFirstNameAndLastName(customerDTO.getFirstName(), customerDTO.getLastName());
        }
        else {
            customer = new Customer();
        }

        if(specimen.getId() != 0 && specimen.getSpecimenState() == SpecimenState.MagazineStock && customer.getId() != 0) {
            LendOut lendOut = libraryRepository.getLendOutBySpecimenCustomerAndLendOutDate(specimen.getId(), customer.getId(), lendOutDTO.getLendOutDate());

            if(lendOut == null) {
                lendOut = new LendOut(lendOutDTO.getLendOutDate(), lendOutDTO.getReturnDate());
                lendOut.setExtensions(1);

                libraryRepository.add(lendOut);
                lendOutDTO.setId(lendOut.getId());

                libraryRepository.add(specimen, lendOut);
                libraryRepository.add(lendOut, customer);
            } else if(lendOut != null) {
                var ext = lendOut.getExtensions();

                if(ext < 3) {
                    lendOut.setReturnDate(lendOutDTO.getReturnDate());
                    lendOut.setExtensions(ext + 1);
                    libraryRepository.add(lendOut);
                }
            }
        }
    }

    @Transactional
    public LendOutDTO getLendOut(int specimenId, String customerFirstName, String customerLastName, LocalDate lendOutDate) {
        LendOutDTO lendOutDTO = null;

        Customer customer = libraryRepository.getCustomerByFirstNameAndLastName(customerFirstName, customerLastName);

        if(customer != null) {
            LendOut lendOut = libraryRepository.getLendOutBySpecimenCustomerAndLendOutDate(specimenId, customer.getId(), lendOutDate);

            if(lendOut != null) {
                lendOutDTO = new LendOutDTO();
                mapper.map(lendOut, lendOutDTO);
                lendOutDTO.setCustomerDTO(getCustomer(customer.getFirstName(), customer.getLastName()));
            }
        }

        return lendOutDTO;
    }


    @Transactional
    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer = libraryRepository.getCustomerByFirstNameAndLastName(customerDTO.getFirstName(), customerDTO.getLastName());
        if(customer == null) {
            customer = new Customer();
            mapper.map(customerDTO, customer);
            libraryRepository.add(customer);
            customerDTO.setId(customer.getId());
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
    public CustomerDTO getCustomer(int customerId) {
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = libraryRepository.getEntity(Customer.class, customerId);

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
            employeeDTO.setId(employee.getId());
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

    @Transactional
    public EmployeeDTO getEmployee(int employeeId) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = libraryRepository.getEntity(Employee.class, employeeId);

        if(employee != null)
            mapper.map(employee, employeeDTO);
        else
            employeeDTO = null;

        return employeeDTO;
    }
}
