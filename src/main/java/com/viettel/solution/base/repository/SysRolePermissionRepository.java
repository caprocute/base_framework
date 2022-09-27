package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysRolePermission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysRolePermission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRolePermissionRepository extends JpaRepository<SysRolePermission, String> {}
