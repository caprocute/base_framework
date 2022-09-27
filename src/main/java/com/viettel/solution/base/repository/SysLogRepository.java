package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysLogRepository extends JpaRepository<SysLog, String> {}
