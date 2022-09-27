package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysDataLogRepository;
import com.viettel.solution.base.service.SysDataLogService;
import com.viettel.solution.base.service.dto.SysDataLogDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysDataLog}.
 */
@RestController
@RequestMapping("/api")
public class SysDataLogResource {

    private final Logger log = LoggerFactory.getLogger(SysDataLogResource.class);

    private static final String ENTITY_NAME = "sysDataLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysDataLogService sysDataLogService;

    private final SysDataLogRepository sysDataLogRepository;

    public SysDataLogResource(SysDataLogService sysDataLogService, SysDataLogRepository sysDataLogRepository) {
        this.sysDataLogService = sysDataLogService;
        this.sysDataLogRepository = sysDataLogRepository;
    }

    /**
     * {@code POST  /sys-data-logs} : Create a new sysDataLog.
     *
     * @param sysDataLogDTO the sysDataLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysDataLogDTO, or with status {@code 400 (Bad Request)} if the sysDataLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-data-logs")
    public ResponseEntity<SysDataLogDTO> createSysDataLog(@Valid @RequestBody SysDataLogDTO sysDataLogDTO) throws URISyntaxException {
        log.debug("REST request to save SysDataLog : {}", sysDataLogDTO);
        if (sysDataLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysDataLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysDataLogDTO result = sysDataLogService.save(sysDataLogDTO);
        return ResponseEntity
            .created(new URI("/api/sys-data-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-data-logs/:id} : Updates an existing sysDataLog.
     *
     * @param id the id of the sysDataLogDTO to save.
     * @param sysDataLogDTO the sysDataLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysDataLogDTO,
     * or with status {@code 400 (Bad Request)} if the sysDataLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysDataLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-data-logs/{id}")
    public ResponseEntity<SysDataLogDTO> updateSysDataLog(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysDataLogDTO sysDataLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysDataLog : {}, {}", id, sysDataLogDTO);
        if (sysDataLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysDataLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysDataLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysDataLogDTO result = sysDataLogService.update(sysDataLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysDataLogDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-data-logs/:id} : Partial updates given fields of an existing sysDataLog, field will ignore if it is null
     *
     * @param id the id of the sysDataLogDTO to save.
     * @param sysDataLogDTO the sysDataLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysDataLogDTO,
     * or with status {@code 400 (Bad Request)} if the sysDataLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysDataLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysDataLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-data-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysDataLogDTO> partialUpdateSysDataLog(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysDataLogDTO sysDataLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysDataLog partially : {}, {}", id, sysDataLogDTO);
        if (sysDataLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysDataLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysDataLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysDataLogDTO> result = sysDataLogService.partialUpdate(sysDataLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysDataLogDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-data-logs} : get all the sysDataLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysDataLogs in body.
     */
    @GetMapping("/sys-data-logs")
    public ResponseEntity<List<SysDataLogDTO>> getAllSysDataLogs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysDataLogs");
        Page<SysDataLogDTO> page = sysDataLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-data-logs/:id} : get the "id" sysDataLog.
     *
     * @param id the id of the sysDataLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysDataLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-data-logs/{id}")
    public ResponseEntity<SysDataLogDTO> getSysDataLog(@PathVariable String id) {
        log.debug("REST request to get SysDataLog : {}", id);
        Optional<SysDataLogDTO> sysDataLogDTO = sysDataLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysDataLogDTO);
    }

    /**
     * {@code DELETE  /sys-data-logs/:id} : delete the "id" sysDataLog.
     *
     * @param id the id of the sysDataLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-data-logs/{id}")
    public ResponseEntity<Void> deleteSysDataLog(@PathVariable String id) {
        log.debug("REST request to delete SysDataLog : {}", id);
        sysDataLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
