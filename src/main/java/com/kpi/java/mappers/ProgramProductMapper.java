package com.kpi.java.mappers;

import com.kpi.java.dtos.ProgramProductDTO;
import com.kpi.java.entities.ProgramProduct;
import org.mapstruct.Mapper;

@Mapper
public interface ProgramProductMapper {
    ProgramProduct map2Entity(ProgramProductDTO programProductDTO);
    ProgramProductDTO map2Dto(ProgramProduct programProduct);
}
