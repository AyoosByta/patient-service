package com.bytatech.patientservice.service.impl;

import com.bytatech.patientservice.service.CommandService;
import com.bytatech.patientservice.service.DMSRecordService;
import com.bytatech.patientservice.service.PatientService;
import com.bytatech.patientservice.domain.DMSRecord;
import com.bytatech.patientservice.repository.DMSRecordRepository;
//import com.bytatech.patientservice.repository.search.DMSRecordSearchRepository;
import com.bytatech.patientservice.service.dto.DMSRecordDTO;
import com.bytatech.patientservice.service.dto.PatientDTO;
import com.bytatech.patientservice.service.mapper.DMSRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


/*import com.bytatech.patientservice.client.custom_dms_core.ApiKeyRequestInterceptor;
import com.bytatech.patientservice.client.custom_dms_core.api.SitesApi;*/
import com.bytatech.patientservice.client.dmscore.api.PeopleApi;
import com.bytatech.patientservice.client.dmscore.model.PersonBodyCreate;
import com.bytatech.patientservice.client.dmscore.model.PersonEntry;
import com.bytatech.patientservice.client.dmscore.model.SiteBodyCreate;
import com.bytatech.patientservice.client.dmscore.model.SiteEntry;
import com.bytatech.patientservice.client.dmscore.model.SiteMemberEntry;
import com.bytatech.patientservice.client.dmscore.model.SiteMembershipBodyCreate;
import com.bytatech.patientservice.client.dmscore.model.SiteBodyCreate.VisibilityEnum;
import com.bytatech.patientservice.client.dmscore.model.SiteMembershipBodyCreate.RoleEnum;
/**
 * Service Implementation for managing DMSRecord.
 */
@Service
@Transactional
public class CommandServiceImpl implements CommandService {

//    private final Logger log = LoggerFactory.getLogger(CommandServiceImpl.class);
    @Autowired
	private PatientService patientService;
  /*  
	@Autowired
	com.bytatech.patientservice.client.custom_dms_core.api.NodesApi nodesApi;
	

	
	@Autowired
	com.bytatech.patientservice.client.dms_core.api.SitesApi siteApi;*/
	@Autowired
	PeopleApi peopleApi;
    
	@Autowired
	DMSRecordService dMSRecordService ;
	@Autowired
	com.bytatech.patientservice.client.dmscore.api.SitesApi siteApi;
/*   public String addPrescriptionOnDMS(byte[] file, String  idpCode) {
    	//String dmsId=patientService.findDmsIdByIdpCode(idpCode);
		Resource prescriptionResource = new ByteArrayResource(file);
		com.bytatech.patientservice.client.custom_dms_core.model.NodeBodyCreate nodeBodyCreate = new com.bytatech.patientservice.client.custom_dms_core.model.NodeBodyCreate();
		nodeBodyCreate.setName("prescription");
		nodeBodyCreate.setNodeType("cm:content");
		nodeBodyCreate.setRelativePath("Sites/" + dmsId);
		com.bytatech.patientservice.client.custom_dms_core.model.NodeEntry nodeEntry=	nodesApi.createNode("-my-", nodeBodyCreate, true, null, null).getBody();
		nodesApi.updateNodeContent(nodeEntry.getEntry().getId(), prescriptionResource, true, null, null, null, null);
		return nodeEntry.getEntry().getId();
	}*/

    
    /**
	 * Create a new people on DMS.
	 *
	 * @param patientDTO
	 * 
	 *
	 */
	public void createPersonOnDMS(PatientDTO patientDTO) {
	//	log.debug("createPersonOnDMS : {}", patientDTO);

		PersonBodyCreate personBodyCreate = new PersonBodyCreate();
		personBodyCreate.setId(patientDTO.getIdpCode());
		personBodyCreate.setFirstName(patientDTO.getIdpCode());
		personBodyCreate.setEmail(patientDTO.getIdpCode()+"@gmail.com");
		personBodyCreate.setPassword(patientDTO.getIdpCode());
		personBodyCreate.setEnabled(true);
		ResponseEntity<PersonEntry> p = peopleApi.createPerson(personBodyCreate, null);

	}
	/**
	 * Create a new patientDMS-Site.
	 *
	 * @param siteId
	 *            the patientDMS-Site to create
	 *
	 */

	
	public String createSite(String siteId) {
		
		SiteBodyCreate siteBodyCreate = new SiteBodyCreate();
		siteBodyCreate.setTitle(siteId);
		siteBodyCreate.setId(siteId);
		siteBodyCreate.setVisibility(VisibilityEnum.MODERATED);
		
		ResponseEntity<SiteEntry> entry = siteApi.createSite(siteBodyCreate, false, false, null);
	//	System.out.println("entryyyyyyyyyy"+entry.getBody().getEntry());
		
		return entry.getBody().getEntry().getId() ;
	}
	/**
	 * Create a new site membership -.
	 *
	 * @param siteId,idpCode
	 * 
	 *
	 */
	public SiteMemberEntry createSiteMembership(String siteId, String idpCode) {
		SiteMembershipBodyCreate siteMembershipBodyCreate = new SiteMembershipBodyCreate();
		siteMembershipBodyCreate.setRole(RoleEnum.SITEMANAGER);
		siteMembershipBodyCreate.setId(idpCode);
		return siteApi.createSiteMembership(siteId, siteMembershipBodyCreate, null).getBody();
	}
}
