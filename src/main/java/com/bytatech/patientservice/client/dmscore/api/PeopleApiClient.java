package com.bytatech.patientservice.client.dmscore.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.patientservice.client.dmscore.ClientConfiguration;

@FeignClient(name="${dmscore.name:dmscore}", url="${dmscore.url:https://aozd73.trial.alfresco.com/alfresco/api/-default-/public/alfresco/versions/1}", configuration = ClientConfiguration.class)
public interface PeopleApiClient extends PeopleApi {
}