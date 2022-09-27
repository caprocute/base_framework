package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysPermission;
import com.viettel.solution.base.repository.SysPermissionRepository;
import com.viettel.solution.base.service.SysPermissionService;
import com.viettel.solution.base.service.dto.SysPermissionDTO;
import com.viettel.solution.base.service.mapper.SysPermissionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysPermission}.
 */
@Service
@Transactional
public class SysPermissionServiceImpl implements SysPermissionService {

    private final Logger log = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    private final SysPermissionRepository sysPermissionRepository;

    private final SysPermissionMapper sysPermissionMapper;

    public SysPermissionServiceImpl(SysPermissionRepository sysPermissionRepository, SysPermissionMapper sysPermissionMapper) {
        this.sysPermissionRepository = sysPermissionRepository;
        this.sysPermissionMapper = sysPermissionMapper;
    }

    @Override
    public SysPermissionDTO save(SysPermissionDTO sysPermissionDTO) {
        log.debug("Request to save SysPermission : {}", sysPermissionDTO);
        SysPermission sysPermission = sysPermissionMapper.toEntity(sysPermissionDTO);
        sysPermission = sysPermissionRepository.save(sysPermission);
        return sysPermissionMapper.toDto(sysPermission);
    }

    @Override
    public SysPermissionDTO update(SysPermissionDTO sysPermissionDTO) {
        log.debug("Request to update SysPermission : {}", sysPermissionDTO);
        SysPermission sysPermission = sysPermissionMapper.toEntity(sysPermissionDTO);
        sysPermission.setIsPersisted();
        sysPermission = sysPermissionRepository.save(sysPermission);
        return sysPermissionMapper.toDto(sysPermission);
    }

    @Override
    public Optional<SysPermissionDTO> partialUpdate(SysPermissionDTO sysPermissionDTO) {
        log.debug("Request to partially update SysPermission : {}", sysPermissionDTO);

        return sysPermissionRepository
            .findById(sysPermissionDTO.getId())
            .map(existingSysPermission -> {
                sysPermissionMapper.partialUpdate(existingSysPermission, sysPermissionDTO);

                return existingSysPermission;
            })
            .map(sysPermissionRepository::save)
            .map(sysPermissionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysPermissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysPermissions");
        return sysPermissionRepository.findAll(pageable).map(sysPermissionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysPermissionDTO> findOne(String id) {
        log.debug("Request to get SysPermission : {}", id);
        return sysPermissionRepository.findById(id).map(sysPermissionMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysPermission : {}", id);
        sysPermissionRepository.deleteById(id);
    }
}
