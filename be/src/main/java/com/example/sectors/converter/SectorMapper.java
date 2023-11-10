package com.example.sectors.converter;

import com.example.sectors.dto.SectorResponse;
import com.example.sectors.entity.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectorMapper {

    @Mapping(target = "parentId", source = "parentSector.id")
    SectorResponse convertToResponse(Sector sector);

    List<SectorResponse> convertToResponse(List<Sector> sectors);
}
