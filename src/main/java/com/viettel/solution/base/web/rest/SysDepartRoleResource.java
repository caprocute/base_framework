package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysDepartRoleRepository;
import com.viettel.solution.base.service.SysDepartRoleService;
import com.viettel.solution.base.service.dto.SysDepartRoleDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysDepartRole}.
 */
@RestController
@RequestMapping("/api")
public class SysDepartRoleResource {

    private final Logger log = LoggerFactory.getLogger(SysDepartRoleResource.class);

    private static final String ENTITY_NAME = "sysDepartRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysDepartRoleService sysDepartRoleService;

    private final SysDepartRoleRepository sysDepartRoleRepository;

    public SysDepartRoleResource(SysDepartRoleService sysDepartRoleService, SysDepartRoleRepository sysDepartRoleRepository) {
        this.sysDepartRoleService = sysDepartRoleService;
        this.sysDepartRoleRepository = sysDepartRoleRepository;
    }

    /**
     * {@code POST  /sys-depart-roles} : Create a new sysDepartRole.
     *
     * @param sysDepartRoleDTO the sysDepartRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysDepartRoleDTO, or with status {@code 400 (Bad Request)} if the sysDepartRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-depart-roles")
    public ResponseEntity<SysDepartRoleDTO> createSysDepartRole(@Valid @RequestBody SysDepartRoleDTO sysDepartRoleDTO)
        throws URISyntaxException {
        log.debug("REST request to save SysDepartRole : {}", sysDepartRoleDTO);
        if (sysDepartRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysDepartRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysDepartRoleDTO result = sysDepartRoleService.save(sysDepartRoleDTO);
        return ResponseEntity
            .created(new URI("/api/sys-depart-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-depart-roles/:id} : Updates an existing sysDepartRole.
     *
     * @param id the id of the sysDepartRoleDTO to save.
     * @param sysDepartRoleDTO the sysDepartRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysDepartRoleDTO,
     * or with status {@code 400 (Bad Request)} if the sysDepartRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysDepartRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-depart-roles/{id}")
    public ResponseEntity<SysDepartRoleDTO> updateSysDepartRole(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysDepartRoleDTO sysDepartRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysDepartRole : {}, {}", id, sysDepartRoleDTO);
        if (sysDepartRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysDepartRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysDepartRoleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysDepartRoleDTO result = sysDepartRoleService.update(sysDepartRoleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysDepartRoleDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-depart-roles/:id} : Partial updates given fields of an existing sysDepartRole, field will ignore if it is null
     *
     * @param id the id of the sysDepartRoleDTO to save.
     * @param sysDepartRoleDTO the sysDepartRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysDepartRoleDTO,
     * or with status {@code 400 (Bad Request)} if the sysDepartRoleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysDepartRoleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysDepartRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-depart-roles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysDepartRoleDTO> partialUpdateSysDepartRole(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysDepartRoleDTO sysDepartRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysDepartRole partially : {}, {}", id, sysDepartRoleDTO);
        if (sysDepartRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysDepartRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysDepartRoleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysDepartRoleDTO> result = sysDepartRoleService.partialUpdate(sysDepartRoleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysDepartRoleDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-depart-roles} : get all the sysDepartRoles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysDepartRoles in body.
     */
    @GetMapping("/sys-depart-roles")
    public ResponseEntity<List<SysDepartRoleDTO>> getAllSysDepartRoles(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysDepartRoles");
        Page<SysDepartRoleDTO> page = sysDepartRoleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-depart-roles/:id} : get the "id" sysDepartRole.
     *
     * @param id the id of the sysDepartRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysDepartRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-depart-roles/{id}")
    public ResponseEntity<SysDepartRoleDTO> getSysDepartRole(@PathVariable String id) {
        log.debug("REST request to get SysDepartRole : {}", id);
        Optional<SysDepartRoleDTO> sysDepartRoleDTO = sysDepartRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysDepartRoleDTO);
    }

    /**
     * {@code DELETE  /sys-depart-roles/:id} : delete the "id" sysDepartRole.
     *
     * @param id the id of the sysDepartRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-depart-roles/{id}")
    public ResponseEntity<Void> deleteSysDepartRole(@PathVariable String id) {
        log.debug("REST request to delete SysDepartRole : {}", id);
        sysDepartRoleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
