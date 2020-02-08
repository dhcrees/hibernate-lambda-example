package hibernate.lambda.example.rest.resources;

import hibernate.lambda.example.ConnectionBase;
import hibernate.lambda.example.model.Author;
import hibernate.lambda.example.model.Book;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
public class BooksResource extends ConnectionBase {

    private static final Logger LOGGER = Logger.getLogger(BooksResource.class);
    private EntityManager entityManager = getEntityManager();
    private static final String ERROR = "Exception Thrown";

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
        }

        return null;
    }

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
        }

        return null;
    }

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
        }

        return null;
    }

}
