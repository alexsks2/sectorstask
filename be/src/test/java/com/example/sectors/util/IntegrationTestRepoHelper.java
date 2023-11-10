package com.example.sectors.util;

import com.example.sectors.repository.FormRepository;
import com.example.sectors.repository.SectorRepository;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class IntegrationTestRepoHelper {
    @Autowired
    protected SectorRepository sectorRepository;
    @Autowired
    protected FormRepository formRepository;
}
