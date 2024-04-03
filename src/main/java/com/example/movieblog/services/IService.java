package com.example.movieblog.services;

import java.util.List;
import java.util.Optional;

public interface IService<K, T>{
    T save(T object);
    List<T> findAll();
    Optional<T> findOne(K key);
    void delete(K key);


}
