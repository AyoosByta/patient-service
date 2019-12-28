package com.bytatech.patientservice.web.rest;

import com.bytatech.patientservice.PatientServiceApp;
import com.bytatech.patientservice.config.TestSecurityConfiguration;
import com.bytatech.patientservice.domain.MedicalCase;
import com.bytatech.patientservice.repository.MedicalCaseRepository;
import com.bytatech.patientservice.service.MedicalCaseService;
import com.bytatech.patientservice.service.dto.MedicalCaseDTO;
import com.bytatech.patientservice.service.mapper.MedicalCaseMapper;
import com.bytatech.patientservice.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.bytatech.patientservice.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MedicalCaseResource} REST controller.
 */
@SpringBootTest(classes = {PatientServiceApp.class, TestSecurityConfiguration.class})
public class MedicalCaseResourceIT {

    private static final LocalDate DEFAULT_CONSULTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CONSULTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DIAGNOSED = "AAAAAAAAAA";
    private static final String UPDATED_DIAGNOSED = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    @Autowired
    private MedicalCaseRepository medicalCaseRepository;

    @Autowired
    private MedicalCaseMapper medicalCaseMapper;

    @Autowired
    private MedicalCaseService medicalCaseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMedicalCaseMockMvc;

    private MedicalCase medicalCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MedicalCaseResource medicalCaseResource = new MedicalCaseResource(medicalCaseService);
        this.restMedicalCaseMockMvc = MockMvcBuilders.standaloneSetup(medicalCaseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalCase createEntity(EntityManager em) {
        MedicalCase medicalCase = new MedicalCase()
            .consultedDate(DEFAULT_CONSULTED_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .diagnosed(DEFAULT_DIAGNOSED)
            .note(DEFAULT_NOTE);
        return medicalCase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalCase createUpdatedEntity(EntityManager em) {
        MedicalCase medicalCase = new MedicalCase()
            .consultedDate(UPDATED_CONSULTED_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .diagnosed(UPDATED_DIAGNOSED)
            .note(UPDATED_NOTE);
        return medicalCase;
    }

    @BeforeEach
    public void initTest() {
        medicalCase = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalCase() throws Exception {
        int databaseSizeBeforeCreate = medicalCaseRepository.findAll().size();

        // Create the MedicalCase
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);
        restMedicalCaseMockMvc.perform(post("/api/medical-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalCase in the database
        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalCase testMedicalCase = medicalCaseList.get(medicalCaseList.size() - 1);
        assertThat(testMedicalCase.getConsultedDate()).isEqualTo(DEFAULT_CONSULTED_DATE);
        assertThat(testMedicalCase.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMedicalCase.getDiagnosed()).isEqualTo(DEFAULT_DIAGNOSED);
        assertThat(testMedicalCase.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    public void createMedicalCaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalCaseRepository.findAll().size();

        // Create the MedicalCase with an existing ID
        medicalCase.setId(1L);
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalCaseMockMvc.perform(post("/api/medical-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalCase in the database
        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedicalCases() throws Exception {
        // Initialize the database
        medicalCaseRepository.saveAndFlush(medicalCase);

        // Get all the medicalCaseList
        restMedicalCaseMockMvc.perform(get("/api/medical-cases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].consultedDate").value(hasItem(DEFAULT_CONSULTED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].diagnosed").value(hasItem(DEFAULT_DIAGNOSED)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)));
    }
    
    @Test
    @Transactional
    public void getMedicalCase() throws Exception {
        // Initialize the database
        medicalCaseRepository.saveAndFlush(medicalCase);

        // Get the medicalCase
        restMedicalCaseMockMvc.perform(get("/api/medical-cases/{id}", medicalCase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(medicalCase.getId().intValue()))
            .andExpect(jsonPath("$.consultedDate").value(DEFAULT_CONSULTED_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.diagnosed").value(DEFAULT_DIAGNOSED))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE));
    }

    @Test
    @Transactional
    public void getNonExistingMedicalCase() throws Exception {
        // Get the medicalCase
        restMedicalCaseMockMvc.perform(get("/api/medical-cases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalCase() throws Exception {
        // Initialize the database
        medicalCaseRepository.saveAndFlush(medicalCase);

        int databaseSizeBeforeUpdate = medicalCaseRepository.findAll().size();

        // Update the medicalCase
        MedicalCase updatedMedicalCase = medicalCaseRepository.findById(medicalCase.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalCase are not directly saved in db
        em.detach(updatedMedicalCase);
        updatedMedicalCase
            .consultedDate(UPDATED_CONSULTED_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .diagnosed(UPDATED_DIAGNOSED)
            .note(UPDATED_NOTE);
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(updatedMedicalCase);

        restMedicalCaseMockMvc.perform(put("/api/medical-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalCase in the database
        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeUpdate);
        MedicalCase testMedicalCase = medicalCaseList.get(medicalCaseList.size() - 1);
        assertThat(testMedicalCase.getConsultedDate()).isEqualTo(UPDATED_CONSULTED_DATE);
        assertThat(testMedicalCase.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMedicalCase.getDiagnosed()).isEqualTo(UPDATED_DIAGNOSED);
        assertThat(testMedicalCase.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalCase() throws Exception {
        int databaseSizeBeforeUpdate = medicalCaseRepository.findAll().size();

        // Create the MedicalCase
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalCaseMockMvc.perform(put("/api/medical-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalCase in the database
        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalCase() throws Exception {
        // Initialize the database
        medicalCaseRepository.saveAndFlush(medicalCase);

        int databaseSizeBeforeDelete = medicalCaseRepository.findAll().size();

        // Delete the medicalCase
        restMedicalCaseMockMvc.perform(delete("/api/medical-cases/{id}", medicalCase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
