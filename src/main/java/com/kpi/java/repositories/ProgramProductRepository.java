package com.kpi.java.repositories;

import com.kpi.java.entities.ProgramProduct;

import java.util.List;

public interface ProgramProductRepository {
    List<ProgramProduct> findAll();
}
