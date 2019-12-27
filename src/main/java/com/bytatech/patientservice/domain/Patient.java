package com.bytatech.patientservice.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Patient.
 */
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "idp_code")
    private String idpCode;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "location")
    private String location;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "dms_id")
    private String dmsId;

    @OneToMany(mappedBy = "patient")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MedicalCase> medicalCases = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Patient imageLink(String imageLink) {
        this.imageLink = imageLink;
        return this;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Patient phoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdpCode() {
        return idpCode;
    }

    public Patient idpCode(String idpCode) {
        this.idpCode = idpCode;
        return this;
    }

    public void setIdpCode(String idpCode) {
        this.idpCode = idpCode;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Patient dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getLocation() {
        return location;
    }

    public Patient location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Patient createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getDmsId() {
        return dmsId;
    }

    public Patient dmsId(String dmsId) {
        this.dmsId = dmsId;
        return this;
    }

    public void setDmsId(String dmsId) {
        this.dmsId = dmsId;
    }

    public Set<MedicalCase> getMedicalCases() {
        return medicalCases;
    }

    public Patient medicalCases(Set<MedicalCase> medicalCases) {
        this.medicalCases = medicalCases;
        return this;
    }

    public Patient addMedicalCases(MedicalCase medicalCase) {
        this.medicalCases.add(medicalCase);
        medicalCase.setPatient(this);
        return this;
    }

    public Patient removeMedicalCases(MedicalCase medicalCase) {
        this.medicalCases.remove(medicalCase);
        medicalCase.setPatient(null);
        return this;
    }

    public void setMedicalCases(Set<MedicalCase> medicalCases) {
        this.medicalCases = medicalCases;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        return id != null && id.equals(((Patient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Patient{" +
            "id=" + getId() +
            ", imageLink='" + getImageLink() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", idpCode='" + getIdpCode() + "'" +
            ", dob='" + getDob() + "'" +
            ", location='" + getLocation() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", dmsId='" + getDmsId() + "'" +
            "}";
    }
}
