package com.bytatech.patientservice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.patientservice.web.rest.TestUtil;

public class DMSRecordTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DMSRecord.class);
        DMSRecord dMSRecord1 = new DMSRecord();
        dMSRecord1.setId(1L);
        DMSRecord dMSRecord2 = new DMSRecord();
        dMSRecord2.setId(dMSRecord1.getId());
        assertThat(dMSRecord1).isEqualTo(dMSRecord2);
        dMSRecord2.setId(2L);
        assertThat(dMSRecord1).isNotEqualTo(dMSRecord2);
        dMSRecord1.setId(null);
        assertThat(dMSRecord1).isNotEqualTo(dMSRecord2);
    }
}
