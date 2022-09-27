package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysUser;
import com.viettel.solution.base.repository.SysUserRepository;
import com.viettel.solution.base.service.SysUserService;
import com.viettel.solution.base.service.dto.SysUserDTO;
import com.viettel.solution.base.service.mapper.SysUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysUser}.
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    private final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    private final SysUserRepository sysUserRepository;

    private final SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserRepository sysUserRepository, SysUserMapper sysUserMapper) {
        this.sysUserRepository = sysUserRepository;
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public SysUserDTO save(SysUserDTO sysUserDTO) {
        log.debug("Request to save SysUser : {}", sysUserDTO);
        SysUser sysUser = sysUserMapper.toEntity(sysUserDTO);
        sysUser = sysUserRepository.save(sysUser);
        return sysUserMapper.toDto(sysUser);
    }

    @Override
    public SysUserDTO update(SysUserDTO sysUserDTO) {
        log.debug("Request to update SysUser : {}", sysUserDTO);
        SysUser sysUser = sysUserMapper.toEntity(sysUserDTO);
        sysUser.setIsPersisted();
        sysUser = sysUserRepository.save(sysUser);
        return sysUserMapper.toDto(sysUser);
    }

    @Override
    public Optional<SysUserDTO> partialUpdate(SysUserDTO sysUserDTO) {
        log.debug("Request to partially update SysUser : {}", sysUserDTO);

        return sysUserRepository
            .findById(sysUserDTO.getId())
            .map(existingSysUser -> {
                sysUserMapper.partialUpdate(existingSysUser, sysUserDTO);

                return existingSysUser;
            })
            .map(sysUserRepository::save)
            .map(sysUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysUsers");
        return sysUserRepository.findAll(pageable).map(sysUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysUserDTO> findOne(String id) {
        log.debug("Request to get SysUser : {}", id);
        return sysUserRepository.findById(id).map(sysUserMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysUser : {}", id);
        sysUserRepository.deleteById(id);
    }
}
