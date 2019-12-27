package com.bytatech.patientservice.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DMSRecordMapperTest {

    private DMSRecordMapper dMSRecordMapper;

    @BeforeEach
    public void setUp() {
        dMSRecordMapper = new DMSRecordMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dMSRecordMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dMSRecordMapper.fromId(null)).isNull();
    }
}
