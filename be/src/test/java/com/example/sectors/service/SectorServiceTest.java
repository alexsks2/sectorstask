package com.example.sectors.service;

import com.example.sectors.BaseIntegrationTest;
import com.example.sectors.exception.ResourceNotFoundException;
import com.example.sectors.util.TestConstant;
import com.example.sectors.util.TestDataCreator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SectorServiceTest extends BaseIntegrationTest {
    private static final Long SECTOR_ID = 1L;
    private static final Long WRONG_ID = 12L;

    @Autowired
    private SectorService sectorService;

    @BeforeEach
    public void setUp() {
        sectorRepository.save(
                TestDataCreator.createSubSector(TestDataCreator.createMainSector()));
    }

    @Test
    public void getAll() {
        var expectedSize = 1;
        var actual = sectorService.getAll();

        assertAll("Should be not null and contains sub sector",
                () -> assertEquals(expectedSize, actual.size()),
                () -> Assertions.assertEquals(actual.get(0).getName(), TestConstant.MAIN_SECTOR_NAME),
                () -> assertFalse(actual.get(0).getSectors().isEmpty()));
    }

    @Test
    public void getById() {
        var actual = sectorService.getById(SECTOR_ID);

        assertNotNull(actual);
    }

    @Test
    public void getByIdShouldThrowResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () ->
                sectorService.getById(WRONG_ID));
    }
}
