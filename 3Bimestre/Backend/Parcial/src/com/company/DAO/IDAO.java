package com.company.DAO;

import java.util.List;

public interface IDAO<T> {
    public int create(T newObject);
    public T update(int ID, T updatedObject);
    public void delete(int id);
    public T get(int id);
    public List<T> list();
}
