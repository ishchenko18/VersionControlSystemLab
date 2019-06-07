package com.kpi.java.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    public List<ProgramProductDTO> getAllExistedProject() {
        return programProductRepository.findAll().stream()
                .map(programProductMapper::map2Dto)
                .collect(Collectors.toList());
    }

    public void addNewProjectUnderVersionControl(String request) throws IOException {
        ProgramProductDTO programProductDTO = objectMapper.readValue(request, ProgramProductDTO.class);
        ProgramProduct programProduct = programProductMapper.map2Entity(programProductDTO);

        programProductRepository.saveOrUpdate(programProduct);
    }

    public void deleteProjectFromVersionControl(String request) throws IOException {
        ObjectNode objectNode = objectMapper.readValue(request, ObjectNode.class);

        if (objectNode.has("repositoryId")) {
            Long projectId = objectNode.get("repositoryId").asLong();
            programProductRepository.delete(projectId);
        }
    }

    public ProgramProductDTO getProject(Long id) {
        ProgramProduct programProduct = programProductRepository.findById(id);

        return programProductMapper.map2Dto(programProduct);
    }

    public ProgramProductDTO updateProject(String request) throws IOException {
        ProgramProductDTO programProductDTO = objectMapper.readValue(request, ProgramProductDTO.class);
        ProgramProduct programProduct = programProductMapper.map2Entity(programProductDTO);

        programProductRepository.saveOrUpdate(programProduct);
        programProduct = programProductRepository.findById(programProduct.getId());

        return programProductMapper.map2Dto(programProduct);
    }
}
