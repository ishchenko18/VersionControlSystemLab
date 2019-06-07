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

    public ProgramProductDTO deleteFileFromVersionControl(Long id) {

        File childFile = null;

        if (id > 0) {
            childFile = fileRepository.findChild(id);
        }

        if (childFile != null) {
            log.error("File with id:{} has child file(s).", id);
            throw new ValidationException(String.format("File with id:%s can't be deleted, because it used by another file", id));
        }

        File fileToDelete = fileRepository.findById(id);
        ProgramProduct programProduct = fileToDelete.getProgramProduct();

        log.info("Start deleting file with id: {}", id);
        fileRepository.delete(id);
        log.info("File id:{} successfully deleted.", id);

        programProduct.getFiles().remove(fileToDelete);
        return programProductMapper.map2Dto(programProduct);
    }

    public ProgramProductDTO createNewFileInRepository(String jsonRequest) throws IOException, ValidationException {
        FileDTO fileDTO;

        try {
            fileDTO = objectMapper.readValue(jsonRequest, FileDTO.class);
        } catch (IOException e) {
            log.error("Error parsing json request.");
            throw e;
        }

        Long programProductId = fileDTO.getProgramProduct().getId();
        Long fileVersion = fileDTO.getVersion();
        String fileName = fileDTO.getName();

        ProgramProduct programProduct = programProductRepository.findById(programProductId);

        if (programProduct == null) {
            log.error("Repository by id: {} does not exist.", programProductId);
            throw new ValidationException(String.format("File %s does not linked to any repository.", fileName));
        }

        log.info("Repository with id: {} was find.", programProductId);

        if (fileVersion < 1) {
            log.error("Version of file should be positive value, bu was: {}.", fileVersion);
            throw new ValidationException("Version of file should be a positive value.");
        }

        File previousFile = null;

        if (fileVersion > 1) {
            previousFile = fileRepository.findByNameAndVersion(fileDTO.getName(), fileVersion - 1);

            if (previousFile == null) {
                log.error("File with version {} does not have previous version of file.", fileVersion);
                throw new ValidationException(String.format("File with version %s does not have previous version of file.", fileVersion));
            }

            log.info("File with previous version to {} was find.", fileVersion);
        }


        File file = fileMapper.map2Entity(fileDTO);
        file.setPreviousFile(previousFile);
        file.setProgramProduct(programProduct);

        log.info("Start saving new file to repository - {}.", programProduct.getName());

        fileRepository.saveOrUpdate(file);
        programProduct = programProductRepository.findById(programProductId);

        log.info("Data successfully saved.");

        return programProductMapper.map2Dto(programProduct);
    }
}
