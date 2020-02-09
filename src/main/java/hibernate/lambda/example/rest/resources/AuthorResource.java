package hibernate.lambda.example.rest.resources;

import hibernate.lambda.example.ConnectionBase;
import hibernate.lambda.example.model.Author;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
public class AuthorResource extends ConnectionBase {

    private static final Logger LOGGER = Logger.getLogger(AuthorResource.class);
    private EntityManager entityManager = getEntityManager();
    private static final String ERROR = "Exception Thrown";

    /**
     * Save a new Author and Book to the database
     * @param author Author
     * @return null
     */
    @POST
    @Path("/1.0/author")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveAuthors(Author author) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.getTransaction().commit();

            return Response.status(Response.Status.CREATED).entity("Author Saved").build();
        } catch (Exception e) {
            LOGGER.error(ERROR + e);
        } finally {
            entityManager.close();
        }

        return null;
    }

    /**
     * Returns a list of all authors and their books
     * @return List of Authors
     */
    @GET
    @Path("/1.0/author")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAuthors() {

        try {
            entityManager.getTransaction().begin();
            List<Author> authors = entityManager.createQuery("from Author", Author.class).getResultList();
            entityManager.getTransaction().commit();

            return Response.status(Response.Status.OK).entity(authors).build();
        } catch (Exception e) {
            LOGGER.error(ERROR + e);
        } finally {
            entityManager.close();
        }

        return null;
    }

    /**
     * Find an Author by name
     * @param name String representing the authors name
     * @return Author
     */
    @GET
    @Path("/1.0/author/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorByName(@PathParam("name") String name) {

        try {
            Author author = entityManager.createNamedQuery("Author.findByName", Author.class)
                    .setParameter("name", name)
                    .getSingleResult();

            return Response.status(Response.Status.OK).entity(author).build();
        } catch (Exception e) {
            LOGGER.error(ERROR + e);
        }  finally {
            entityManager.close();
        }

        return null;
    }
}
