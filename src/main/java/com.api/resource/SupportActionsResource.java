package com.api.resource;

import com.api.dao.SupportActionsDao;
import com.api.model.Support;

import javax.ws.rs.Path;

@Path("supports")
public class SupportActionsResource extends BaseResource<Support, SupportActionsDao>{
    SupportActionsDao dao = new SupportActionsDao();

    public SupportActionsResource(){
        setDb(dao);
    }
}
