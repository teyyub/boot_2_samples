package com.boot2.repository;

import java.util.Collection;

public interface CommonRepository<T> {
    public T save(T domain);
    public Iterable<T> save(Collection<T> domains);
    public void delete(T domain);
    public T findById(Long id);
    public Iterable<T> findAll();
}