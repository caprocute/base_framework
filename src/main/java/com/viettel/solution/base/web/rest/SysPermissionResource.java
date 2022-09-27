package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysPermissionRepository;
import com.viettel.solution.base.service.SysPermissionService;
import com.viettel.solution.base.service.dto.SysPermissionDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysPermission}.
 */
@RestController
@RequestMapping("/api")
public class SysPermissionResource {

    private final Logger log = LoggerFactory.getLogger(SysPermissionResource.class);

    private static final String ENTITY_NAME = "sysPermission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysPermissionService sysPermissionService;

    private final SysPermissionRepository sysPermissionRepository;

    public SysPermissionResource(SysPermissionService sysPermissionService, SysPermissionRepository sysPermissionRepository) {
        this.sysPermissionService = sysPermissionService;
        this.sysPermissionRepository = sysPermissionRepository;
    }

    /**
     * {@code POST  /sys-permissions} : Create a new sysPermission.
     *
     * @param sysPermissionDTO the sysPermissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysPermissionDTO, or with status {@code 400 (Bad Request)} if the sysPermission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-permissions")
    public ResponseEntity<SysPermissionDTO> createSysPermission(@Valid @RequestBody SysPermissionDTO sysPermissionDTO)
        throws URISyntaxException {
        log.debug("REST request to save SysPermission : {}", sysPermissionDTO);
        if (sysPermissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysPermission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysPermissionDTO result = sysPermissionService.save(sysPermissionDTO);
        return ResponseEntity
            .created(new URI("/api/sys-permissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-permissions/:id} : Updates an existing sysPermission.
     *
     * @param id the id of the sysPermissionDTO to save.
     * @param sysPermissionDTO the sysPermissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysPermissionDTO,
     * or with status {@code 400 (Bad Request)} if the sysPermissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysPermissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-permissions/{id}")
    public ResponseEntity<SysPermissionDTO> updateSysPermission(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysPermissionDTO sysPermissionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysPermission : {}, {}", id, sysPermissionDTO);
        if (sysPermissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysPermissionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysPermissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysPermissionDTO result = sysPermissionService.update(sysPermissionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysPermissionDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-permissions/:id} : Partial updates given fields of an existing sysPermission, field will ignore if it is null
     *
     * @param id the id of the sysPermissionDTO to save.
     * @param sysPermissionDTO the sysPermissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysPermissionDTO,
     * or with status {@code 400 (Bad Request)} if the sysPermissionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysPermissionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysPermissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-permissions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysPermissionDTO> partialUpdateSysPermission(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysPermissionDTO sysPermissionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysPermission partially : {}, {}", id, sysPermissionDTO);
        if (sysPermissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysPermissionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysPermissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysPermissionDTO> result = sysPermissionService.partialUpdate(sysPermissionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysPermissionDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-permissions} : get all the sysPermissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysPermissions in body.
     */
    @GetMapping("/sys-permissions")
    public ResponseEntity<List<SysPermissionDTO>> getAllSysPermissions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysPermissions");
        Page<SysPermissionDTO> page = sysPermissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-permissions/:id} : get the "id" sysPermission.
     *
     * @param id the id of the sysPermissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysPermissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-permissions/{id}")
    public ResponseEntity<SysPermissionDTO> getSysPermission(@PathVariable String id) {
        log.debug("REST request to get SysPermission : {}", id);
        Optional<SysPermissionDTO> sysPermissionDTO = sysPermissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysPermissionDTO);
    }

    /**
     * {@code DELETE  /sys-permissions/:id} : delete the "id" sysPermission.
     *
     * @param id the id of the sysPermissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-permissions/{id}")
    public ResponseEntity<Void> deleteSysPermission(@PathVariable String id) {
        log.debug("REST request to delete SysPermission : {}", id);
        sysPermissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
