package com.bytatech.patientservice.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bytatech.patientservice.domain.DMSRecord} entity.
 */
public class DMSRecordDTO implements Serializable {

    private Long id;

    private String prescriptionRef;


    private Long medicalCaseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrescriptionRef() {
        return prescriptionRef;
    }

    public void setPrescriptionRef(String prescriptionRef) {
        this.prescriptionRef = prescriptionRef;
    }

    public Long getMedicalCaseId() {
        return medicalCaseId;
    }

    public void setMedicalCaseId(Long medicalCaseId) {
        this.medicalCaseId = medicalCaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DMSRecordDTO dMSRecordDTO = (DMSRecordDTO) o;
        if (dMSRecordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dMSRecordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DMSRecordDTO{" +
            "id=" + getId() +
            ", prescriptionRef='" + getPrescriptionRef() + "'" +
            ", medicalCaseId=" + getMedicalCaseId() +
            "}";
    }
}
