package com.kpi.java.services;

import com.kpi.java.entities.File;
import com.kpi.java.mappers.FileMapper;
import com.kpi.java.mappers.FileMapperImpl;
import com.kpi.java.repositories.FileRepositoryImpl;
import com.kpi.java.repositories.Repository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilesService {

    private Repository<File> fileRepository;
    private FileMapper fileMapper;

    public FilesService() {
        fileRepository = new FileRepositoryImpl();
        fileMapper = new FileMapperImpl();
    }

    public void deleteFileFromVersionControl(Long id) {
        fileRepository.delete(id);
    }
}
