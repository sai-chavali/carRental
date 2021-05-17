package com.java.resources;
import com.java.auth.MyUser;
import com.java.core.DTO.BookingTimingsDTO;
import com.java.core.DTO.UserBookingsDTO;
import com.java.core.User;
import com.java.core.UserBooking;
import com.java.db.BookingsDAO;
import com.java.db.TokenDAO;
import com.java.db.UserDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.auth.JSONUnauthorizedHandler;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;


@Path("/api/users")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {
    UserDAO userDAO;
    TokenDAO tokenDAO;
    BookingsDAO bookingsDAO;

    public UserResource(UserDAO userDAO, TokenDAO tokenDAO, BookingsDAO bookingsDAO) {
        this.userDAO = userDAO;
        this.tokenDAO = tokenDAO;
        this.bookingsDAO = bookingsDAO;
    }

    @GET
    public List<User> getAll(@Auth MyUser myUser) {
        return userDAO.getAllUsers();
    }

    @POST
    @Path("/add")
    public String addUser(@Valid User user) {
        User userEmail = userDAO.findByEmail(user.getEmail());
        if (userEmail != null) {
            return "Email address already exists. Please try again";
        }
        user.setPassword(user.getHashedPassword());
        userDAO.insert(user);
        return "User Registered Successfully";
    }

    @POST
    @Path("/login")
    public Response loginUser(@Valid User user) {
        User userDetails = userDAO.findByEmail(user.getEmail());
        if(userDetails != null && BCrypt.checkpw(user.getPassword(),userDetails.getPassword())) {
            String token = tokenDAO.findOrCreateTokenforUser(userDetails.getId());
            return Response.status(Response.Status.OK).entity(userDetails).header("Authorization",token).build();
        }
        return new JSONUnauthorizedHandler().buildResponse("User with given email and password doesn't exist", "AUTH" );
    }

    @GET
    @Path("/mybookings")
    public UserBookingsDTO getUserBookings(@Auth MyUser myUser) {
        User user = userDAO.findById(myUser.getId());
        List<UserBooking> userBookings = bookingsDAO.getBookingsForUser(myUser.getId());
        return new UserBookingsDTO(user, userBookings);
    }

    @GET
    @Path("/me")
    public User getUser(@Auth MyUser myUser) {
        return userDAO.findById(myUser.getId());
    }

    @POST
    @Path("/logOut")
    public void logOut(@Auth MyUser myUser) {
        tokenDAO.deleteTokenForUser(myUser.getId());
    }

    @PUT
    @Path("/")
    public int UpdateUser(@Auth MyUser myUser, @Valid User user){
        return userDAO.update(user);
    }

    @POST
    @Path("/book/{id}")
    public int addBooking(@Auth MyUser myUser, @PathParam("id") Integer id, @Valid BookingTimingsDTO bookingTimingsDTO){
        int collisions = bookingsDAO.checkTimingsForCar(id, bookingTimingsDTO.getStart_time(), bookingTimingsDTO.getEnd_time());
        if(collisions == 0){
            //add pricing logic here
            bookingsDAO.addBooking(id,myUser.getId(),bookingTimingsDTO.getStart_time(),bookingTimingsDTO.getEnd_time(), new BigDecimal(500));
            return 1;
        }
        return 0;
    }

}
