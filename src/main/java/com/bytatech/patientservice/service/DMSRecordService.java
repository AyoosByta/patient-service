package com.bytatech.patientservice.service;

import com.bytatech.patientservice.service.dto.DMSRecordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.bytatech.patientservice.domain.DMSRecord}.
 */
public interface DMSRecordService {

    /**
     * Save a dMSRecord.
     *
     * @param dMSRecordDTO the entity to save.
     * @return the persisted entity.
     */
    DMSRecordDTO save(DMSRecordDTO dMSRecordDTO);

    /**
     * Get all the dMSRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DMSRecordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dMSRecord.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DMSRecordDTO> findOne(Long id);

    /**
     * Delete the "id" dMSRecord.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
