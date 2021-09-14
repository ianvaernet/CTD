package com.example.TFI.Persistence;

import java.util.List;

public interface IDAO<T> {
    public int create(T newObject);
    public T update(int ID, T updatedObject);
    public boolean delete(int id);
    public T get(int id);
    public List<T> list();
}
