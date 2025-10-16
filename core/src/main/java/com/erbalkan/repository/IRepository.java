package com.erbalkan.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    T save(T entity);
    void delete(T entity); // Soft delete
}

/*
Soyut veri erişim sözleşmesi. Başka adapter’lar 
(örneğin MongoAdapter, RestAdapter) da bu interface’i implement edebilir.
*/