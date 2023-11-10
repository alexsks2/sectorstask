package com.example.sectors.controller;

import com.example.sectors.dto.FormRequest;
import com.example.sectors.dto.FormResponse;
import com.example.sectors.service.FormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/form")
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    /**
     * Saving the form
     * @param request
     * @return FormResponse
     */
    @PostMapping
    public FormResponse save(@RequestBody @Valid FormRequest request) {
        return formService.save(request);
    }

    /**
     * Update the form by id
     * @param id
     * @param request
     * @return FormResponse
     */
    @PutMapping("/{id}")
    public FormResponse update(@PathVariable Long id, @RequestBody @Valid FormRequest request) {
        return formService.update(id, request);
    }
}
