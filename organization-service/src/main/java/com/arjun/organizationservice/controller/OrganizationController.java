package com.arjun.organizationservice.controller;

import com.arjun.organizationservice.dto.OrganizationDto;
import com.arjun.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService organizationService;

    // Build Save Organization REST API
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto>  getOrganization(@PathVariable("code") String organizationCode){
        OrganizationDto organizationDto =organizationService.getOrganizationCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }

}
