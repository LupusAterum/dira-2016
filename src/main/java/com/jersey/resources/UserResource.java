package com.jersey.resources;

import com.jersey.mappers.AppException;
import com.jersey.persistence.InvoiceDataDAO;
import com.jersey.persistence.TShirtInfoDAO;
import com.jersey.persistence.UserDAO;
import com.jersey.representations.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by lupus on 09.05.16.
 */
@Path("/users")
@Transactional
@Component
public class UserResource {
    private UserDAO userDAO;
    private TShirtInfoDAO tShirtInfoDAO;
    private InvoiceDataDAO invoiceDataDAO;

    @Inject
    public UserResource(UserDAO userDAO, TShirtInfoDAO tShirtInfoDAO, InvoiceDataDAO invoiceDataDAO) {
        this.userDAO = userDAO;
        this.tShirtInfoDAO = tShirtInfoDAO;
        this.invoiceDataDAO = invoiceDataDAO;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getOne(@PathParam("id") long id) throws AppException {
        User u = userDAO.findOne(id);
        if (u == null)
            throw new AppException(404, 990, "User with id " + id + " does not exist", null, null);
        return u;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(@Valid User u) throws AppException {
        if (u.getId() != null) {
            if (userDAO.findOne(u.getId()) != null) {
                throw new AppException(409, 999, "User with id " + u.getId() + " already exists", "error in User create(User u)", null);
            }
        }
        invoiceDataDAO.save(u.getInvoiceData());
        tShirtInfoDAO.save(u.gettShirtInfo());
        return userDAO.save(u);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(@PathParam("id") long id, @Valid User user) throws AppException {
        User u = userDAO.findOne(id);
        if (u == null) {
            throw new AppException(404, 990, "User with id " + id + " does not exist", null, null);
        }
        user.setId(id);
        invoiceDataDAO.save(user.getInvoiceData());
        tShirtInfoDAO.save(user.gettShirtInfo());
        return userDAO.save(user);
    }

    @DELETE
    @Path("/{id}")
    public ResponseEntity<String> delete(@PathParam("id") long id) throws AppException {
        User u = userDAO.findOne(id);
        if (u == null) {
            throw new AppException(404, 990, "User with id " + id + " does not exist", null, null);
        } else {
            tShirtInfoDAO.delete(u.gettShirtInfo());
            invoiceDataDAO.delete(u.getInvoiceData());
            userDAO.delete(id);
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        }

    }

}
