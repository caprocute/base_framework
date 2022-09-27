package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysUserRoleDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysUserRole}.
 */
public interface SysUserRoleService {
    /**
     * Save a sysUserRole.
     *
     * @param sysUserRoleDTO the entity to save.
     * @return the persisted entity.
     */
    SysUserRoleDTO save(SysUserRoleDTO sysUserRoleDTO);

    /**
     * Updates a sysUserRole.
     *
     * @param sysUserRoleDTO the entity to update.
     * @return the persisted entity.
     */
    SysUserRoleDTO update(SysUserRoleDTO sysUserRoleDTO);

    /**
     * Partially updates a sysUserRole.
     *
     * @param sysUserRoleDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysUserRoleDTO> partialUpdate(SysUserRoleDTO sysUserRoleDTO);

    /**
     * Get all the sysUserRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysUserRoleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysUserRole.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysUserRoleDTO> findOne(String id);

    /**
     * Delete the "id" sysUserRole.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
