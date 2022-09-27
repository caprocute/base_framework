package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysPermissionDataRule;
import com.viettel.solution.base.repository.SysPermissionDataRuleRepository;
import com.viettel.solution.base.service.SysPermissionDataRuleService;
import com.viettel.solution.base.service.dto.SysPermissionDataRuleDTO;
import com.viettel.solution.base.service.mapper.SysPermissionDataRuleMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysPermissionDataRule}.
 */
@Service
@Transactional
public class SysPermissionDataRuleServiceImpl implements SysPermissionDataRuleService {

    private final Logger log = LoggerFactory.getLogger(SysPermissionDataRuleServiceImpl.class);

    private final SysPermissionDataRuleRepository sysPermissionDataRuleRepository;

    private final SysPermissionDataRuleMapper sysPermissionDataRuleMapper;

    public SysPermissionDataRuleServiceImpl(
        SysPermissionDataRuleRepository sysPermissionDataRuleRepository,
        SysPermissionDataRuleMapper sysPermissionDataRuleMapper
    ) {
        this.sysPermissionDataRuleRepository = sysPermissionDataRuleRepository;
        this.sysPermissionDataRuleMapper = sysPermissionDataRuleMapper;
    }

    @Override
    public SysPermissionDataRuleDTO save(SysPermissionDataRuleDTO sysPermissionDataRuleDTO) {
        log.debug("Request to save SysPermissionDataRule : {}", sysPermissionDataRuleDTO);
        SysPermissionDataRule sysPermissionDataRule = sysPermissionDataRuleMapper.toEntity(sysPermissionDataRuleDTO);
        sysPermissionDataRule = sysPermissionDataRuleRepository.save(sysPermissionDataRule);
        return sysPermissionDataRuleMapper.toDto(sysPermissionDataRule);
    }

    @Override
    public SysPermissionDataRuleDTO update(SysPermissionDataRuleDTO sysPermissionDataRuleDTO) {
        log.debug("Request to update SysPermissionDataRule : {}", sysPermissionDataRuleDTO);
        SysPermissionDataRule sysPermissionDataRule = sysPermissionDataRuleMapper.toEntity(sysPermissionDataRuleDTO);
        sysPermissionDataRule.setIsPersisted();
        sysPermissionDataRule = sysPermissionDataRuleRepository.save(sysPermissionDataRule);
        return sysPermissionDataRuleMapper.toDto(sysPermissionDataRule);
    }

    @Override
    public Optional<SysPermissionDataRuleDTO> partialUpdate(SysPermissionDataRuleDTO sysPermissionDataRuleDTO) {
        log.debug("Request to partially update SysPermissionDataRule : {}", sysPermissionDataRuleDTO);

        return sysPermissionDataRuleRepository
            .findById(sysPermissionDataRuleDTO.getId())
            .map(existingSysPermissionDataRule -> {
                sysPermissionDataRuleMapper.partialUpdate(existingSysPermissionDataRule, sysPermissionDataRuleDTO);

                return existingSysPermissionDataRule;
            })
            .map(sysPermissionDataRuleRepository::save)
            .map(sysPermissionDataRuleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysPermissionDataRuleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysPermissionDataRules");
        return sysPermissionDataRuleRepository.findAll(pageable).map(sysPermissionDataRuleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysPermissionDataRuleDTO> findOne(String id) {
        log.debug("Request to get SysPermissionDataRule : {}", id);
        return sysPermissionDataRuleRepository.findById(id).map(sysPermissionDataRuleMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysPermissionDataRule : {}", id);
        sysPermissionDataRuleRepository.deleteById(id);
    }
}
