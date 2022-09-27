package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysDepartRepository;
import com.viettel.solution.base.service.SysDepartService;
import com.viettel.solution.base.service.dto.SysDepartDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysDepart}.
 */
@RestController
@RequestMapping("/api")
public class SysDepartResource {

    private final Logger log = LoggerFactory.getLogger(SysDepartResource.class);

    private static final String ENTITY_NAME = "sysDepart";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysDepartService sysDepartService;

    private final SysDepartRepository sysDepartRepository;

    public SysDepartResource(SysDepartService sysDepartService, SysDepartRepository sysDepartRepository) {
        this.sysDepartService = sysDepartService;
        this.sysDepartRepository = sysDepartRepository;
    }

    /**
     * {@code POST  /sys-departs} : Create a new sysDepart.
     *
     * @param sysDepartDTO the sysDepartDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysDepartDTO, or with status {@code 400 (Bad Request)} if the sysDepart has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-departs")
    public ResponseEntity<SysDepartDTO> createSysDepart(@Valid @RequestBody SysDepartDTO sysDepartDTO) throws URISyntaxException {
        log.debug("REST request to save SysDepart : {}", sysDepartDTO);
        if (sysDepartDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysDepart cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysDepartDTO result = sysDepartService.save(sysDepartDTO);
        return ResponseEntity
            .created(new URI("/api/sys-departs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-departs/:id} : Updates an existing sysDepart.
     *
     * @param id the id of the sysDepartDTO to save.
     * @param sysDepartDTO the sysDepartDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysDepartDTO,
     * or with status {@code 400 (Bad Request)} if the sysDepartDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysDepartDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-departs/{id}")
    public ResponseEntity<SysDepartDTO> updateSysDepart(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysDepartDTO sysDepartDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysDepart : {}, {}", id, sysDepartDTO);
        if (sysDepartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysDepartDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysDepartRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysDepartDTO result = sysDepartService.update(sysDepartDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysDepartDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-departs/:id} : Partial updates given fields of an existing sysDepart, field will ignore if it is null
     *
     * @param id the id of the sysDepartDTO to save.
     * @param sysDepartDTO the sysDepartDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysDepartDTO,
     * or with status {@code 400 (Bad Request)} if the sysDepartDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysDepartDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysDepartDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-departs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysDepartDTO> partialUpdateSysDepart(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysDepartDTO sysDepartDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysDepart partially : {}, {}", id, sysDepartDTO);
        if (sysDepartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysDepartDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysDepartRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysDepartDTO> result = sysDepartService.partialUpdate(sysDepartDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysDepartDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-departs} : get all the sysDeparts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysDeparts in body.
     */
    @GetMapping("/sys-departs")
    public ResponseEntity<List<SysDepartDTO>> getAllSysDeparts(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysDeparts");
        Page<SysDepartDTO> page = sysDepartService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-departs/:id} : get the "id" sysDepart.
     *
     * @param id the id of the sysDepartDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysDepartDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-departs/{id}")
    public ResponseEntity<SysDepartDTO> getSysDepart(@PathVariable String id) {
        log.debug("REST request to get SysDepart : {}", id);
        Optional<SysDepartDTO> sysDepartDTO = sysDepartService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysDepartDTO);
    }

    /**
     * {@code DELETE  /sys-departs/:id} : delete the "id" sysDepart.
     *
     * @param id the id of the sysDepartDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-departs/{id}")
    public ResponseEntity<Void> deleteSysDepart(@PathVariable String id) {
        log.debug("REST request to delete SysDepart : {}", id);
        sysDepartService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
