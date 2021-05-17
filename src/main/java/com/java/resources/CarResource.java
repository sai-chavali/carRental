package com.java.resources;

import com.java.auth.MyUser;
import com.java.core.CarBooking;
import com.java.core.Car;
import com.java.core.DTO.CarBookingsDTO;
import com.java.db.BookingsDAO;
import com.java.db.CarDAO;
import io.dropwizard.auth.Auth;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/catalog")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class CarResource {

    CarDAO carDAO;
    BookingsDAO bookingsDAO;

    public CarResource(CarDAO carDAO, BookingsDAO bookingsDAO){
        this.carDAO = carDAO;
        this.bookingsDAO = bookingsDAO;
    }

    @GET
    public List<Car> getAll(@Auth MyUser myUser){
        return carDAO.getAll();
    }

    @POST
    @Path("/addCar")
    public int addCar(@Auth MyUser myUser, @Valid Car car) {
        return carDAO.insert(car);
    }

    @GET
    @Path("/{id}")
    public CarBookingsDTO get(@Auth MyUser myUser, @PathParam("id") Integer id){
        List<CarBooking> carBookings = bookingsDAO.getBookingsForCar(id);
        Car car = carDAO.findById(id);
        return new CarBookingsDTO(car, carBookings);
    }

    @PUT
    @Path("/{id}")
    public Car update(@Auth MyUser myUser, @PathParam("id") Integer id, @Valid Car car) {
        carDAO.update(car);
        return car;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@Auth MyUser myUser, @PathParam("id") Integer id) {
        carDAO.deleteById(id);
    }
}
