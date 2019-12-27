package com.bytatech.patientservice.service.mapper;

import com.bytatech.patientservice.domain.*;
import com.bytatech.patientservice.service.dto.MedicalCaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalCase} and its DTO {@link MedicalCaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface MedicalCaseMapper extends EntityMapper<MedicalCaseDTO, MedicalCase> {

    @Mapping(source = "patient.id", target = "patientId")
    MedicalCaseDTO toDto(MedicalCase medicalCase);

    @Mapping(target = "dmsRecords", ignore = true)
    @Mapping(target = "removeDmsRecords", ignore = true)
    @Mapping(source = "patientId", target = "patient")
    MedicalCase toEntity(MedicalCaseDTO medicalCaseDTO);

    default MedicalCase fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalCase medicalCase = new MedicalCase();
        medicalCase.setId(id);
        return medicalCase;
    }
}
