package com.mwstudio.component.rest.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

    public void insert(T newInstance);

    public T get(ID primaryKey);

    public T update(T transientObj);

    public void delete(T persistentObj);

    public List<T> list();

}
