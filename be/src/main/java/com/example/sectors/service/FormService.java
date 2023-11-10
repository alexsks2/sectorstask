package com.example.sectors.service;

import com.example.sectors.dto.FormRequest;
import com.example.sectors.dto.FormResponse;
import com.example.sectors.entity.Form;
import com.example.sectors.repository.FormRepository;
import com.example.sectors.converter.FormMapper;
import com.example.sectors.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final FormMapper formMapper;

    @Transactional
    public FormResponse save(FormRequest request) {
        Form convertedForm = formMapper.convertToEntity(request);
        return saveOrUpdateForm(convertedForm);
    }

    @Transactional
    public FormResponse update(Long id, FormRequest request) {
        Form existedForm = formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form", id));

        return saveOrUpdateForm(formMapper.updateEntityByRequest(existedForm, request));
    }

    private FormResponse saveOrUpdateForm(Form form) {
        Form savedForm = formRepository.save(form);
        return formMapper.convertToResponse(savedForm);
    }
}
