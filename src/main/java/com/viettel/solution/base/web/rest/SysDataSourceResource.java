package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysDataSourceRepository;
import com.viettel.solution.base.service.SysDataSourceService;
import com.viettel.solution.base.service.dto.SysDataSourceDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysDataSource}.
 */
@RestController
@RequestMapping("/api")
public class SysDataSourceResource {

    private final Logger log = LoggerFactory.getLogger(SysDataSourceResource.class);

    private static final String ENTITY_NAME = "sysDataSource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysDataSourceService sysDataSourceService;

    private final SysDataSourceRepository sysDataSourceRepository;

    public SysDataSourceResource(SysDataSourceService sysDataSourceService, SysDataSourceRepository sysDataSourceRepository) {
        this.sysDataSourceService = sysDataSourceService;
        this.sysDataSourceRepository = sysDataSourceRepository;
    }

    /**
     * {@code POST  /sys-data-sources} : Create a new sysDataSource.
     *
     * @param sysDataSourceDTO the sysDataSourceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysDataSourceDTO, or with status {@code 400 (Bad Request)} if the sysDataSource has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-data-sources")
    public ResponseEntity<SysDataSourceDTO> createSysDataSource(@Valid @RequestBody SysDataSourceDTO sysDataSourceDTO)
        throws URISyntaxException {
        log.debug("REST request to save SysDataSource : {}", sysDataSourceDTO);
        if (sysDataSourceDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysDataSource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysDataSourceDTO result = sysDataSourceService.save(sysDataSourceDTO);
        return ResponseEntity
            .created(new URI("/api/sys-data-sources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-data-sources/:id} : Updates an existing sysDataSource.
     *
     * @param id the id of the sysDataSourceDTO to save.
     * @param sysDataSourceDTO the sysDataSourceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysDataSourceDTO,
     * or with status {@code 400 (Bad Request)} if the sysDataSourceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysDataSourceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-data-sources/{id}")
    public ResponseEntity<SysDataSourceDTO> updateSysDataSource(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysDataSourceDTO sysDataSourceDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysDataSource : {}, {}", id, sysDataSourceDTO);
        if (sysDataSourceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysDataSourceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysDataSourceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysDataSourceDTO result = sysDataSourceService.update(sysDataSourceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysDataSourceDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-data-sources/:id} : Partial updates given fields of an existing sysDataSource, field will ignore if it is null
     *
     * @param id the id of the sysDataSourceDTO to save.
     * @param sysDataSourceDTO the sysDataSourceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysDataSourceDTO,
     * or with status {@code 400 (Bad Request)} if the sysDataSourceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysDataSourceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysDataSourceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-data-sources/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysDataSourceDTO> partialUpdateSysDataSource(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysDataSourceDTO sysDataSourceDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysDataSource partially : {}, {}", id, sysDataSourceDTO);
        if (sysDataSourceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysDataSourceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysDataSourceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysDataSourceDTO> result = sysDataSourceService.partialUpdate(sysDataSourceDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysDataSourceDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-data-sources} : get all the sysDataSources.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysDataSources in body.
     */
    @GetMapping("/sys-data-sources")
    public ResponseEntity<List<SysDataSourceDTO>> getAllSysDataSources(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysDataSources");
        Page<SysDataSourceDTO> page = sysDataSourceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-data-sources/:id} : get the "id" sysDataSource.
     *
     * @param id the id of the sysDataSourceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysDataSourceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-data-sources/{id}")
    public ResponseEntity<SysDataSourceDTO> getSysDataSource(@PathVariable String id) {
        log.debug("REST request to get SysDataSource : {}", id);
        Optional<SysDataSourceDTO> sysDataSourceDTO = sysDataSourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysDataSourceDTO);
    }

    /**
     * {@code DELETE  /sys-data-sources/:id} : delete the "id" sysDataSource.
     *
     * @param id the id of the sysDataSourceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-data-sources/{id}")
    public ResponseEntity<Void> deleteSysDataSource(@PathVariable String id) {
        log.debug("REST request to delete SysDataSource : {}", id);
        sysDataSourceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
