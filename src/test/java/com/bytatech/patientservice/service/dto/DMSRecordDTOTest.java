package com.bytatech.patientservice.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.patientservice.web.rest.TestUtil;

public class DMSRecordDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DMSRecordDTO.class);
        DMSRecordDTO dMSRecordDTO1 = new DMSRecordDTO();
        dMSRecordDTO1.setId(1L);
        DMSRecordDTO dMSRecordDTO2 = new DMSRecordDTO();
        assertThat(dMSRecordDTO1).isNotEqualTo(dMSRecordDTO2);
        dMSRecordDTO2.setId(dMSRecordDTO1.getId());
        assertThat(dMSRecordDTO1).isEqualTo(dMSRecordDTO2);
        dMSRecordDTO2.setId(2L);
        assertThat(dMSRecordDTO1).isNotEqualTo(dMSRecordDTO2);
        dMSRecordDTO1.setId(null);
        assertThat(dMSRecordDTO1).isNotEqualTo(dMSRecordDTO2);
    }
}
