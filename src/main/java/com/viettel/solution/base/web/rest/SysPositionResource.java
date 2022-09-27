package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysPositionRepository;
import com.viettel.solution.base.service.SysPositionService;
import com.viettel.solution.base.service.dto.SysPositionDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysPosition}.
 */
@RestController
@RequestMapping("/api")
public class SysPositionResource {

    private final Logger log = LoggerFactory.getLogger(SysPositionResource.class);

    private static final String ENTITY_NAME = "sysPosition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysPositionService sysPositionService;

    private final SysPositionRepository sysPositionRepository;

    public SysPositionResource(SysPositionService sysPositionService, SysPositionRepository sysPositionRepository) {
        this.sysPositionService = sysPositionService;
        this.sysPositionRepository = sysPositionRepository;
    }

    /**
     * {@code POST  /sys-positions} : Create a new sysPosition.
     *
     * @param sysPositionDTO the sysPositionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysPositionDTO, or with status {@code 400 (Bad Request)} if the sysPosition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-positions")
    public ResponseEntity<SysPositionDTO> createSysPosition(@Valid @RequestBody SysPositionDTO sysPositionDTO) throws URISyntaxException {
        log.debug("REST request to save SysPosition : {}", sysPositionDTO);
        if (sysPositionDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysPosition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysPositionDTO result = sysPositionService.save(sysPositionDTO);
        return ResponseEntity
            .created(new URI("/api/sys-positions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-positions/:id} : Updates an existing sysPosition.
     *
     * @param id the id of the sysPositionDTO to save.
     * @param sysPositionDTO the sysPositionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysPositionDTO,
     * or with status {@code 400 (Bad Request)} if the sysPositionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysPositionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-positions/{id}")
    public ResponseEntity<SysPositionDTO> updateSysPosition(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysPositionDTO sysPositionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysPosition : {}, {}", id, sysPositionDTO);
        if (sysPositionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysPositionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysPositionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysPositionDTO result = sysPositionService.update(sysPositionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysPositionDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-positions/:id} : Partial updates given fields of an existing sysPosition, field will ignore if it is null
     *
     * @param id the id of the sysPositionDTO to save.
     * @param sysPositionDTO the sysPositionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysPositionDTO,
     * or with status {@code 400 (Bad Request)} if the sysPositionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysPositionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysPositionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-positions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysPositionDTO> partialUpdateSysPosition(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysPositionDTO sysPositionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysPosition partially : {}, {}", id, sysPositionDTO);
        if (sysPositionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysPositionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysPositionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysPositionDTO> result = sysPositionService.partialUpdate(sysPositionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysPositionDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-positions} : get all the sysPositions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysPositions in body.
     */
    @GetMapping("/sys-positions")
    public ResponseEntity<List<SysPositionDTO>> getAllSysPositions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysPositions");
        Page<SysPositionDTO> page = sysPositionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-positions/:id} : get the "id" sysPosition.
     *
     * @param id the id of the sysPositionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysPositionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-positions/{id}")
    public ResponseEntity<SysPositionDTO> getSysPosition(@PathVariable String id) {
        log.debug("REST request to get SysPosition : {}", id);
        Optional<SysPositionDTO> sysPositionDTO = sysPositionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysPositionDTO);
    }

    /**
     * {@code DELETE  /sys-positions/:id} : delete the "id" sysPosition.
     *
     * @param id the id of the sysPositionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-positions/{id}")
    public ResponseEntity<Void> deleteSysPosition(@PathVariable String id) {
        log.debug("REST request to delete SysPosition : {}", id);
        sysPositionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
