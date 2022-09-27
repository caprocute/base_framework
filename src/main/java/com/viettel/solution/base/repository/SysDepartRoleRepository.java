package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysDepartRole;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysDepartRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysDepartRoleRepository extends JpaRepository<SysDepartRole, String> {}
