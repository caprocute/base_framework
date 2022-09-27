package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysDepartDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysDepart}.
 */
public interface SysDepartService {
    /**
     * Save a sysDepart.
     *
     * @param sysDepartDTO the entity to save.
     * @return the persisted entity.
     */
    SysDepartDTO save(SysDepartDTO sysDepartDTO);

    /**
     * Updates a sysDepart.
     *
     * @param sysDepartDTO the entity to update.
     * @return the persisted entity.
     */
    SysDepartDTO update(SysDepartDTO sysDepartDTO);

    /**
     * Partially updates a sysDepart.
     *
     * @param sysDepartDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysDepartDTO> partialUpdate(SysDepartDTO sysDepartDTO);

    /**
     * Get all the sysDeparts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysDepartDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysDepart.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysDepartDTO> findOne(String id);

    /**
     * Delete the "id" sysDepart.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
