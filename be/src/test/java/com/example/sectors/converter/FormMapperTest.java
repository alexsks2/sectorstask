package com.example.sectors.converter;

import com.example.sectors.dto.FormRequest;
import com.example.sectors.entity.Form;
import com.example.sectors.service.SectorService;
import com.example.sectors.util.TestConstant;
import com.example.sectors.util.TestDataCreator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FormMapperTest {

    private SectorService sectorService;
    private FormMapper formMapper;

    @BeforeEach
    public void init() {
        sectorService = Mockito.mock(SectorService.class);
        formMapper = new FormMapperImpl(sectorService);
    }

    @Test
    public void convertToEntity() {
        FormRequest request = FormRequest.builder()
                .username(TestConstant.FORM_NAME)
                .agreeToTerms(true)
                .sectorId(TestConstant.DEFAULT_SECTOR_ID)
                .build();

        when(sectorService.getById(request.getSectorId())).thenReturn(TestDataCreator.createDefaultSector());

        var actual = formMapper.convertToEntity(request);

        assertAll("Should contains appropriate fields",
                () -> Assertions.assertEquals(actual.getUsername(), request.getUsername()),
                () -> Assertions.assertEquals(actual.getAgreeToTerms(), request.getAgreeToTerms()),
                () -> Assertions.assertEquals(actual.getSector().getId(), request.getSectorId())
        );
    }

    @Test
    public void convertToResponse() {
        Form form = TestDataCreator.createForm(TestDataCreator.createDefaultSector());
        form.setId(TestConstant.DEFAULT_FORM_ID);

        var actual = formMapper.convertToResponse(form);

        assertAll("Should contains appropriate fields",
                () -> Assertions.assertEquals(actual.getId(), form.getId()),
                () -> Assertions.assertEquals(actual.getUsername(), form.getUsername()),
                () -> Assertions.assertEquals(actual.getSectorId(), form.getSector().getId()),
                () -> Assertions.assertEquals(actual.getAgreeToTerms(), form.getAgreeToTerms())
        );
    }

    @Test
    public void updateEntityByRequest() {
        FormRequest request = FormRequest.builder()
                .username(TestConstant.FORM_NAME)
                .agreeToTerms(true)
                .sectorId(TestConstant.DEFAULT_SECTOR_ID)
                .build();

        when(sectorService.getById(request.getSectorId())).thenReturn(TestDataCreator.createDefaultSector());

        var actual = formMapper.updateEntityByRequest(TestDataCreator.createForm(TestDataCreator.createDefaultSector()), request);

        assertAll("Should contains appropriate fields",
                () -> Assertions.assertEquals(actual.getUsername(), request.getUsername()),
                () -> Assertions.assertEquals(actual.getAgreeToTerms(), request.getAgreeToTerms()),
                () -> Assertions.assertEquals(actual.getSector().getId(), request.getSectorId())
        );
    }
}