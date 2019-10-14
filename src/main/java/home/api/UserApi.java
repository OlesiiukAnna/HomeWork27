package home.api;

import home.dao.UserDao;
import home.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class UserApi {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAll() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<User> users = UserDao.getInstance().getAll();
        String json = gson.toJson(users);
        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addUser(@FormParam("name") String name, @FormParam("age") int age, @FormParam("city") String city) {
        UserDao.getInstance().addUser(new User(name, age, city));
        return Response
                .status(Response.Status.OK)
                .entity("User added")
                .build();
    }

}
