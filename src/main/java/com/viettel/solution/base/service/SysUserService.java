package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysUser}.
 */
public interface SysUserService {
    /**
     * Save a sysUser.
     *
     * @param sysUserDTO the entity to save.
     * @return the persisted entity.
     */
    SysUserDTO save(SysUserDTO sysUserDTO);

    /**
     * Updates a sysUser.
     *
     * @param sysUserDTO the entity to update.
     * @return the persisted entity.
     */
    SysUserDTO update(SysUserDTO sysUserDTO);

    /**
     * Partially updates a sysUser.
     *
     * @param sysUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysUserDTO> partialUpdate(SysUserDTO sysUserDTO);

    /**
     * Get all the sysUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysUserDTO> findOne(String id);

    /**
     * Delete the "id" sysUser.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
