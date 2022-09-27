package com.viettel.solution.base.service;

import com.viettel.solution.base.service.dto.SysPermissionDataRuleDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.viettel.solution.base.domain.SysPermissionDataRule}.
 */
public interface SysPermissionDataRuleService {
    /**
     * Save a sysPermissionDataRule.
     *
     * @param sysPermissionDataRuleDTO the entity to save.
     * @return the persisted entity.
     */
    SysPermissionDataRuleDTO save(SysPermissionDataRuleDTO sysPermissionDataRuleDTO);

    /**
     * Updates a sysPermissionDataRule.
     *
     * @param sysPermissionDataRuleDTO the entity to update.
     * @return the persisted entity.
     */
    SysPermissionDataRuleDTO update(SysPermissionDataRuleDTO sysPermissionDataRuleDTO);

    /**
     * Partially updates a sysPermissionDataRule.
     *
     * @param sysPermissionDataRuleDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysPermissionDataRuleDTO> partialUpdate(SysPermissionDataRuleDTO sysPermissionDataRuleDTO);

    /**
     * Get all the sysPermissionDataRules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysPermissionDataRuleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysPermissionDataRule.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysPermissionDataRuleDTO> findOne(String id);

    /**
     * Delete the "id" sysPermissionDataRule.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
