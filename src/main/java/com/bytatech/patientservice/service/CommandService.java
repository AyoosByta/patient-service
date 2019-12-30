package com.bytatech.patientservice.service;

import com.bytatech.patientservice.client.dmscore.model.SiteBodyCreate;
import com.bytatech.patientservice.client.dmscore.model.SiteMemberEntry;
/*import com.bytatech.ayoos.service.dto.DMSRecordDTO;*/
import com.bytatech.patientservice.service.dto.PatientDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DMSRecord.
 */
public interface CommandService {

	/**
	 * Save a dMSRecord.
	 *
	 * @param dMSRecordDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	// DMSRecordDTO save(DMSRecordDTO dMSRecordDTO);

	/**
	 * Get all the dMSRecords.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	// Page<DMSRecordDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" dMSRecord.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	// Optional<DMSRecordDTO> findOne(Long id);

	/**
	 * Delete the "id" dMSRecord.
	 *
	 * @param id
	 *            the id of the entity
	 */
	// void delete(Long id);

	/**
	 * Search for the dMSRecord corresponding to the query.
	 *
	 * @param query
	 *            the query of the search
	 * 
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	
	/*public String addPrescriptionOnDMS(byte[] file, String dmsId);

	
*/
	public String createSite(String siteId);
	
	public void createPersonOnDMS(PatientDTO patientDTO);
	public SiteMemberEntry createSiteMembership(String siteId, String idpCode);
}
