package com.example.sectors.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SectorResponse {
    private Long id;
    private String name;
    private Long parentId;
    private List<SectorResponse> sectors;
}
