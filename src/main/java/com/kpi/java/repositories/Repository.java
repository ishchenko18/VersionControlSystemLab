package com.kpi.java.repositories;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();

    void saveOrUpdate(T t);

    void delete(Long id);

    T findById(Long id);
}
