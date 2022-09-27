package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysDataSource;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysDataSource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysDataSourceRepository extends JpaRepository<SysDataSource, String> {}
