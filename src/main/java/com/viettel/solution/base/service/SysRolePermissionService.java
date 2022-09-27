package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysRolePermissionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysRolePermission}.
 */
public interface SysRolePermissionService {
    /**
     * Save a sysRolePermission.
     *
     * @param sysRolePermissionDTO the entity to save.
     * @return the persisted entity.
     */
    SysRolePermissionDTO save(SysRolePermissionDTO sysRolePermissionDTO);

    /**
     * Updates a sysRolePermission.
     *
     * @param sysRolePermissionDTO the entity to update.
     * @return the persisted entity.
     */
    SysRolePermissionDTO update(SysRolePermissionDTO sysRolePermissionDTO);

    /**
     * Partially updates a sysRolePermission.
     *
     * @param sysRolePermissionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysRolePermissionDTO> partialUpdate(SysRolePermissionDTO sysRolePermissionDTO);

    /**
     * Get all the sysRolePermissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysRolePermissionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysRolePermission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysRolePermissionDTO> findOne(String id);

    /**
     * Delete the "id" sysRolePermission.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
