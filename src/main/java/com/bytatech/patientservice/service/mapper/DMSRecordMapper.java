package com.bytatech.patientservice.service.mapper;

import com.bytatech.patientservice.domain.*;
import com.bytatech.patientservice.service.dto.DMSRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DMSRecord} and its DTO {@link DMSRecordDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedicalCaseMapper.class})
public interface DMSRecordMapper extends EntityMapper<DMSRecordDTO, DMSRecord> {

    @Mapping(source = "medicalCase.id", target = "medicalCaseId")
    DMSRecordDTO toDto(DMSRecord dMSRecord);

    @Mapping(source = "medicalCaseId", target = "medicalCase")
    DMSRecord toEntity(DMSRecordDTO dMSRecordDTO);

    default DMSRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        DMSRecord dMSRecord = new DMSRecord();
        dMSRecord.setId(id);
        return dMSRecord;
    }
}
