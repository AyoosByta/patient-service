package com.bytatech.patientservice.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.patientservice.web.rest.TestUtil;

public class MedicalCaseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalCaseDTO.class);
        MedicalCaseDTO medicalCaseDTO1 = new MedicalCaseDTO();
        medicalCaseDTO1.setId(1L);
        MedicalCaseDTO medicalCaseDTO2 = new MedicalCaseDTO();
        assertThat(medicalCaseDTO1).isNotEqualTo(medicalCaseDTO2);
        medicalCaseDTO2.setId(medicalCaseDTO1.getId());
        assertThat(medicalCaseDTO1).isEqualTo(medicalCaseDTO2);
        medicalCaseDTO2.setId(2L);
        assertThat(medicalCaseDTO1).isNotEqualTo(medicalCaseDTO2);
        medicalCaseDTO1.setId(null);
        assertThat(medicalCaseDTO1).isNotEqualTo(medicalCaseDTO2);
    }
}
