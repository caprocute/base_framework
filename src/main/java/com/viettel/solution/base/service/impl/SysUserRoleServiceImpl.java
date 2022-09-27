package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysUserRole;
import com.viettel.solution.base.repository.SysUserRoleRepository;
import com.viettel.solution.base.service.SysUserRoleService;
import com.viettel.solution.base.service.dto.SysUserRoleDTO;
import com.viettel.solution.base.service.mapper.SysUserRoleMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysUserRole}.
 */
@Service
@Transactional
public class SysUserRoleServiceImpl implements SysUserRoleService {

    private final Logger log = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    private final SysUserRoleRepository sysUserRoleRepository;

    private final SysUserRoleMapper sysUserRoleMapper;

    public SysUserRoleServiceImpl(SysUserRoleRepository sysUserRoleRepository, SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleRepository = sysUserRoleRepository;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Override
    public SysUserRoleDTO save(SysUserRoleDTO sysUserRoleDTO) {
        log.debug("Request to save SysUserRole : {}", sysUserRoleDTO);
        SysUserRole sysUserRole = sysUserRoleMapper.toEntity(sysUserRoleDTO);
        sysUserRole = sysUserRoleRepository.save(sysUserRole);
        return sysUserRoleMapper.toDto(sysUserRole);
    }

    @Override
    public SysUserRoleDTO update(SysUserRoleDTO sysUserRoleDTO) {
        log.debug("Request to update SysUserRole : {}", sysUserRoleDTO);
        SysUserRole sysUserRole = sysUserRoleMapper.toEntity(sysUserRoleDTO);
        sysUserRole.setIsPersisted();
        sysUserRole = sysUserRoleRepository.save(sysUserRole);
        return sysUserRoleMapper.toDto(sysUserRole);
    }

    @Override
    public Optional<SysUserRoleDTO> partialUpdate(SysUserRoleDTO sysUserRoleDTO) {
        log.debug("Request to partially update SysUserRole : {}", sysUserRoleDTO);

        return sysUserRoleRepository
            .findById(sysUserRoleDTO.getId())
            .map(existingSysUserRole -> {
                sysUserRoleMapper.partialUpdate(existingSysUserRole, sysUserRoleDTO);

                return existingSysUserRole;
            })
            .map(sysUserRoleRepository::save)
            .map(sysUserRoleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysUserRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysUserRoles");
        return sysUserRoleRepository.findAll(pageable).map(sysUserRoleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysUserRoleDTO> findOne(String id) {
        log.debug("Request to get SysUserRole : {}", id);
        return sysUserRoleRepository.findById(id).map(sysUserRoleMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysUserRole : {}", id);
        sysUserRoleRepository.deleteById(id);
    }
}
