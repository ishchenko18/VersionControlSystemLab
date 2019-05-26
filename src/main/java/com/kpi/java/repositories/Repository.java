package com.kpi.java.repositories;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();

    void save(T t);
}
