package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysTenantRepository;
import com.viettel.solution.base.service.SysTenantService;
import com.viettel.solution.base.service.dto.SysTenantDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysTenant}.
 */
@RestController
@RequestMapping("/api")
public class SysTenantResource {

    private final Logger log = LoggerFactory.getLogger(SysTenantResource.class);

    private static final String ENTITY_NAME = "sysTenant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysTenantService sysTenantService;

    private final SysTenantRepository sysTenantRepository;

    public SysTenantResource(SysTenantService sysTenantService, SysTenantRepository sysTenantRepository) {
        this.sysTenantService = sysTenantService;
        this.sysTenantRepository = sysTenantRepository;
    }

    /**
     * {@code POST  /sys-tenants} : Create a new sysTenant.
     *
     * @param sysTenantDTO the sysTenantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysTenantDTO, or with status {@code 400 (Bad Request)} if the sysTenant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-tenants")
    public ResponseEntity<SysTenantDTO> createSysTenant(@Valid @RequestBody SysTenantDTO sysTenantDTO) throws URISyntaxException {
        log.debug("REST request to save SysTenant : {}", sysTenantDTO);
        if (sysTenantDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysTenant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysTenantDTO result = sysTenantService.save(sysTenantDTO);
        return ResponseEntity
            .created(new URI("/api/sys-tenants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-tenants/:id} : Updates an existing sysTenant.
     *
     * @param id the id of the sysTenantDTO to save.
     * @param sysTenantDTO the sysTenantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysTenantDTO,
     * or with status {@code 400 (Bad Request)} if the sysTenantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysTenantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-tenants/{id}")
    public ResponseEntity<SysTenantDTO> updateSysTenant(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysTenantDTO sysTenantDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysTenant : {}, {}", id, sysTenantDTO);
        if (sysTenantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysTenantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysTenantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysTenantDTO result = sysTenantService.update(sysTenantDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysTenantDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-tenants/:id} : Partial updates given fields of an existing sysTenant, field will ignore if it is null
     *
     * @param id the id of the sysTenantDTO to save.
     * @param sysTenantDTO the sysTenantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysTenantDTO,
     * or with status {@code 400 (Bad Request)} if the sysTenantDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysTenantDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysTenantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-tenants/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysTenantDTO> partialUpdateSysTenant(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysTenantDTO sysTenantDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysTenant partially : {}, {}", id, sysTenantDTO);
        if (sysTenantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysTenantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysTenantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysTenantDTO> result = sysTenantService.partialUpdate(sysTenantDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysTenantDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-tenants} : get all the sysTenants.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysTenants in body.
     */
    @GetMapping("/sys-tenants")
    public ResponseEntity<List<SysTenantDTO>> getAllSysTenants(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysTenants");
        Page<SysTenantDTO> page = sysTenantService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-tenants/:id} : get the "id" sysTenant.
     *
     * @param id the id of the sysTenantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysTenantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-tenants/{id}")
    public ResponseEntity<SysTenantDTO> getSysTenant(@PathVariable String id) {
        log.debug("REST request to get SysTenant : {}", id);
        Optional<SysTenantDTO> sysTenantDTO = sysTenantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysTenantDTO);
    }

    /**
     * {@code DELETE  /sys-tenants/:id} : delete the "id" sysTenant.
     *
     * @param id the id of the sysTenantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-tenants/{id}")
    public ResponseEntity<Void> deleteSysTenant(@PathVariable String id) {
        log.debug("REST request to delete SysTenant : {}", id);
        sysTenantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
