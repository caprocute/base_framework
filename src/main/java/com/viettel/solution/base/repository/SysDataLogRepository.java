package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysDataLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysDataLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysDataLogRepository extends JpaRepository<SysDataLog, String> {}
