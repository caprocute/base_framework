package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysDataSourceDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysDataSource}.
 */
public interface SysDataSourceService {
    /**
     * Save a sysDataSource.
     *
     * @param sysDataSourceDTO the entity to save.
     * @return the persisted entity.
     */
    SysDataSourceDTO save(SysDataSourceDTO sysDataSourceDTO);

    /**
     * Updates a sysDataSource.
     *
     * @param sysDataSourceDTO the entity to update.
     * @return the persisted entity.
     */
    SysDataSourceDTO update(SysDataSourceDTO sysDataSourceDTO);

    /**
     * Partially updates a sysDataSource.
     *
     * @param sysDataSourceDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysDataSourceDTO> partialUpdate(SysDataSourceDTO sysDataSourceDTO);

    /**
     * Get all the sysDataSources.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysDataSourceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysDataSource.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysDataSourceDTO> findOne(String id);

    /**
     * Delete the "id" sysDataSource.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
