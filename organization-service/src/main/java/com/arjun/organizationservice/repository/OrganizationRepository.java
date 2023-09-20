package com.arjun.organizationservice.repository;

import com.arjun.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findByOrganizationCode(String organizationCode);

}
