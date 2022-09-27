package com.viettel.solution.base.repository;

import com.viettel.solution.base.domain.SysTenant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysTenant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysTenantRepository extends JpaRepository<SysTenant, String> {}
