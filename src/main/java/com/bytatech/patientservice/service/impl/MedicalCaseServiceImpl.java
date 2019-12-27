package com.bytatech.patientservice.service.impl;

import com.bytatech.patientservice.service.MedicalCaseService;
import com.bytatech.patientservice.domain.MedicalCase;
import com.bytatech.patientservice.repository.MedicalCaseRepository;
import com.bytatech.patientservice.service.dto.MedicalCaseDTO;
import com.bytatech.patientservice.service.mapper.MedicalCaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalCase}.
 */
@Service
@Transactional
public class MedicalCaseServiceImpl implements MedicalCaseService {

    private final Logger log = LoggerFactory.getLogger(MedicalCaseServiceImpl.class);

    private final MedicalCaseRepository medicalCaseRepository;

    private final MedicalCaseMapper medicalCaseMapper;

    public MedicalCaseServiceImpl(MedicalCaseRepository medicalCaseRepository, MedicalCaseMapper medicalCaseMapper) {
        this.medicalCaseRepository = medicalCaseRepository;
        this.medicalCaseMapper = medicalCaseMapper;
    }

    /**
     * Save a medicalCase.
     *
     * @param medicalCaseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MedicalCaseDTO save(MedicalCaseDTO medicalCaseDTO) {
        log.debug("Request to save MedicalCase : {}", medicalCaseDTO);
        MedicalCase medicalCase = medicalCaseMapper.toEntity(medicalCaseDTO);
        medicalCase = medicalCaseRepository.save(medicalCase);
        return medicalCaseMapper.toDto(medicalCase);
    }

    /**
     * Get all the medicalCases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MedicalCaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalCases");
        return medicalCaseRepository.findAll(pageable)
            .map(medicalCaseMapper::toDto);
    }


    /**
     * Get one medicalCase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MedicalCaseDTO> findOne(Long id) {
        log.debug("Request to get MedicalCase : {}", id);
        return medicalCaseRepository.findById(id)
            .map(medicalCaseMapper::toDto);
    }

    /**
     * Delete the medicalCase by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicalCase : {}", id);
        medicalCaseRepository.deleteById(id);
    }
}
