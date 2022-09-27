package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysUserDepartDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysUserDepart}.
 */
public interface SysUserDepartService {
    /**
     * Save a sysUserDepart.
     *
     * @param sysUserDepartDTO the entity to save.
     * @return the persisted entity.
     */
    SysUserDepartDTO save(SysUserDepartDTO sysUserDepartDTO);

    /**
     * Updates a sysUserDepart.
     *
     * @param sysUserDepartDTO the entity to update.
     * @return the persisted entity.
     */
    SysUserDepartDTO update(SysUserDepartDTO sysUserDepartDTO);

    /**
     * Partially updates a sysUserDepart.
     *
     * @param sysUserDepartDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysUserDepartDTO> partialUpdate(SysUserDepartDTO sysUserDepartDTO);

    /**
     * Get all the sysUserDeparts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysUserDepartDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysUserDepart.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysUserDepartDTO> findOne(String id);

    /**
     * Delete the "id" sysUserDepart.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
