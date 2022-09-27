package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysRoleIndexDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysRoleIndex}.
 */
public interface SysRoleIndexService {
    /**
     * Save a sysRoleIndex.
     *
     * @param sysRoleIndexDTO the entity to save.
     * @return the persisted entity.
     */
    SysRoleIndexDTO save(SysRoleIndexDTO sysRoleIndexDTO);

    /**
     * Updates a sysRoleIndex.
     *
     * @param sysRoleIndexDTO the entity to update.
     * @return the persisted entity.
     */
    SysRoleIndexDTO update(SysRoleIndexDTO sysRoleIndexDTO);

    /**
     * Partially updates a sysRoleIndex.
     *
     * @param sysRoleIndexDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysRoleIndexDTO> partialUpdate(SysRoleIndexDTO sysRoleIndexDTO);

    /**
     * Get all the sysRoleIndices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysRoleIndexDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysRoleIndex.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysRoleIndexDTO> findOne(String id);

    /**
     * Delete the "id" sysRoleIndex.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
