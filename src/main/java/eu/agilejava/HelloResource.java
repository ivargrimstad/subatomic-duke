package eu.agilejava;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    @Inject
    @ConfigProperty(name = "key")
    private String prop;

    @GET
    public List<Greeting> all() {
        return Greeting.listAll();
    }

    @GET
    @Path("{id}")
    public Response findOne(@PathParam("id") Long id) {

        Greeting greeting = Greeting.findById(id);

        if( greeting == null ) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(greeting).build();
    }

    @POST
    @Transactional
    public Response create(Greeting greeting) {

        greeting.persist();

        return Response.created(UriBuilder.fromResource(HelloResource.class).path(greeting.getId() + "").build()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {

        Greeting.delete("id", id);
        return Response.noContent().build();
    }
}