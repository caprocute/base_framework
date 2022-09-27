package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysUserDepart;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysUserDepart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserDepartRepository extends JpaRepository<SysUserDepart, String> {}
