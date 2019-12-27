package com.bytatech.patientservice.service;

import com.bytatech.patientservice.service.dto.MedicalCaseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.bytatech.patientservice.domain.MedicalCase}.
 */
public interface MedicalCaseService {

    /**
     * Save a medicalCase.
     *
     * @param medicalCaseDTO the entity to save.
     * @return the persisted entity.
     */
    MedicalCaseDTO save(MedicalCaseDTO medicalCaseDTO);

    /**
     * Get all the medicalCases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MedicalCaseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" medicalCase.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MedicalCaseDTO> findOne(Long id);

    /**
     * Delete the "id" medicalCase.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
