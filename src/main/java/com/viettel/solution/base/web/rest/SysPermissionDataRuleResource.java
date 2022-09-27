package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysPermissionDataRuleRepository;
import com.viettel.solution.base.service.SysPermissionDataRuleService;
import com.viettel.solution.base.service.dto.SysPermissionDataRuleDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysPermissionDataRule}.
 */
@RestController
@RequestMapping("/api")
public class SysPermissionDataRuleResource {

    private final Logger log = LoggerFactory.getLogger(SysPermissionDataRuleResource.class);

    private static final String ENTITY_NAME = "sysPermissionDataRule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysPermissionDataRuleService sysPermissionDataRuleService;

    private final SysPermissionDataRuleRepository sysPermissionDataRuleRepository;

    public SysPermissionDataRuleResource(
        SysPermissionDataRuleService sysPermissionDataRuleService,
        SysPermissionDataRuleRepository sysPermissionDataRuleRepository
    ) {
        this.sysPermissionDataRuleService = sysPermissionDataRuleService;
        this.sysPermissionDataRuleRepository = sysPermissionDataRuleRepository;
    }

    /**
     * {@code POST  /sys-permission-data-rules} : Create a new sysPermissionDataRule.
     *
     * @param sysPermissionDataRuleDTO the sysPermissionDataRuleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysPermissionDataRuleDTO, or with status {@code 400 (Bad Request)} if the sysPermissionDataRule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-permission-data-rules")
    public ResponseEntity<SysPermissionDataRuleDTO> createSysPermissionDataRule(
        @Valid @RequestBody SysPermissionDataRuleDTO sysPermissionDataRuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SysPermissionDataRule : {}", sysPermissionDataRuleDTO);
        if (sysPermissionDataRuleDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysPermissionDataRule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysPermissionDataRuleDTO result = sysPermissionDataRuleService.save(sysPermissionDataRuleDTO);
        return ResponseEntity
            .created(new URI("/api/sys-permission-data-rules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-permission-data-rules/:id} : Updates an existing sysPermissionDataRule.
     *
     * @param id the id of the sysPermissionDataRuleDTO to save.
     * @param sysPermissionDataRuleDTO the sysPermissionDataRuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysPermissionDataRuleDTO,
     * or with status {@code 400 (Bad Request)} if the sysPermissionDataRuleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysPermissionDataRuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-permission-data-rules/{id}")
    public ResponseEntity<SysPermissionDataRuleDTO> updateSysPermissionDataRule(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysPermissionDataRuleDTO sysPermissionDataRuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysPermissionDataRule : {}, {}", id, sysPermissionDataRuleDTO);
        if (sysPermissionDataRuleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysPermissionDataRuleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysPermissionDataRuleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysPermissionDataRuleDTO result = sysPermissionDataRuleService.update(sysPermissionDataRuleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysPermissionDataRuleDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-permission-data-rules/:id} : Partial updates given fields of an existing sysPermissionDataRule, field will ignore if it is null
     *
     * @param id the id of the sysPermissionDataRuleDTO to save.
     * @param sysPermissionDataRuleDTO the sysPermissionDataRuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysPermissionDataRuleDTO,
     * or with status {@code 400 (Bad Request)} if the sysPermissionDataRuleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysPermissionDataRuleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysPermissionDataRuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-permission-data-rules/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysPermissionDataRuleDTO> partialUpdateSysPermissionDataRule(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysPermissionDataRuleDTO sysPermissionDataRuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysPermissionDataRule partially : {}, {}", id, sysPermissionDataRuleDTO);
        if (sysPermissionDataRuleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysPermissionDataRuleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysPermissionDataRuleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysPermissionDataRuleDTO> result = sysPermissionDataRuleService.partialUpdate(sysPermissionDataRuleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysPermissionDataRuleDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-permission-data-rules} : get all the sysPermissionDataRules.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysPermissionDataRules in body.
     */
    @GetMapping("/sys-permission-data-rules")
    public ResponseEntity<List<SysPermissionDataRuleDTO>> getAllSysPermissionDataRules(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of SysPermissionDataRules");
        Page<SysPermissionDataRuleDTO> page = sysPermissionDataRuleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-permission-data-rules/:id} : get the "id" sysPermissionDataRule.
     *
     * @param id the id of the sysPermissionDataRuleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysPermissionDataRuleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-permission-data-rules/{id}")
    public ResponseEntity<SysPermissionDataRuleDTO> getSysPermissionDataRule(@PathVariable String id) {
        log.debug("REST request to get SysPermissionDataRule : {}", id);
        Optional<SysPermissionDataRuleDTO> sysPermissionDataRuleDTO = sysPermissionDataRuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysPermissionDataRuleDTO);
    }

    /**
     * {@code DELETE  /sys-permission-data-rules/:id} : delete the "id" sysPermissionDataRule.
     *
     * @param id the id of the sysPermissionDataRuleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-permission-data-rules/{id}")
    public ResponseEntity<Void> deleteSysPermissionDataRule(@PathVariable String id) {
        log.debug("REST request to delete SysPermissionDataRule : {}", id);
        sysPermissionDataRuleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
