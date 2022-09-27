package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysPermission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysPermission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermission, String> {}
