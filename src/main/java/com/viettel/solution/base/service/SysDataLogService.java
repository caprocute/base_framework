package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysDataLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysDataLog}.
 */
public interface SysDataLogService {
    /**
     * Save a sysDataLog.
     *
     * @param sysDataLogDTO the entity to save.
     * @return the persisted entity.
     */
    SysDataLogDTO save(SysDataLogDTO sysDataLogDTO);

    /**
     * Updates a sysDataLog.
     *
     * @param sysDataLogDTO the entity to update.
     * @return the persisted entity.
     */
    SysDataLogDTO update(SysDataLogDTO sysDataLogDTO);

    /**
     * Partially updates a sysDataLog.
     *
     * @param sysDataLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysDataLogDTO> partialUpdate(SysDataLogDTO sysDataLogDTO);

    /**
     * Get all the sysDataLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysDataLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysDataLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysDataLogDTO> findOne(String id);

    /**
     * Delete the "id" sysDataLog.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
