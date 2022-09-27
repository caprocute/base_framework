package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysLog}.
 */
public interface SysLogService {
    /**
     * Save a sysLog.
     *
     * @param sysLogDTO the entity to save.
     * @return the persisted entity.
     */
    SysLogDTO save(SysLogDTO sysLogDTO);

    /**
     * Updates a sysLog.
     *
     * @param sysLogDTO the entity to update.
     * @return the persisted entity.
     */
    SysLogDTO update(SysLogDTO sysLogDTO);

    /**
     * Partially updates a sysLog.
     *
     * @param sysLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysLogDTO> partialUpdate(SysLogDTO sysLogDTO);

    /**
     * Get all the sysLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysLogDTO> findOne(String id);

    /**
     * Delete the "id" sysLog.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
