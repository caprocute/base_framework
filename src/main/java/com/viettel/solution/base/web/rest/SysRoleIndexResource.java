package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysRoleIndexRepository;
import com.viettel.solution.base.service.SysRoleIndexService;
import com.viettel.solution.base.service.dto.SysRoleIndexDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysRoleIndex}.
 */
@RestController
@RequestMapping("/api")
public class SysRoleIndexResource {

    private final Logger log = LoggerFactory.getLogger(SysRoleIndexResource.class);

    private static final String ENTITY_NAME = "sysRoleIndex";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRoleIndexService sysRoleIndexService;

    private final SysRoleIndexRepository sysRoleIndexRepository;

    public SysRoleIndexResource(SysRoleIndexService sysRoleIndexService, SysRoleIndexRepository sysRoleIndexRepository) {
        this.sysRoleIndexService = sysRoleIndexService;
        this.sysRoleIndexRepository = sysRoleIndexRepository;
    }

    /**
     * {@code POST  /sys-role-indices} : Create a new sysRoleIndex.
     *
     * @param sysRoleIndexDTO the sysRoleIndexDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRoleIndexDTO, or with status {@code 400 (Bad Request)} if the sysRoleIndex has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-role-indices")
    public ResponseEntity<SysRoleIndexDTO> createSysRoleIndex(@Valid @RequestBody SysRoleIndexDTO sysRoleIndexDTO)
        throws URISyntaxException {
        log.debug("REST request to save SysRoleIndex : {}", sysRoleIndexDTO);
        if (sysRoleIndexDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysRoleIndex cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRoleIndexDTO result = sysRoleIndexService.save(sysRoleIndexDTO);
        return ResponseEntity
            .created(new URI("/api/sys-role-indices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-role-indices/:id} : Updates an existing sysRoleIndex.
     *
     * @param id the id of the sysRoleIndexDTO to save.
     * @param sysRoleIndexDTO the sysRoleIndexDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRoleIndexDTO,
     * or with status {@code 400 (Bad Request)} if the sysRoleIndexDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRoleIndexDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-role-indices/{id}")
    public ResponseEntity<SysRoleIndexDTO> updateSysRoleIndex(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysRoleIndexDTO sysRoleIndexDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysRoleIndex : {}, {}", id, sysRoleIndexDTO);
        if (sysRoleIndexDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysRoleIndexDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysRoleIndexRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysRoleIndexDTO result = sysRoleIndexService.update(sysRoleIndexDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysRoleIndexDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-role-indices/:id} : Partial updates given fields of an existing sysRoleIndex, field will ignore if it is null
     *
     * @param id the id of the sysRoleIndexDTO to save.
     * @param sysRoleIndexDTO the sysRoleIndexDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRoleIndexDTO,
     * or with status {@code 400 (Bad Request)} if the sysRoleIndexDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysRoleIndexDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysRoleIndexDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-role-indices/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysRoleIndexDTO> partialUpdateSysRoleIndex(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysRoleIndexDTO sysRoleIndexDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysRoleIndex partially : {}, {}", id, sysRoleIndexDTO);
        if (sysRoleIndexDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysRoleIndexDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysRoleIndexRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysRoleIndexDTO> result = sysRoleIndexService.partialUpdate(sysRoleIndexDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysRoleIndexDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-role-indices} : get all the sysRoleIndices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRoleIndices in body.
     */
    @GetMapping("/sys-role-indices")
    public ResponseEntity<List<SysRoleIndexDTO>> getAllSysRoleIndices(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysRoleIndices");
        Page<SysRoleIndexDTO> page = sysRoleIndexService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-role-indices/:id} : get the "id" sysRoleIndex.
     *
     * @param id the id of the sysRoleIndexDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRoleIndexDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-role-indices/{id}")
    public ResponseEntity<SysRoleIndexDTO> getSysRoleIndex(@PathVariable String id) {
        log.debug("REST request to get SysRoleIndex : {}", id);
        Optional<SysRoleIndexDTO> sysRoleIndexDTO = sysRoleIndexService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRoleIndexDTO);
    }

    /**
     * {@code DELETE  /sys-role-indices/:id} : delete the "id" sysRoleIndex.
     *
     * @param id the id of the sysRoleIndexDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-role-indices/{id}")
    public ResponseEntity<Void> deleteSysRoleIndex(@PathVariable String id) {
        log.debug("REST request to delete SysRoleIndex : {}", id);
        sysRoleIndexService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
