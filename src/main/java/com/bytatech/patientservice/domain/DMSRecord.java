package com.bytatech.patientservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DMSRecord.
 */
@Entity
@Table(name = "dms_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DMSRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prescription_ref")
    private String prescriptionRef;

    @ManyToOne
    @JsonIgnoreProperties("dmsRecords")
    private MedicalCase medicalCase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrescriptionRef() {
        return prescriptionRef;
    }

    public DMSRecord prescriptionRef(String prescriptionRef) {
        this.prescriptionRef = prescriptionRef;
        return this;
    }

    public void setPrescriptionRef(String prescriptionRef) {
        this.prescriptionRef = prescriptionRef;
    }

    public MedicalCase getMedicalCase() {
        return medicalCase;
    }

    public DMSRecord medicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
        return this;
    }

    public void setMedicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DMSRecord)) {
            return false;
        }
        return id != null && id.equals(((DMSRecord) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DMSRecord{" +
            "id=" + getId() +
            ", prescriptionRef='" + getPrescriptionRef() + "'" +
            "}";
    }
}
