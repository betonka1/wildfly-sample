package explaineverything.wildfly.rest.service;

import explaineverything.wildfly.ejb.UsersGroupEJB;
import explaineverything.wildfly.vo.GroupVO;
import explaineverything.wildfly.vo.UserGroupRq;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
//@Lock(LockType.)
@Path("/group")
public class GroupService {

    @Inject
    private UsersGroupEJB service;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(GroupVO group) {
        return Response.ok(service.createGroup(group)).build();
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(GroupVO group) {
        return Response.ok(service.updateGroup(group)).build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        return Response.ok(service.getGroup(id)).build();
    }

    @POST
    @Path("/assignUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignUser(UserGroupRq rq) {
        return Response.ok(service.assignUserToGroup(rq)).build();
    }

    @POST
    @Path("/deleteUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(UserGroupRq rq) {
        return Response.ok(service.removeUser(rq)).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        service.deleteGroup(id);
        return Response.ok().build();
    }

}