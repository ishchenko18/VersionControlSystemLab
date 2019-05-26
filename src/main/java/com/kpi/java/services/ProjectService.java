package com.kpi.java.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpi.java.dtos.ProgramProductDTO;
import com.kpi.java.entities.ProgramProduct;
import com.kpi.java.mappers.ProgramProductMapper;
import com.kpi.java.mappers.ProgramProductMapperImpl;
import com.kpi.java.repositories.ProgramProductRepositoryImpl;
import com.kpi.java.repositories.Repository;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ProjectService {

    private Repository<ProgramProduct> programProductRepository;
    private ObjectMapper objectMapper;
    private ProgramProductMapper programProductMapper;

    public ProjectService() {
        programProductRepository = new ProgramProductRepositoryImpl();
        objectMapper = new ObjectMapper();
        programProductMapper = new ProgramProductMapperImpl();
    }

    public String getAllExistedProjects() throws JsonProcessingException {
        List<ProgramProductDTO> programProductList = programProductRepository.findAll().stream()
                .map(programProductMapper::map2Dto)
                .collect(Collectors.toList());

        return objectMapper.writeValueAsString(programProductList);
    }

    public void addNewProjectUnderVersionControl(String request) throws IOException {
        ProgramProductDTO programProductDTO = objectMapper.readValue(request, ProgramProductDTO.class);
        ProgramProduct programProduct = programProductMapper.map2Entity(programProductDTO);

        programProductRepository.save(programProduct);
    }
}
