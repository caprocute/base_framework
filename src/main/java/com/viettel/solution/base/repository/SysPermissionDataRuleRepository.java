package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysPermissionDataRule;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysPermissionDataRule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysPermissionDataRuleRepository extends JpaRepository<SysPermissionDataRule, String> {}
