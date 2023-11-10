package com.example.sectors.converter;

import com.example.sectors.dto.FormRequest;
import com.example.sectors.dto.FormResponse;
import com.example.sectors.entity.Form;
import com.example.sectors.service.SectorService;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring", uses = { SectorService.class})
public interface FormMapper {

    @Mapping(target = "sector", source = "request.sectorId")
    @Mapping(target = "agreeToTerms", source = "request.agreeToTerms")
    Form convertToEntity(FormRequest request);

    @Mapping(target = "sector", source = "request.sectorId")
    Form updateEntityByRequest(@MappingTarget Form existedForm, FormRequest request);

    @Mapping(target = "sectorId", source = "sector.id")
    FormResponse convertToResponse(Form form);
}
