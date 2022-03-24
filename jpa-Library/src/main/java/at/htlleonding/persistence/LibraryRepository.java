package at.htlleonding.persistence;
///home/peter/src/dbi4/quarkus-hibernate-cmdline

import at.htlleonding.persistence.MediaTypes.Book;
import at.htlleonding.persistence.People.Customer;
import at.htlleonding.persistence.People.Employee;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.MapsId;
import javax.transaction.Transactional;

// @Transactional
// https://quarkus.io/guides/transaction
// https://quarkus.io/guides/hibernate-orm
// Mark your CDI bean method as @Transactional and the EntityManager will enlist and flush at commit.
// Make sure to wrap methods modifying your database (e.g. entity.persist()) within a transaction.
// Marking a CDI bean method @Transactional will do that for you and make that method a transaction boundary.
// We recommend doing so at your application entry point boundaries like your REST endpoint controllers.

@ApplicationScoped
public class LibraryRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

//    @Transactional
//    public void add(Author a) {
//        entityManager.persist(a);
//    }
//
//    @Transactional
//    public void add(Topic t) {
//        entityManager.persist(t);
//    }
//
//    @Transactional
//    public void add(Genre g) {
//        entityManager.persist(g);
//    }
//
//    @Transactional
//    public void add(Language l) {
//        entityManager.persist(l);
//    }
//
//    @Transactional
//    public void add(Publisher p) {
//        entityManager.persist(p);
//    }
//
//    @Transactional
//    public void add(Publication p) {
//        entityManager.persist(p);
//    }
//
//    @Transactional
//    public void add(Media m) {
//        entityManager.persist(m);
//    }

    @Transactional
    public <E> void add(E e) {entityManager.persist(e);}
    @Transactional
    public <E> void remove(E e) {entityManager.remove(e);}


    @Transactional
    public void add(Media m, Genre g) {
        if(m.getId() == null) {
            add(m);
        }
        if(g.getId() == null) {
            add(g);
        }

        m.setGenre(g);
        g.getMedia().add(m);

        entityManager.persist(m);
        entityManager.persist(g);
    }

    @Transactional
    public void add(Media m, Topic t) {
        if(m.getId() == null) {
            add(m);
        }
        if(t.getId() == null) {
            add(t);
        }

        m.getTopics().add(t);
        t.getMedia().add(m);

        entityManager.persist(m);
        entityManager.persist(t);
    }

    @Transactional
    public void add(Media m, Author a) {
        if(m.getId() == null) {
            add(m);
        }
        if(a.getId() == null) {
            add(a);
        }

        m.getAuthors().add(a);
        a.getMedia().add(m);

        entityManager.persist(m);
        entityManager.persist(a);
    }

    @Transactional
    public void add(Publication p, Media m) {
        if(p.getId() == null) {
            add(p);
        }
        if(m.getId() == null) {
            add(m);
        }

        p.setMedia(m);
        m.getPublications().add(p);

        entityManager.persist(p);
        entityManager.persist(m);
    }

    @Transactional
    public void add(Publication pc, Publisher ps) {
        if(pc.getId() == null) {
            add(pc);
        }
        if(ps.getId() == null) {
            add(ps);
        }

        pc.setPublisher(ps);
        ps.getPublications().add(pc);

        entityManager.persist(pc);
        entityManager.persist(ps);
    }

    @Transactional
    public void add(Publication p, Language l) {
        if(p.getId() == null) {
            add(p);
        }
        if(l.getId() == null) {
            add(l);
        }

        p.setLanguage(l);
        l.getPublications().add(p);

        entityManager.persist(p);
        entityManager.persist(l);
    }

    @Transactional
    public void add(Room r, BookShelf b) {
        if(r.getId() == null) {
            add(r);
        }
        if(b.getId() == null) {
            add(b);
        }

        b.setRoom(r);
        r.getBookShelves().add(b);

        entityManager.persist(r);
        entityManager.persist(b);
    }

    @Transactional
    public void add(Specimen s, BookShelf b) {
        if(s.getId() == null) {
            add(s);
        }
        if(b.getId() == null) {
            add(b);
        }

        s.setBookShelf(b);
        b.getSpecimen().add(s);

        entityManager.persist(s);
        entityManager.persist(b);
    }

    @Transactional
    public void add(Specimen s, Publication p) {
        if(s.getId() == null) {
            add(s);
        }
        if(p.getId() == null) {
            add(p);
        }

        s.setPublication(p);
        p.getSpecimen().add(s);

        entityManager.persist(s);
        entityManager.persist(p);
    }

    @Transactional
    public void add(Publication p, Reservation r) {
        if(p.getId() == null) {
            add(p);
        }
        if(r.getId() == null) {
            add(r);
        }

        p.getReservations().add(r);
        r.setPublication(p);

        entityManager.persist(p);
        entityManager.persist(r);
    }

    @Transactional
    public void add(Reservation r, Customer c) {
        if(r.getId() == null) {
            add(r);
        }
        if(c.getId() == null) {
            add(c);
        }

        r.setCustomer(c);
        c.getReservations().add(r);

        entityManager.persist(r);
        entityManager.persist(c);
    }

    @Transactional
    public void add(Specimen s, LendOut l) {
        if(s.getId() == null) {
            add(s);
        }
        if(l.getId() == null) {
            add(l);
        }

        s.setLendOut(l);
        l.setSpecimen(s);

        entityManager.persist(s);
        entityManager.persist(l);
    }

    @Transactional
    public void add(LendOut l, Customer c) {
        if(l.getId() == null) {
            add(l);
        }
        if(c.getId() == null) {
            add(c);
        }

        l.setCustomer(c);
        c.getLendOuts().add(l);

        entityManager.persist(l);
        entityManager.persist(c);
    }

    @Transactional
    public void add(Sale s, Customer c) {
        if(s.getId() == null) {
            add(s);
        }
        if(c.getId() == null) {
            add(c);
        }

        s.setCustomer(c);
        c.getSales().add(s);

        entityManager.persist(s);
        entityManager.persist(c);
    }

    @Transactional
    public void add(Sale s, Employee e) {
        if(s.getId() == null) {
            add(s);
        }
        if(e.getId() == null) {
            add(e);
        }

        s.setEmployee(e);
        e.getSales().add(s);

        entityManager.persist(s);
        entityManager.persist(e);
    }

    @Transactional
    public void add(Sale s, SalePosition sp) {
        if(s.getId() == null) {
            add(s);
        }
        if(sp.getId() == null) {
            add(sp);
        }

        s.getSalePositions().add(sp);
        sp.setSale(s);

        entityManager.persist(s);
        entityManager.persist(sp);
    }

    @Transactional
    public void add(Specimen s, SalePosition sp) {
        if(s.getId() == null) {
            add(s);
        }
        if(sp.getId() == null) {
            add(sp);
        }

        s.setSalePosition(sp);
        sp.setSpecimen(s);

        entityManager.persist(s);
        entityManager.persist(sp);
    }

    @Transactional
    public void add(Person p, Location l) {
        if(p.getId() == null) {
            add(p);
        }
        if(l.getId() == null) {
            add(l);
        }

        p.setLocation(l);
        l.getPersons().add(p);

        entityManager.persist(p);
        entityManager.persist(l);
    }

    @Transactional
    public List<Media> getAllMedia() {
        return
                entityManager
                        .createQuery("select m from Media m", Media.class)
                        .getResultList();
    }

    @Transactional
    public List<Topic> getAllTopics() {
        return
                entityManager
                        .createQuery("select t from Topic t", Topic.class)
                        .getResultList();
    }

    @Transactional
    public List<Genre> getAllGenres() {
        return
                entityManager
                        .createQuery("select g from Genre g", Genre.class)
                        .getResultList();
    }

    @Transactional
    public List<Author> getAllAuthors() {
        return
                entityManager
                        .createQuery("select a from Author a", Author.class)
                        .getResultList();
    }

    @Transactional
    public List<Publication> getAllPublications() {
        return
                entityManager
                        .createQuery("select p from Publication p", Publication.class)
                        .getResultList();
    }

    @Transactional
    public List<Publisher> getAllPublisher() {
        return
                entityManager
                        .createQuery("select p from Publisher p", Publisher.class)
                        .getResultList();
    }

    @Transactional
    public List<Language> getAllLanguages() {
        return
                entityManager
                        .createQuery("select l from Language l", Language.class)
                        .getResultList();
    }

    @Transactional
    public List<Room> getAllRooms() {
        return
                entityManager
                        .createQuery("select e from Room e", Room.class)
                        .getResultList();
    }

    @Transactional
    public List<BookShelf> getAllBookShelves() {
        return
                entityManager
                        .createQuery("select b from BookShelf b", BookShelf.class)
                        .getResultList();
    }

    @Transactional
    public List<Specimen> getAllSpecimen() {
        return
                entityManager
                        .createQuery("select s from Specimen s", Specimen.class)
                        .getResultList();
    }

    @Transactional
    public List<AuditTrail> getAllAuditTrails() {
        return
                entityManager
                        .createQuery("select a from AuditTrail a", AuditTrail.class)
                        .getResultList();
    }

    @Transactional
    public List<Location> getAllLocations() {
        return
                entityManager
                        .createQuery("select l from Location l", Location.class)
                        .getResultList();
    }

    @Transactional
    public List<Customer> getAllCustomer() {
        return
                entityManager
                        .createQuery("select c from Customer c", Customer.class)
                        .getResultList();
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        return
                entityManager
                        .createQuery("select e from Employee e", Employee.class)
                        .getResultList();
    }

    @Transactional
    public List<Reservation> getAllReservations() {
        return
                entityManager
                        .createQuery("select r from Reservation r", Reservation.class)
                        .getResultList();
    }

    @Transactional
    public List<LendOut> getAllLendOuts() {
        return
                entityManager
                        .createQuery("select l from LendOut l", LendOut.class)
                        .getResultList();
    }

    @Transactional
    public List<Sale> getAllSales() {
        return
                entityManager
                        .createQuery("select s from Sale s", Sale.class)
                        .getResultList();
    }

    @Transactional
    public List<SalePosition> getAllSalePositions() {
        return
                entityManager
                        .createQuery("select s from SalePosition s", SalePosition.class)
                        .getResultList();
    }

    @Transactional
    public List<MediaType> getAllMediaTypes() {
        return
                entityManager
                        .createQuery("select m from MediaType m", MediaType.class)
                        .getResultList();
    }


    @Transactional
    public Author getAuthorByLastName(String lastName) {
        try {
            return entityManager
                    .createQuery("select p from Author p where p.lastName = :name ", Author.class)
                    .setParameter("name", lastName)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    @Transactional
    public List<Media> getMediaByAuthorAndGenre(String authorLastName, String genre) {
        try {
            return entityManager
                    .createQuery("select m from Media m where m.author.lastName = ?1 and m.genre.keyword = ?2", Media.class)
                    .setParameter(1, authorLastName)
                    .setParameter(2, genre)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Publication getPublication(String title, String language) {
        try {
            return entityManager
                    .createQuery("select p from Publication p where p.title = ?1 and p.language.keyword = ?2", Publication.class)
                    .setParameter(1, title)
                    .setParameter(2, language)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Genre getGenre(String keyword) {
        try {
            return entityManager
                    .createQuery("select g from Genre g where g.keyword = :keyword", Genre.class)
                    .setParameter("keyword", keyword)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Topic getTopic(String keyword) {
        try {
            return entityManager
                    .createQuery("select t from Topic t where t.keyword = :keyword", Topic.class)
                    .setParameter("keyword", keyword)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Language getLanguage(String keyword) {
        try {
            return entityManager
                    .createQuery("select l from Language l where l.keyword = :keyword", Language.class)
                    .setParameter("keyword", keyword)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Publisher getPublisher(String name) {
        try {
            return entityManager
                    .createQuery("select p from Publisher p where p.name = ?1", Publisher.class)
                    .setParameter(1, name)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Room getRoom(int number, int floor) {
        try {
            return entityManager
                    .createQuery("select r from Room r where r.number = ?1 and r.floor = ?2", Room.class)
                    .setParameter(1, number)
                    .setParameter(2, floor)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<BookShelf> getBookShelvesFromRoom(int roomId) {
        try {
            return entityManager
                    .createQuery("select b from BookShelf b where b.room.id = ?1", BookShelf.class)
                    .setParameter(1, roomId)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Specimen> getSpecimensByPublication(int publicationId) {
        try {
            return entityManager
                    .createQuery("select s from Specimen s where s.publication.id = ?1", Specimen.class)
                    .setParameter(1, publicationId)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<AuditTrail> getAuditTrailsByUser(String user) {
        try {
            return entityManager
                    .createQuery("select a from AuditTrail a where s.user = ?1", AuditTrail.class)
                    .setParameter(1, user)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Location getLocationByName(String name) {
        try {
            return entityManager
                    .createQuery("select l from Location l where l.name = ?1", Location.class)
                    .setParameter(1, name)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Customer getCustomerByFirstNameAndLastName(String firstName, String lastName) {
        try {
            return entityManager
                    .createQuery("select c from Customer c where c.firstName = ?1 and c.lastName = ?2", Customer.class)
                    .setParameter(1, firstName)
                    .setParameter(2, lastName)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Employee getEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        try {
            return entityManager
                    .createQuery("select e from Employee e where e.firstName = ?1 and e.lastName = ?2", Employee.class)
                    .setParameter(1, firstName)
                    .setParameter(2, lastName)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Reservation> getReservationsOfPublicationByCustomer(int publicationId, String customerId) {
        try {
            return entityManager
                    .createQuery("select r from Reservation r where r.publication.id = ?1 and e.customer.id = ?2", Reservation.class)
                    .setParameter(1, publicationId)
                    .setParameter(2, customerId)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<LendOut> getLendOutsByCustomer(String customerId) {
        try {
            return entityManager
                    .createQuery("select l from LendOut l where l.customer.id = ?1", LendOut.class)
                    .setParameter(1, customerId)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Sale> getSalesByEmployee(String employeeId) {
        try {
            return entityManager
                    .createQuery("select s from Sale s where s.employee.id = ?1", Sale.class)
                    .setParameter(1, employeeId)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Sale> getSalesByCustomer(String customerId) {
        try {
            return entityManager
                    .createQuery("select s from Sale s where s.customer.id = ?1", Sale.class)
                    .setParameter(1, customerId)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<SalePosition> getSalePositionsBySale(String saleId) {
        try {
            return entityManager
                    .createQuery("select s from SalePosition s where s.sale.id = ?1", SalePosition.class)
                    .setParameter(1, saleId)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<MediaType> getMediaTypeByName(String mediaType) {
        try {
            return entityManager
                    .createQuery("select m from MediaType m where m.designation = ?1", MediaType.class)
                    .setParameter(1, mediaType)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
