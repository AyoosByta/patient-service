package com.bytatech.patientservice.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bytatech.patientservice.domain.MedicalCase} entity.
 */
public class MedicalCaseDTO implements Serializable {

    private Long id;

    private LocalDate consultedDate;

    private LocalDate createdDate;

    private String diagnosed;

    private String note;


    private Long patientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getConsultedDate() {
        return consultedDate;
    }

    public void setConsultedDate(LocalDate consultedDate) {
        this.consultedDate = consultedDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getDiagnosed() {
        return diagnosed;
    }

    public void setDiagnosed(String diagnosed) {
        this.diagnosed = diagnosed;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicalCaseDTO medicalCaseDTO = (MedicalCaseDTO) o;
        if (medicalCaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicalCaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicalCaseDTO{" +
            "id=" + getId() +
            ", consultedDate='" + getConsultedDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", diagnosed='" + getDiagnosed() + "'" +
            ", note='" + getNote() + "'" +
            ", patientId=" + getPatientId() +
            "}";
    }
}
