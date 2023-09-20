package com.arjun.organizationservice.service.impl;

import com.arjun.organizationservice.dto.OrganizationDto;
import com.arjun.organizationservice.entity.Organization;
import com.arjun.organizationservice.mapper.OrganizationMapper;
import com.arjun.organizationservice.repository.OrganizationRepository;
import com.arjun.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        // convert OrganizationDto into Organization jpa entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

}
