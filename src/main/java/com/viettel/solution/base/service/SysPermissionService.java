package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysPermissionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysPermission}.
 */
public interface SysPermissionService {
    /**
     * Save a sysPermission.
     *
     * @param sysPermissionDTO the entity to save.
     * @return the persisted entity.
     */
    SysPermissionDTO save(SysPermissionDTO sysPermissionDTO);

    /**
     * Updates a sysPermission.
     *
     * @param sysPermissionDTO the entity to update.
     * @return the persisted entity.
     */
    SysPermissionDTO update(SysPermissionDTO sysPermissionDTO);

    /**
     * Partially updates a sysPermission.
     *
     * @param sysPermissionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysPermissionDTO> partialUpdate(SysPermissionDTO sysPermissionDTO);

    /**
     * Get all the sysPermissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysPermissionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysPermission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysPermissionDTO> findOne(String id);

    /**
     * Delete the "id" sysPermission.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
