package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysPositionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysPosition}.
 */
public interface SysPositionService {
    /**
     * Save a sysPosition.
     *
     * @param sysPositionDTO the entity to save.
     * @return the persisted entity.
     */
    SysPositionDTO save(SysPositionDTO sysPositionDTO);

    /**
     * Updates a sysPosition.
     *
     * @param sysPositionDTO the entity to update.
     * @return the persisted entity.
     */
    SysPositionDTO update(SysPositionDTO sysPositionDTO);

    /**
     * Partially updates a sysPosition.
     *
     * @param sysPositionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysPositionDTO> partialUpdate(SysPositionDTO sysPositionDTO);

    /**
     * Get all the sysPositions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysPositionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysPosition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysPositionDTO> findOne(String id);

    /**
     * Delete the "id" sysPosition.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
