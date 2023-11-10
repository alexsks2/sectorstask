package com.example.sectors.service;

import com.example.sectors.dto.SectorResponse;
import com.example.sectors.repository.SectorRepository;
import com.example.sectors.converter.SectorMapper;
import com.example.sectors.entity.Sector;
import com.example.sectors.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SectorService {
    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    public List<SectorResponse> getAll() {
        List<Sector> sectors = sectorRepository.findAllByParentSectorNull();
        return sectorMapper.convertToResponse(sectors);
    }

    public Sector getById(Long id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sector", id));
    }
}
