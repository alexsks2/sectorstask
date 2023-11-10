package com.example.sectors.util;

import com.example.sectors.entity.Form;
import com.example.sectors.entity.Sector;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataCreator {

    public static Sector createMainSector() {
        return new Sector()
                .setName(TestConstant.MAIN_SECTOR_NAME)
                .setParentSector(null)
                .setSectors(new ArrayList<>());
    }

    public static Sector createSubSector(Sector sector) {
        return new Sector()
                .setName(TestConstant.SUB_SECTOR_NAME)
                .setParentSector(sector)
                .setSectors(new ArrayList<>());
    }

    public static Sector createDefaultSector() {
        return new Sector()
                .setId(TestConstant.DEFAULT_SECTOR_ID)
                .setName(TestConstant.DEFAULT_SECTOR_NAME)
                .setParentSector(
                        new Sector().setId(2L))
                .setSectors(List.of(createMainSector()));
    }

    public static Form createForm(Sector sector) {
        return new Form()
                .setAgreeToTerms(true)
                .setSector(sector)
                .setUsername(TestConstant.FORM_NAME);
    }
}
