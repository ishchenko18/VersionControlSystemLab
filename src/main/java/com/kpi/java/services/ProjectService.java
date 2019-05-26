package com.kpi.java.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpi.java.entities.ProgramProduct;
import com.kpi.java.repositories.ProgramProductRepository;
import com.kpi.java.repositories.ProgramProductRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Slf4j
public class ProjectService {

    private ProgramProductRepository programProductRepository;
    private ObjectMapper objectMapper;

    public ProjectService() {
        programProductRepository = new ProgramProductRepositoryImpl();
        objectMapper = new ObjectMapper();
    }

    public String getAllExistedProjects() {
        List<ProgramProduct> programProductList = programProductRepository.findAll();
        String result = StringUtils.EMPTY;

        try {
            result = objectMapper.writeValueAsString(programProductList);
        } catch (JsonProcessingException e) {
            log.error("Error in processing object");
        }

        return result;
    }
}
