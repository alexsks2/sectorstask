package com.example.sectors.service;

import com.example.sectors.BaseIntegrationTest;
import com.example.sectors.dto.FormRequest;
import com.example.sectors.entity.Sector;
import com.example.sectors.util.TestConstant;
import com.example.sectors.util.TestDataCreator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Stream;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FormServiceTest extends BaseIntegrationTest {
    private static final String UPDATED_FORM_NAME = "ChangedName";
    private static final Long FORM_ID = 1L;
    private Sector sector;

    @Autowired
    private FormService formService;

    @BeforeEach
    public void init() {
        sector = sectorRepository.save(TestDataCreator.createMainSector());
    }

    @Test
    public void save() {
        FormRequest request = FormRequest.builder()
                .agreeToTerms(true)
                .sectorId(TestConstant.DEFAULT_SECTOR_ID)
                .username(TestConstant.FORM_NAME)
                .build();

        var actual = formService.save(request);

        assertNotNull(actual);
    }

    @Test
    public void saveWithNullSectorIdShouldThrowInvalidDataAccessApiUsageException() {
        FormRequest invalidRequest = FormRequest.builder()
                .agreeToTerms(true)
                .sectorId(null)
                .username(TestConstant.FORM_NAME)
                .build();

        assertThrows(InvalidDataAccessApiUsageException.class, () ->
                formService.save(invalidRequest));
    }

    @ParameterizedTest
    @MethodSource("provideNames")
    void saveWithEmptyFieldShouldThrowConstraintViolationException(String name, Class<ValidationException> clazz) {
        FormRequest invalidRequest = FormRequest.builder()
                .agreeToTerms(true)
                .sectorId(TestConstant.DEFAULT_SECTOR_ID)
                .username(name)
                .build();

        assertThrows(clazz, () ->
                formService.save(invalidRequest));
    }

    @Test
    public void update() {
        formRepository.save(TestDataCreator.createForm(sector));

        FormRequest request = FormRequest.builder()
                .agreeToTerms(true)
                .sectorId(TestConstant.DEFAULT_SECTOR_ID)
                .username(UPDATED_FORM_NAME)
                .build();

        var actual = formService.update(FORM_ID, request);

        assertAll("Should be not null and change value",
                () -> assertNotNull(actual),
                () -> Assertions.assertEquals(actual.getUsername(), UPDATED_FORM_NAME));
    }

    private static Stream<Arguments> provideNames() {
        return Stream.of(
                Arguments.of(null, ConstraintViolationException.class),
                Arguments.of("", ConstraintViolationException.class));
    }
}