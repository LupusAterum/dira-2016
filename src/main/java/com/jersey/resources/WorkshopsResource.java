package com.jersey.resources;

import com.jersey.mappers.AppException;
import com.jersey.persistence.UserDAO;
import com.jersey.persistence.WorkshopDAO;
import com.jersey.representations.User;
import com.jersey.representations.Workshop;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lupus on 09.05.16.
 */
@Path("/workshops")
@Transactional
@Component
public class WorkshopsResource {
    private WorkshopDAO workshopDAO;
    private UserDAO userDAO;

    @Inject
    public WorkshopsResource(WorkshopDAO workshopDAO, UserDAO userDAO) {
        this.workshopDAO = workshopDAO;
        this.userDAO = userDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Workshop> getAll() {
        List<Workshop> workshops = this.workshopDAO.findAll();
        return workshops;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Workshop getOne(@PathParam("id") long id) throws AppException {
        Workshop workshop = workshopDAO.findOne(id);
        if(workshop == null) {
            throw new AppException(404, 998, "Workshop with id " + id + " does not exist", null, null);
        }
        else {
            return workshop;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Workshop create(@Valid Workshop w) throws AppException {
        if(w.getId() != null) {
            if(workshopDAO.findOne(w.getId()) != null) {
                throw new AppException(409, 998, "Workshop with id " + w.getId() + " already exists", null, null);
            }
        }
        return workshopDAO.save(w);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Workshop update(@PathParam("id") long id, @Valid Workshop w) throws AppException {
        if(workshopDAO.findOne(id) == null) {
            throw new AppException(404, 998, "Workshop with id " + id + " does not exist", null, null);
        } else {
            w.setId(id);
            return workshopDAO.save(w);
        }
    }

    @DELETE
    @Path("/{id}")
    public ResponseEntity<String> delete(@PathParam("id") long id) throws AppException {
        Workshop w = workshopDAO.findOne(id);
        if (w== null) {
            throw new AppException(404, 998, "Workshop with id " + id + " does not exist", null, null);
        }
        else {
            workshopDAO.delete(w);
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        }
    }

    @GET
    @Path("/{id}/registeredUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getRegisteredUsers(@PathParam("id") long id) throws AppException {
        Workshop w = workshopDAO.findOne(id);
        List <User> toReturn = new ArrayList<User>();
        if (w == null) {
            throw new AppException(404, 998, "Workshop with id " + id + " does not exist", null, null);
        }
        else {
            List<User> list = userDAO.findAll();
            for (User u : list) {
                if(u.getWorkshopsSignedFor().contains(w)) {
                    toReturn.add(u);
                }
            }
            return toReturn;
        }
    }
    @POST
    @Path("/{id}/registeredUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addWorkshopToUser(@PathParam("id") long id, @QueryParam("userId") long userId) throws AppException {
        User u = userDAO.findOne(userId);
        if(u == null)throw new AppException(404, 999, "User with id " + u.getId() + " does not exist", null, null);
        Workshop w = workshopDAO.findOne(id);
        if (w == null) throw new AppException(404, 998, "Workshop with id " + w.getId() + " does not exist", null, null);
        u.setId(userId);
        if(u.getWorkshopsSignedFor().contains(w)) throw new AppException(409, 999, "User with id " + u.getId() + "is already registered", null, null);
        u.getWorkshopsSignedFor().add(w);
        userDAO.save(u);
        return true;
    }

    @DELETE
    @Path("/{id}/registeredUsers")
    public void deleteWorkshopFromUser(@PathParam("id") long id, @QueryParam("userId") long userId) throws AppException {
        User u = userDAO.findOne(userId);
        if(u == null) throw new AppException(404, 999, "User with id " + u.getId() + " does not exist", null, null);
        Workshop w = workshopDAO.findOne(id);
        if (w == null) throw new AppException(404, 998, "Workshop with id " + w.getId() + " does not exist", null, null);
        u.setId(userId);
        u.getWorkshopsSignedFor().remove(w);
        userDAO.save(u);
    }

}
