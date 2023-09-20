package com.arjun.organizationservice.service;

import com.arjun.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationCode(String organizationCode);
}
