package com.bytatech.patientservice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.patientservice.web.rest.TestUtil;

public class MedicalCaseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalCase.class);
        MedicalCase medicalCase1 = new MedicalCase();
        medicalCase1.setId(1L);
        MedicalCase medicalCase2 = new MedicalCase();
        medicalCase2.setId(medicalCase1.getId());
        assertThat(medicalCase1).isEqualTo(medicalCase2);
        medicalCase2.setId(2L);
        assertThat(medicalCase1).isNotEqualTo(medicalCase2);
        medicalCase1.setId(null);
        assertThat(medicalCase1).isNotEqualTo(medicalCase2);
    }
}
