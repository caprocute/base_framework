package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysDepart;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysDepart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysDepartRepository extends JpaRepository<SysDepart, String> {}
