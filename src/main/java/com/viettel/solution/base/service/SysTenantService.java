package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysTenantDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysTenant}.
 */
public interface SysTenantService {
    /**
     * Save a sysTenant.
     *
     * @param sysTenantDTO the entity to save.
     * @return the persisted entity.
     */
    SysTenantDTO save(SysTenantDTO sysTenantDTO);

    /**
     * Updates a sysTenant.
     *
     * @param sysTenantDTO the entity to update.
     * @return the persisted entity.
     */
    SysTenantDTO update(SysTenantDTO sysTenantDTO);

    /**
     * Partially updates a sysTenant.
     *
     * @param sysTenantDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysTenantDTO> partialUpdate(SysTenantDTO sysTenantDTO);

    /**
     * Get all the sysTenants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysTenantDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysTenant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysTenantDTO> findOne(String id);

    /**
     * Delete the "id" sysTenant.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
