package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysLogRepository;
import com.viettel.solution.base.service.SysLogService;
import com.viettel.solution.base.service.dto.SysLogDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysLog}.
 */
@RestController
@RequestMapping("/api")
public class SysLogResource {

    private final Logger log = LoggerFactory.getLogger(SysLogResource.class);

    private static final String ENTITY_NAME = "sysLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysLogService sysLogService;

    private final SysLogRepository sysLogRepository;

    public SysLogResource(SysLogService sysLogService, SysLogRepository sysLogRepository) {
        this.sysLogService = sysLogService;
        this.sysLogRepository = sysLogRepository;
    }

    /**
     * {@code POST  /sys-logs} : Create a new sysLog.
     *
     * @param sysLogDTO the sysLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysLogDTO, or with status {@code 400 (Bad Request)} if the sysLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-logs")
    public ResponseEntity<SysLogDTO> createSysLog(@Valid @RequestBody SysLogDTO sysLogDTO) throws URISyntaxException {
        log.debug("REST request to save SysLog : {}", sysLogDTO);
        if (sysLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysLogDTO result = sysLogService.save(sysLogDTO);
        return ResponseEntity
            .created(new URI("/api/sys-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-logs/:id} : Updates an existing sysLog.
     *
     * @param id the id of the sysLogDTO to save.
     * @param sysLogDTO the sysLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysLogDTO,
     * or with status {@code 400 (Bad Request)} if the sysLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-logs/{id}")
    public ResponseEntity<SysLogDTO> updateSysLog(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysLogDTO sysLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysLog : {}, {}", id, sysLogDTO);
        if (sysLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysLogDTO result = sysLogService.update(sysLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysLogDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-logs/:id} : Partial updates given fields of an existing sysLog, field will ignore if it is null
     *
     * @param id the id of the sysLogDTO to save.
     * @param sysLogDTO the sysLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysLogDTO,
     * or with status {@code 400 (Bad Request)} if the sysLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysLogDTO> partialUpdateSysLog(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysLogDTO sysLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysLog partially : {}, {}", id, sysLogDTO);
        if (sysLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysLogDTO> result = sysLogService.partialUpdate(sysLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysLogDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-logs} : get all the sysLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysLogs in body.
     */
    @GetMapping("/sys-logs")
    public ResponseEntity<List<SysLogDTO>> getAllSysLogs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysLogs");
        Page<SysLogDTO> page = sysLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-logs/:id} : get the "id" sysLog.
     *
     * @param id the id of the sysLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-logs/{id}")
    public ResponseEntity<SysLogDTO> getSysLog(@PathVariable String id) {
        log.debug("REST request to get SysLog : {}", id);
        Optional<SysLogDTO> sysLogDTO = sysLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysLogDTO);
    }

    /**
     * {@code DELETE  /sys-logs/:id} : delete the "id" sysLog.
     *
     * @param id the id of the sysLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-logs/{id}")
    public ResponseEntity<Void> deleteSysLog(@PathVariable String id) {
        log.debug("REST request to delete SysLog : {}", id);
        sysLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
