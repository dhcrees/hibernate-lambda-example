package hibernate.lambda.example.rest.resources;

import hibernate.lambda.example.ConnectionBase;
import hibernate.lambda.example.model.Author;
import hibernate.lambda.example.model.Book;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
public class BooksResource extends ConnectionBase {

    private static final Logger LOGGER = Logger.getLogger(BooksResource.class);
    private EntityManager entityManager = getEntityManager();
    private EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
    private static final String ERROR = "Exception Thrown";

    /**
     * Save a book to an existing Author
     * @param book Book to save
     * @param id Id of the Author
     * @return Null
     */
    @PUT
    @Path("/1.0/book/add/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveAuthors(Book book, @PathParam("id") int id) {

        try {

            Author author = entityManager.find(Author.class, id);
            author.addBook(book);
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.getTransaction().commit();

            return Response.status(Response.Status.ACCEPTED).entity("Book Added").build();
        } catch (Exception e) {
            LOGGER.error(ERROR + e);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return null;
    }

    /**
     * Returns a list of all books
     * @return List of Books
     */
    @GET
    @Path("/1.0/book")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {

        try {
            List<Book> books = entityManager.createQuery("from Book", Book.class).getResultList();
            return Response.status(Response.Status.OK).entity(books).build();
        } catch (Exception e) {
            LOGGER.error(ERROR + e);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return null;
    }

    /**
     * Find a book by its ID
     * @param id Int of the Books ID
     * @return Book
     */
    @GET
    @Path("/1.0/book/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") int id) {

        try {
            Book book = entityManager.find(Book.class, id);

            return Response.status(Response.Status.OK).entity(book).build();
        } catch (Exception e) {
            LOGGER.error(ERROR + e);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return null;
    }

    /**
     * Lists all books by a given name
     * @param name book name
     * @return list of books
     */
    @GET
    @Path("/1.0/book/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookByName(@PathParam("name") String name) {

        try {
            List<Book> books = entityManager.createNamedQuery("Book.findByName", Book.class)
                    .setParameter("name", name)
                    .getResultList();

            return Response.status(Response.Status.OK).entity(books).build();
        } catch (Exception e) {
            LOGGER.error(ERROR + e);
        }  finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return null;
    }

}
