package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysPosition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysPosition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysPositionRepository extends JpaRepository<SysPosition, String> {}
