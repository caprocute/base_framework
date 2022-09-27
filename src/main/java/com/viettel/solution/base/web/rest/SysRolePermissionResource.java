package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysRolePermissionRepository;
import com.viettel.solution.base.service.SysRolePermissionService;
import com.viettel.solution.base.service.dto.SysRolePermissionDTO;
import com.viettel.solution.base.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.viettel.solution.base.domain.SysRolePermission}.
 */
@RestController
@RequestMapping("/api")
public class SysRolePermissionResource {

    private final Logger log = LoggerFactory.getLogger(SysRolePermissionResource.class);

    private static final String ENTITY_NAME = "sysRolePermission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRolePermissionService sysRolePermissionService;

    private final SysRolePermissionRepository sysRolePermissionRepository;

    public SysRolePermissionResource(
        SysRolePermissionService sysRolePermissionService,
        SysRolePermissionRepository sysRolePermissionRepository
    ) {
        this.sysRolePermissionService = sysRolePermissionService;
        this.sysRolePermissionRepository = sysRolePermissionRepository;
    }

    /**
     * {@code POST  /sys-role-permissions} : Create a new sysRolePermission.
     *
     * @param sysRolePermissionDTO the sysRolePermissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRolePermissionDTO, or with status {@code 400 (Bad Request)} if the sysRolePermission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-role-permissions")
    public ResponseEntity<SysRolePermissionDTO> createSysRolePermission(@Valid @RequestBody SysRolePermissionDTO sysRolePermissionDTO)
        throws URISyntaxException {
        log.debug("REST request to save SysRolePermission : {}", sysRolePermissionDTO);
        if (sysRolePermissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysRolePermission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRolePermissionDTO result = sysRolePermissionService.save(sysRolePermissionDTO);
        return ResponseEntity
            .created(new URI("/api/sys-role-permissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-role-permissions/:id} : Updates an existing sysRolePermission.
     *
     * @param id the id of the sysRolePermissionDTO to save.
     * @param sysRolePermissionDTO the sysRolePermissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRolePermissionDTO,
     * or with status {@code 400 (Bad Request)} if the sysRolePermissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRolePermissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-role-permissions/{id}")
    public ResponseEntity<SysRolePermissionDTO> updateSysRolePermission(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysRolePermissionDTO sysRolePermissionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysRolePermission : {}, {}", id, sysRolePermissionDTO);
        if (sysRolePermissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysRolePermissionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysRolePermissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysRolePermissionDTO result = sysRolePermissionService.update(sysRolePermissionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysRolePermissionDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-role-permissions/:id} : Partial updates given fields of an existing sysRolePermission, field will ignore if it is null
     *
     * @param id the id of the sysRolePermissionDTO to save.
     * @param sysRolePermissionDTO the sysRolePermissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRolePermissionDTO,
     * or with status {@code 400 (Bad Request)} if the sysRolePermissionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysRolePermissionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysRolePermissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-role-permissions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysRolePermissionDTO> partialUpdateSysRolePermission(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysRolePermissionDTO sysRolePermissionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysRolePermission partially : {}, {}", id, sysRolePermissionDTO);
        if (sysRolePermissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysRolePermissionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysRolePermissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysRolePermissionDTO> result = sysRolePermissionService.partialUpdate(sysRolePermissionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysRolePermissionDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-role-permissions} : get all the sysRolePermissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRolePermissions in body.
     */
    @GetMapping("/sys-role-permissions")
    public ResponseEntity<List<SysRolePermissionDTO>> getAllSysRolePermissions(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of SysRolePermissions");
        Page<SysRolePermissionDTO> page = sysRolePermissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-role-permissions/:id} : get the "id" sysRolePermission.
     *
     * @param id the id of the sysRolePermissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRolePermissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-role-permissions/{id}")
    public ResponseEntity<SysRolePermissionDTO> getSysRolePermission(@PathVariable String id) {
        log.debug("REST request to get SysRolePermission : {}", id);
        Optional<SysRolePermissionDTO> sysRolePermissionDTO = sysRolePermissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRolePermissionDTO);
    }

    /**
     * {@code DELETE  /sys-role-permissions/:id} : delete the "id" sysRolePermission.
     *
     * @param id the id of the sysRolePermissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-role-permissions/{id}")
    public ResponseEntity<Void> deleteSysRolePermission(@PathVariable String id) {
        log.debug("REST request to delete SysRolePermission : {}", id);
        sysRolePermissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
