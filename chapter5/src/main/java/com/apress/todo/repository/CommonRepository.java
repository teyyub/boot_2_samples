package com.apress.todo.repository;

import java.util.Collection;
import java.util.Optional;

public interface CommonRepository<T> {
      Optional<T> save(T domain);
      Iterable<Optional<T>> save(Collection<T> domains);
      void delete(T domain);
       Optional<T> findById(Long id);
      Iterable<Optional<T>> findAll();
}

