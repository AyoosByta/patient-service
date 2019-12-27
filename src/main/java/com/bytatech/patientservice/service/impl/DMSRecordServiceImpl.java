package com.bytatech.patientservice.service.impl;

import com.bytatech.patientservice.service.DMSRecordService;
import com.bytatech.patientservice.domain.DMSRecord;
import com.bytatech.patientservice.repository.DMSRecordRepository;
import com.bytatech.patientservice.service.dto.DMSRecordDTO;
import com.bytatech.patientservice.service.mapper.DMSRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DMSRecord}.
 */
@Service
@Transactional
public class DMSRecordServiceImpl implements DMSRecordService {

    private final Logger log = LoggerFactory.getLogger(DMSRecordServiceImpl.class);

    private final DMSRecordRepository dMSRecordRepository;

    private final DMSRecordMapper dMSRecordMapper;

    public DMSRecordServiceImpl(DMSRecordRepository dMSRecordRepository, DMSRecordMapper dMSRecordMapper) {
        this.dMSRecordRepository = dMSRecordRepository;
        this.dMSRecordMapper = dMSRecordMapper;
    }

    /**
     * Save a dMSRecord.
     *
     * @param dMSRecordDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DMSRecordDTO save(DMSRecordDTO dMSRecordDTO) {
        log.debug("Request to save DMSRecord : {}", dMSRecordDTO);
        DMSRecord dMSRecord = dMSRecordMapper.toEntity(dMSRecordDTO);
        dMSRecord = dMSRecordRepository.save(dMSRecord);
        return dMSRecordMapper.toDto(dMSRecord);
    }

    /**
     * Get all the dMSRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DMSRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DMSRecords");
        return dMSRecordRepository.findAll(pageable)
            .map(dMSRecordMapper::toDto);
    }


    /**
     * Get one dMSRecord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DMSRecordDTO> findOne(Long id) {
        log.debug("Request to get DMSRecord : {}", id);
        return dMSRecordRepository.findById(id)
            .map(dMSRecordMapper::toDto);
    }

    /**
     * Delete the dMSRecord by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DMSRecord : {}", id);
        dMSRecordRepository.deleteById(id);
    }
}
