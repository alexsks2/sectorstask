package com.example.sectors.controller;

import com.example.sectors.dto.SectorResponse;
import com.example.sectors.service.SectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/sector")
@RequiredArgsConstructor
public class SectorController {
    private final SectorService sectorService;

    /**
     * Get the list of sectors
     * @return
     */
    @GetMapping
    public List<SectorResponse> getAll() {
        return sectorService.getAll();
    }
}
