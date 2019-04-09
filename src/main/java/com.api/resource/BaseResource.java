package com.api.resource;

import com.api.dao.BaseDao;
import com.api.model.BaseModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public abstract class BaseResource <T extends BaseModel, D extends BaseDao<T>> {
    private D dao;

    void setDb(D dao) {
        this.dao = dao;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<T> getAllMessages()  {
        return dao.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public T getById(@PathParam("id") Long id){
        return dao.getById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void delete(@PathParam("id") Long id) {
        dao.delete(id);
    }
}
