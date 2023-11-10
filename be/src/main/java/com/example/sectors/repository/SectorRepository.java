package com.example.sectors.repository;

import com.example.sectors.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAllByParentSectorNull();
}
