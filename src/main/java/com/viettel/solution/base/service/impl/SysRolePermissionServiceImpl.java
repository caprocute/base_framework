package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysRolePermission;
import com.viettel.solution.base.repository.SysRolePermissionRepository;
import com.viettel.solution.base.service.SysRolePermissionService;
import com.viettel.solution.base.service.dto.SysRolePermissionDTO;
import com.viettel.solution.base.service.mapper.SysRolePermissionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysRolePermission}.
 */
@Service
@Transactional
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

    private final Logger log = LoggerFactory.getLogger(SysRolePermissionServiceImpl.class);

    private final SysRolePermissionRepository sysRolePermissionRepository;

    private final SysRolePermissionMapper sysRolePermissionMapper;

    public SysRolePermissionServiceImpl(
        SysRolePermissionRepository sysRolePermissionRepository,
        SysRolePermissionMapper sysRolePermissionMapper
    ) {
        this.sysRolePermissionRepository = sysRolePermissionRepository;
        this.sysRolePermissionMapper = sysRolePermissionMapper;
    }

    @Override
    public SysRolePermissionDTO save(SysRolePermissionDTO sysRolePermissionDTO) {
        log.debug("Request to save SysRolePermission : {}", sysRolePermissionDTO);
        SysRolePermission sysRolePermission = sysRolePermissionMapper.toEntity(sysRolePermissionDTO);
        sysRolePermission = sysRolePermissionRepository.save(sysRolePermission);
        return sysRolePermissionMapper.toDto(sysRolePermission);
    }

    @Override
    public SysRolePermissionDTO update(SysRolePermissionDTO sysRolePermissionDTO) {
        log.debug("Request to update SysRolePermission : {}", sysRolePermissionDTO);
        SysRolePermission sysRolePermission = sysRolePermissionMapper.toEntity(sysRolePermissionDTO);
        sysRolePermission.setIsPersisted();
        sysRolePermission = sysRolePermissionRepository.save(sysRolePermission);
        return sysRolePermissionMapper.toDto(sysRolePermission);
    }

    @Override
    public Optional<SysRolePermissionDTO> partialUpdate(SysRolePermissionDTO sysRolePermissionDTO) {
        log.debug("Request to partially update SysRolePermission : {}", sysRolePermissionDTO);

        return sysRolePermissionRepository
            .findById(sysRolePermissionDTO.getId())
            .map(existingSysRolePermission -> {
                sysRolePermissionMapper.partialUpdate(existingSysRolePermission, sysRolePermissionDTO);

                return existingSysRolePermission;
            })
            .map(sysRolePermissionRepository::save)
            .map(sysRolePermissionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysRolePermissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysRolePermissions");
        return sysRolePermissionRepository.findAll(pageable).map(sysRolePermissionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysRolePermissionDTO> findOne(String id) {
        log.debug("Request to get SysRolePermission : {}", id);
        return sysRolePermissionRepository.findById(id).map(sysRolePermissionMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysRolePermission : {}", id);
        sysRolePermissionRepository.deleteById(id);
    }
}
