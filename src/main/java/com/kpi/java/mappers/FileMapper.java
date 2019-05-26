package com.kpi.java.mappers;

import com.kpi.java.dtos.FileDTO;
import com.kpi.java.entities.File;
import org.mapstruct.Mapper;

@Mapper
public interface FileMapper {

    FileDTO map2Dto(File file);
    File map2Entity(FileDTO fileDTO);
}
