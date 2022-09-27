package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysUserDepartRepository;
import com.viettel.solution.base.service.SysUserDepartService;
import com.viettel.solution.base.service.dto.SysUserDepartDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysUserDepart}.
 */
@RestController
@RequestMapping("/api")
public class SysUserDepartResource {

    private final Logger log = LoggerFactory.getLogger(SysUserDepartResource.class);

    private static final String ENTITY_NAME = "sysUserDepart";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysUserDepartService sysUserDepartService;

    private final SysUserDepartRepository sysUserDepartRepository;

    public SysUserDepartResource(SysUserDepartService sysUserDepartService, SysUserDepartRepository sysUserDepartRepository) {
        this.sysUserDepartService = sysUserDepartService;
        this.sysUserDepartRepository = sysUserDepartRepository;
    }

    /**
     * {@code POST  /sys-user-departs} : Create a new sysUserDepart.
     *
     * @param sysUserDepartDTO the sysUserDepartDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysUserDepartDTO, or with status {@code 400 (Bad Request)} if the sysUserDepart has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-user-departs")
    public ResponseEntity<SysUserDepartDTO> createSysUserDepart(@Valid @RequestBody SysUserDepartDTO sysUserDepartDTO)
        throws URISyntaxException {
        log.debug("REST request to save SysUserDepart : {}", sysUserDepartDTO);
        if (sysUserDepartDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysUserDepart cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysUserDepartDTO result = sysUserDepartService.save(sysUserDepartDTO);
        return ResponseEntity
            .created(new URI("/api/sys-user-departs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-user-departs/:id} : Updates an existing sysUserDepart.
     *
     * @param id the id of the sysUserDepartDTO to save.
     * @param sysUserDepartDTO the sysUserDepartDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysUserDepartDTO,
     * or with status {@code 400 (Bad Request)} if the sysUserDepartDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysUserDepartDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-user-departs/{id}")
    public ResponseEntity<SysUserDepartDTO> updateSysUserDepart(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysUserDepartDTO sysUserDepartDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysUserDepart : {}, {}", id, sysUserDepartDTO);
        if (sysUserDepartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysUserDepartDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysUserDepartRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysUserDepartDTO result = sysUserDepartService.update(sysUserDepartDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysUserDepartDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-user-departs/:id} : Partial updates given fields of an existing sysUserDepart, field will ignore if it is null
     *
     * @param id the id of the sysUserDepartDTO to save.
     * @param sysUserDepartDTO the sysUserDepartDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysUserDepartDTO,
     * or with status {@code 400 (Bad Request)} if the sysUserDepartDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysUserDepartDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysUserDepartDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-user-departs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysUserDepartDTO> partialUpdateSysUserDepart(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysUserDepartDTO sysUserDepartDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysUserDepart partially : {}, {}", id, sysUserDepartDTO);
        if (sysUserDepartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysUserDepartDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysUserDepartRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysUserDepartDTO> result = sysUserDepartService.partialUpdate(sysUserDepartDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysUserDepartDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-user-departs} : get all the sysUserDeparts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysUserDeparts in body.
     */
    @GetMapping("/sys-user-departs")
    public ResponseEntity<List<SysUserDepartDTO>> getAllSysUserDeparts(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysUserDeparts");
        Page<SysUserDepartDTO> page = sysUserDepartService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-user-departs/:id} : get the "id" sysUserDepart.
     *
     * @param id the id of the sysUserDepartDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysUserDepartDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-user-departs/{id}")
    public ResponseEntity<SysUserDepartDTO> getSysUserDepart(@PathVariable String id) {
        log.debug("REST request to get SysUserDepart : {}", id);
        Optional<SysUserDepartDTO> sysUserDepartDTO = sysUserDepartService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysUserDepartDTO);
    }

    /**
     * {@code DELETE  /sys-user-departs/:id} : delete the "id" sysUserDepart.
     *
     * @param id the id of the sysUserDepartDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-user-departs/{id}")
    public ResponseEntity<Void> deleteSysUserDepart(@PathVariable String id) {
        log.debug("REST request to delete SysUserDepart : {}", id);
        sysUserDepartService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
