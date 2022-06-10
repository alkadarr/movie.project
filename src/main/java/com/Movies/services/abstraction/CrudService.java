package com.Movies.services.abstraction;

import java.util.List;

public interface CrudService {

    public List<?> getAll();
    public Object getById(Object id);
    public Object insert(Object object);
    public Object update(Object id, Object object);
    public void delete(Object id);

}
