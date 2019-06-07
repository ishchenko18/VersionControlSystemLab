package com.kpi.java.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpi.java.dtos.FileDTO;
import com.kpi.java.dtos.ProgramProductDTO;
import com.kpi.java.entities.File;
import com.kpi.java.entities.ProgramProduct;
import com.kpi.java.mappers.FileMapper;
import com.kpi.java.mappers.FileMapperImpl;
import com.kpi.java.mappers.ProgramProductMapper;
import com.kpi.java.mappers.ProgramProductMapperImpl;
import com.kpi.java.repositories.FileRepositoryImpl;
import com.kpi.java.repositories.ProgramProductRepositoryImpl;
import com.kpi.java.repositories.Repository;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ValidationException;
import java.io.IOException;

@Slf4j
public class FilesService {

    private Repository<File> fileRepository;
    private FileMapper fileMapper;
    private ProgramProductMapper programProductMapper;
    private ObjectMapper objectMapper;
    private Repository<ProgramProduct> programProductRepository;

    public FilesService() {
        fileRepository = new FileRepositoryImpl();
        fileMapper = new FileMapperImpl();
        programProductMapper = new ProgramProductMapperImpl();
        objectMapper = new ObjectMapper();
        programProductRepository = new ProgramProductRepositoryImpl();
    }

    public void deleteFileFromVersionControl(Long id) {

        File file = null;

        if (id > 2) {
            file = fileRepository.findById(id - 1);
        }

        if (file != null && file.getPreviousFile() != null) {
            throw new ValidationException(String.format("File with id:%s can't be deleted, because it used by another file", id));
        }

        fileRepository.delete(id);
    }

    public ProgramProductDTO createNewFileInRepository(String jsonRequest) throws IOException, ValidationException {
        FileDTO fileDTO = objectMapper.readValue(jsonRequest, FileDTO.class);

        Long programProductId = fileDTO.getProgramProduct().getId();
        Long fileVersion = fileDTO.getVersion();
        String fileName = fileDTO.getName();

        ProgramProduct programProduct = programProductRepository.findById(programProductId);

        if (programProduct == null) {
            log.error("gavnishe");
            throw new ValidationException(String.format("File %s does not linked to any repository.", fileName));
        }

        if (fileVersion < 1) {
            throw new ValidationException("Version of file should be a positive value.");
        }

        File previousFile = null;

        if (fileVersion > 1) {
            previousFile = fileRepository.findByNameAndVersion(fileDTO.getName(), fileVersion - 1);

            if (previousFile == null) {
                throw new ValidationException(String.format("File with version %s does not have previous version of file.", fileVersion));
            }
        }


        File file = fileMapper.map2Entity(fileDTO);
        file.setPreviousFile(previousFile);
        file.setProgramProduct(programProduct);

        fileRepository.saveOrUpdate(file);
        programProduct = programProductRepository.findById(programProductId);

        return programProductMapper.map2Dto(programProduct);
    }
}
