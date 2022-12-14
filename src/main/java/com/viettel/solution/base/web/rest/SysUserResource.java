package com.viettel.solution.base.web.rest;

import com.viettel.solution.base.repository.SysUserRepository;
import com.viettel.solution.base.service.SysUserService;
import com.viettel.solution.base.service.dto.SysUserDTO;
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
 * REST controller for managing {@link com.viettel.solution.base.domain.SysUser}.
 */
@RestController
@RequestMapping("/api")
public class SysUserResource {

    private final Logger log = LoggerFactory.getLogger(SysUserResource.class);

    private static final String ENTITY_NAME = "sysUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysUserService sysUserService;

    private final SysUserRepository sysUserRepository;

    public SysUserResource(SysUserService sysUserService, SysUserRepository sysUserRepository) {
        this.sysUserService = sysUserService;
        this.sysUserRepository = sysUserRepository;
    }

    /**
     * {@code POST  /sys-users} : Create a new sysUser.
     *
     * @param sysUserDTO the sysUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysUserDTO, or with status {@code 400 (Bad Request)} if the sysUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-users")
    public ResponseEntity<SysUserDTO> createSysUser(@Valid @RequestBody SysUserDTO sysUserDTO) throws URISyntaxException {
        log.debug("REST request to save SysUser : {}", sysUserDTO);
        if (sysUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysUserDTO result = sysUserService.save(sysUserDTO);
        return ResponseEntity
            .created(new URI("/api/sys-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-users/:id} : Updates an existing sysUser.
     *
     * @param id the id of the sysUserDTO to save.
     * @param sysUserDTO the sysUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysUserDTO,
     * or with status {@code 400 (Bad Request)} if the sysUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-users/{id}")
    public ResponseEntity<SysUserDTO> updateSysUser(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SysUserDTO sysUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysUser : {}, {}", id, sysUserDTO);
        if (sysUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysUserDTO result = sysUserService.update(sysUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysUserDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-users/:id} : Partial updates given fields of an existing sysUser, field will ignore if it is null
     *
     * @param id the id of the sysUserDTO to save.
     * @param sysUserDTO the sysUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysUserDTO,
     * or with status {@code 400 (Bad Request)} if the sysUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysUserDTO> partialUpdateSysUser(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SysUserDTO sysUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysUser partially : {}, {}", id, sysUserDTO);
        if (sysUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysUserDTO> result = sysUserService.partialUpdate(sysUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysUserDTO.getId())
        );
    }

    /**
     * {@code GET  /sys-users} : get all the sysUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysUsers in body.
     */
    @GetMapping("/sys-users")
    public ResponseEntity<List<SysUserDTO>> getAllSysUsers(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysUsers");
        Page<SysUserDTO> page = sysUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-users/:id} : get the "id" sysUser.
     *
     * @param id the id of the sysUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-users/{id}")
    public ResponseEntity<SysUserDTO> getSysUser(@PathVariable String id) {
        log.debug("REST request to get SysUser : {}", id);
        Optional<SysUserDTO> sysUserDTO = sysUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysUserDTO);
    }

    /**
     * {@code DELETE  /sys-users/:id} : delete the "id" sysUser.
     *
     * @param id the id of the sysUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-users/{id}")
    public ResponseEntity<Void> deleteSysUser(@PathVariable String id) {
        log.debug("REST request to delete SysUser : {}", id);
        sysUserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
