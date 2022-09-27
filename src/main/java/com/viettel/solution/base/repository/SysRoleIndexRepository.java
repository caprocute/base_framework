package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysRoleIndex;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysRoleIndex entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleIndexRepository extends JpaRepository<SysRoleIndex, String> {}
