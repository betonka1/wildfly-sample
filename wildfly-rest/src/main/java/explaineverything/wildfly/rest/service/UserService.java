package explaineverything.wildfly.rest.service;

import explaineverything.wildfly.ejb.UsersGroupEJB;
import explaineverything.wildfly.vo.UserVO;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
//@Lock(LockType.)
@Path("/user")
public class UserService {

    @Inject
    private UsersGroupEJB service;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserVO user) {
        return Response.ok(service.createUser(user)).build();
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(UserVO user) {
        return Response.ok(service.updateUser(user)).build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        return Response.ok(service.getUser(id)).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        service.deleteUser(id);
        return Response.ok().build();
    }

}