package com.example.demo.dao;

import com.example.demo.domain.Post;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T> {
    public abstract List<T> getAll();
    public abstract Optional<T> findById(String id);
    public abstract boolean add(T t);
    public abstract boolean delete(String id);
    public abstract boolean edit(String id, T t);
}
