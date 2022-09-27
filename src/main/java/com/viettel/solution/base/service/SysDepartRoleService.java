package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysDepartRoleDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysDepartRole}.
 */
public interface SysDepartRoleService {
    /**
     * Save a sysDepartRole.
     *
     * @param sysDepartRoleDTO the entity to save.
     * @return the persisted entity.
     */
    SysDepartRoleDTO save(SysDepartRoleDTO sysDepartRoleDTO);

    /**
     * Updates a sysDepartRole.
     *
     * @param sysDepartRoleDTO the entity to update.
     * @return the persisted entity.
     */
    SysDepartRoleDTO update(SysDepartRoleDTO sysDepartRoleDTO);

    /**
     * Partially updates a sysDepartRole.
     *
     * @param sysDepartRoleDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysDepartRoleDTO> partialUpdate(SysDepartRoleDTO sysDepartRoleDTO);

    /**
     * Get all the sysDepartRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysDepartRoleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysDepartRole.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysDepartRoleDTO> findOne(String id);

    /**
     * Delete the "id" sysDepartRole.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
