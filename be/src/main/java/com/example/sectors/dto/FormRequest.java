package com.example.sectors.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class FormRequest {
    @NotEmpty(message = "Username should not be empty")
    @NotNull
    private String username;
    @NotNull(message = "Sector should not be null")
    private Long sectorId;
    @NotNull
    private Boolean agreeToTerms;
}
