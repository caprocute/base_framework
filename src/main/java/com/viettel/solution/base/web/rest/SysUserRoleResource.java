package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysUserRoleRepository;
import com.viettel.solution.base.service.SysUserRoleService;
import com.viettel.solution.base.service.dto.SysUserRoleDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysUserRole}.
 */
@RestController
@RequestMapping("/api")
public class SysUserRoleResource {

    private final Logger log = LoggerFactory.getLogger(SysUserRoleResource.class);

    private static final String ENTITY_NAME = "sysUserRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysUserRoleService sysUserRoleService;

    private final SysUserRoleRepository sysUserRoleRepository;

    public SysUserRoleResource(SysUserRoleService sysUserRoleService, SysUserRoleRepository sysUserRoleRepository) {
        this.sysUserRoleService = sysUserRoleService;
        this.sysUserRoleRepository = sysUserRoleRepository;
    }

    /**
     * {@code POST  /sys-user-roles} : Create a new sysUserRole.
     *
     * @param sysUserRoleDTO the sysUserRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysUserRoleDTO, or with status {@code 400 (Bad Request)} if the sysUserRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-user-roles")
    public ResponseEntity<SysUserRoleDTO> createSysUserRole(@Valid @RequestBody SysUserRoleDTO sysUserRoleDTO) throws URISyntaxException {
        log.debug("REST request to save SysUserRole : {}", sysUserRoleDTO);
        if (sysUserRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysUserRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysUserRoleDTO result = sysUserRoleService.save(sysUserRoleDTO);
        return ResponseEntity
            .created(new URI("/api/sys-user-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-user-roles/:id} : Updates an existing sysUserRole.
     *
     * @param id the id of the sysUserRoleDTO to save.
     * @param sysUserRoleDTO the sysUserRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysUserRoleDTO,
     * or with status {@code 400 (Bad Request)} if the sysUserRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysUserRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-user-roles/{id}")
    public ResponseEntity<SysUserRoleDTO> updateSysUserRole(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysUserRoleDTO sysUserRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysUserRole : {}, {}", id, sysUserRoleDTO);
        if (sysUserRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysUserRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysUserRoleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysUserRoleDTO result = sysUserRoleService.update(sysUserRoleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysUserRoleDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-user-roles/:id} : Partial updates given fields of an existing sysUserRole, field will ignore if it is null
     *
     * @param id the id of the sysUserRoleDTO to save.
     * @param sysUserRoleDTO the sysUserRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysUserRoleDTO,
     * or with status {@code 400 (Bad Request)} if the sysUserRoleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysUserRoleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysUserRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-user-roles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysUserRoleDTO> partialUpdateSysUserRole(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysUserRoleDTO sysUserRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysUserRole partially : {}, {}", id, sysUserRoleDTO);
        if (sysUserRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysUserRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysUserRoleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysUserRoleDTO> result = sysUserRoleService.partialUpdate(sysUserRoleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysUserRoleDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-user-roles} : get all the sysUserRoles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysUserRoles in body.
     */
    @GetMapping("/sys-user-roles")
    public ResponseEntity<List<SysUserRoleDTO>> getAllSysUserRoles(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysUserRoles");
        Page<SysUserRoleDTO> page = sysUserRoleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-user-roles/:id} : get the "id" sysUserRole.
     *
     * @param id the id of the sysUserRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysUserRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-user-roles/{id}")
    public ResponseEntity<SysUserRoleDTO> getSysUserRole(@PathVariable String id) {
        log.debug("REST request to get SysUserRole : {}", id);
        Optional<SysUserRoleDTO> sysUserRoleDTO = sysUserRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysUserRoleDTO);
    }

    /**
     * {@code DELETE  /sys-user-roles/:id} : delete the "id" sysUserRole.
     *
     * @param id the id of the sysUserRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-user-roles/{id}")
    public ResponseEntity<Void> deleteSysUserRole(@PathVariable String id) {
        log.debug("REST request to delete SysUserRole : {}", id);
        sysUserRoleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
