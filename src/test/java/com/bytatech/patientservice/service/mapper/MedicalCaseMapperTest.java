package com.bytatech.patientservice.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MedicalCaseMapperTest {

    private MedicalCaseMapper medicalCaseMapper;

    @BeforeEach
    public void setUp() {
        medicalCaseMapper = new MedicalCaseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(medicalCaseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medicalCaseMapper.fromId(null)).isNull();
    }
}
