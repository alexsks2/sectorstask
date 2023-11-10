package com.example.sectors.converter;

import com.example.sectors.entity.Sector;
import com.example.sectors.util.TestDataCreator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class SectorMapperTest {
    private final SectorMapper sectorMapper = new SectorMapperImpl();

    @Test
    public void convertToResponse() {
        Sector sector = TestDataCreator.createDefaultSector();

        var actual = sectorMapper.convertToResponse(sector);

        assertAll("Should contains appropriate fields",
                () -> Assertions.assertEquals(actual.getId(), sector.getId()),
                () -> Assertions.assertEquals(actual.getName(), sector.getName()),
                () -> Assertions.assertEquals(actual.getParentId(), sector.getParentSector().getId()),
                () -> Assertions.assertFalse(actual.getSectors().isEmpty()));
    }
}