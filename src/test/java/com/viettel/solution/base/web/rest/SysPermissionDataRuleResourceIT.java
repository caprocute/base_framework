package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysPermissionDataRule;
import com.viettel.solution.base.repository.SysPermissionDataRuleRepository;
import com.viettel.solution.base.service.dto.SysPermissionDataRuleDTO;
import com.viettel.solution.base.service.mapper.SysPermissionDataRuleMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SysPermissionDataRuleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysPermissionDataRuleResourceIT {

    private static final String DEFAULT_PERMISSION_ID = "AAAAAAAAAA";
    private static final String UPDATED_PERMISSION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RULE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RULE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RULE_COLUMN = "AAAAAAAAAA";
    private static final String UPDATED_RULE_COLUMN = "BBBBBBBBBB";

    private static final String DEFAULT_RULE_C_ONDITIONS = "AAAAAAAAAA";
    private static final String UPDATED_RULE_C_ONDITIONS = "BBBBBBBBBB";

    private static final String DEFAULT_RULE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_RULE_VALUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sys-permission-data-rules";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysPermissionDataRuleRepository sysPermissionDataRuleRepository;

    @Autowired
    private SysPermissionDataRuleMapper sysPermissionDataRuleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysPermissionDataRuleMockMvc;

    private SysPermissionDataRule sysPermissionDataRule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysPermissionDataRule createEntity(EntityManager em) {
        SysPermissionDataRule sysPermissionDataRule = new SysPermissionDataRule()
            .permissionId(DEFAULT_PERMISSION_ID)
            .ruleName(DEFAULT_RULE_NAME)
            .ruleColumn(DEFAULT_RULE_COLUMN)
            .ruleCOnditions(DEFAULT_RULE_C_ONDITIONS)
            .ruleValue(DEFAULT_RULE_VALUE)
            .status(DEFAULT_STATUS)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateBy(DEFAULT_UPDATE_BY)
            .updateTime(DEFAULT_UPDATE_TIME)
            .tenantId(DEFAULT_TENANT_ID);
        return sysPermissionDataRule;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysPermissionDataRule createUpdatedEntity(EntityManager em) {
        SysPermissionDataRule sysPermissionDataRule = new SysPermissionDataRule()
            .permissionId(UPDATED_PERMISSION_ID)
            .ruleName(UPDATED_RULE_NAME)
            .ruleColumn(UPDATED_RULE_COLUMN)
            .ruleCOnditions(UPDATED_RULE_C_ONDITIONS)
            .ruleValue(UPDATED_RULE_VALUE)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        return sysPermissionDataRule;
    }

    @BeforeEach
    public void initTest() {
        sysPermissionDataRule = createEntity(em);
    }

    @Test
    @Transactional
    void createSysPermissionDataRule() throws Exception {
        int databaseSizeBeforeCreate = sysPermissionDataRuleRepository.findAll().size();
        // Create the SysPermissionDataRule
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO = sysPermissionDataRuleMapper.toDto(sysPermissionDataRule);
        restSysPermissionDataRuleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDataRuleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeCreate + 1);
        SysPermissionDataRule testSysPermissionDataRule = sysPermissionDataRuleList.get(sysPermissionDataRuleList.size() - 1);
        assertThat(testSysPermissionDataRule.getPermissionId()).isEqualTo(DEFAULT_PERMISSION_ID);
        assertThat(testSysPermissionDataRule.getRuleName()).isEqualTo(DEFAULT_RULE_NAME);
        assertThat(testSysPermissionDataRule.getRuleColumn()).isEqualTo(DEFAULT_RULE_COLUMN);
        assertThat(testSysPermissionDataRule.getRuleCOnditions()).isEqualTo(DEFAULT_RULE_C_ONDITIONS);
        assertThat(testSysPermissionDataRule.getRuleValue()).isEqualTo(DEFAULT_RULE_VALUE);
        assertThat(testSysPermissionDataRule.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysPermissionDataRule.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysPermissionDataRule.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysPermissionDataRule.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysPermissionDataRule.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysPermissionDataRule.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void createSysPermissionDataRuleWithExistingId() throws Exception {
        // Create the SysPermissionDataRule with an existing ID
        sysPermissionDataRule.setId("existing_id");
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO = sysPermissionDataRuleMapper.toDto(sysPermissionDataRule);

        int databaseSizeBeforeCreate = sysPermissionDataRuleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysPermissionDataRuleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDataRuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysPermissionDataRules() throws Exception {
        // Initialize the database
        sysPermissionDataRule.setId(UUID.randomUUID().toString());
        sysPermissionDataRuleRepository.saveAndFlush(sysPermissionDataRule);

        // Get all the sysPermissionDataRuleList
        restSysPermissionDataRuleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysPermissionDataRule.getId())))
            .andExpect(jsonPath("$.[*].permissionId").value(hasItem(DEFAULT_PERMISSION_ID)))
            .andExpect(jsonPath("$.[*].ruleName").value(hasItem(DEFAULT_RULE_NAME)))
            .andExpect(jsonPath("$.[*].ruleColumn").value(hasItem(DEFAULT_RULE_COLUMN)))
            .andExpect(jsonPath("$.[*].ruleCOnditions").value(hasItem(DEFAULT_RULE_C_ONDITIONS)))
            .andExpect(jsonPath("$.[*].ruleValue").value(hasItem(DEFAULT_RULE_VALUE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }

    @Test
    @Transactional
    void getSysPermissionDataRule() throws Exception {
        // Initialize the database
        sysPermissionDataRule.setId(UUID.randomUUID().toString());
        sysPermissionDataRuleRepository.saveAndFlush(sysPermissionDataRule);

        // Get the sysPermissionDataRule
        restSysPermissionDataRuleMockMvc
            .perform(get(ENTITY_API_URL_ID, sysPermissionDataRule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysPermissionDataRule.getId()))
            .andExpect(jsonPath("$.permissionId").value(DEFAULT_PERMISSION_ID))
            .andExpect(jsonPath("$.ruleName").value(DEFAULT_RULE_NAME))
            .andExpect(jsonPath("$.ruleColumn").value(DEFAULT_RULE_COLUMN))
            .andExpect(jsonPath("$.ruleCOnditions").value(DEFAULT_RULE_C_ONDITIONS))
            .andExpect(jsonPath("$.ruleValue").value(DEFAULT_RULE_VALUE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysPermissionDataRule() throws Exception {
        // Get the sysPermissionDataRule
        restSysPermissionDataRuleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysPermissionDataRule() throws Exception {
        // Initialize the database
        sysPermissionDataRule.setId(UUID.randomUUID().toString());
        sysPermissionDataRuleRepository.saveAndFlush(sysPermissionDataRule);

        int databaseSizeBeforeUpdate = sysPermissionDataRuleRepository.findAll().size();

        // Update the sysPermissionDataRule
        SysPermissionDataRule updatedSysPermissionDataRule = sysPermissionDataRuleRepository.findById(sysPermissionDataRule.getId()).get();
        // Disconnect from session so that the updates on updatedSysPermissionDataRule are not directly saved in db
        em.detach(updatedSysPermissionDataRule);
        updatedSysPermissionDataRule
            .permissionId(UPDATED_PERMISSION_ID)
            .ruleName(UPDATED_RULE_NAME)
            .ruleColumn(UPDATED_RULE_COLUMN)
            .ruleCOnditions(UPDATED_RULE_C_ONDITIONS)
            .ruleValue(UPDATED_RULE_VALUE)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO = sysPermissionDataRuleMapper.toDto(updatedSysPermissionDataRule);

        restSysPermissionDataRuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysPermissionDataRuleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDataRuleDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeUpdate);
        SysPermissionDataRule testSysPermissionDataRule = sysPermissionDataRuleList.get(sysPermissionDataRuleList.size() - 1);
        assertThat(testSysPermissionDataRule.getPermissionId()).isEqualTo(UPDATED_PERMISSION_ID);
        assertThat(testSysPermissionDataRule.getRuleName()).isEqualTo(UPDATED_RULE_NAME);
        assertThat(testSysPermissionDataRule.getRuleColumn()).isEqualTo(UPDATED_RULE_COLUMN);
        assertThat(testSysPermissionDataRule.getRuleCOnditions()).isEqualTo(UPDATED_RULE_C_ONDITIONS);
        assertThat(testSysPermissionDataRule.getRuleValue()).isEqualTo(UPDATED_RULE_VALUE);
        assertThat(testSysPermissionDataRule.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysPermissionDataRule.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysPermissionDataRule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysPermissionDataRule.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysPermissionDataRule.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysPermissionDataRule.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysPermissionDataRule() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionDataRuleRepository.findAll().size();
        sysPermissionDataRule.setId(UUID.randomUUID().toString());

        // Create the SysPermissionDataRule
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO = sysPermissionDataRuleMapper.toDto(sysPermissionDataRule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysPermissionDataRuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysPermissionDataRuleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDataRuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysPermissionDataRule() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionDataRuleRepository.findAll().size();
        sysPermissionDataRule.setId(UUID.randomUUID().toString());

        // Create the SysPermissionDataRule
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO = sysPermissionDataRuleMapper.toDto(sysPermissionDataRule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPermissionDataRuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDataRuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysPermissionDataRule() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionDataRuleRepository.findAll().size();
        sysPermissionDataRule.setId(UUID.randomUUID().toString());

        // Create the SysPermissionDataRule
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO = sysPermissionDataRuleMapper.toDto(sysPermissionDataRule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPermissionDataRuleMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDataRuleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysPermissionDataRuleWithPatch() throws Exception {
        // Initialize the database
        sysPermissionDataRule.setId(UUID.randomUUID().toString());
        sysPermissionDataRuleRepository.saveAndFlush(sysPermissionDataRule);

        int databaseSizeBeforeUpdate = sysPermissionDataRuleRepository.findAll().size();

        // Update the sysPermissionDataRule using partial update
        SysPermissionDataRule partialUpdatedSysPermissionDataRule = new SysPermissionDataRule();
        partialUpdatedSysPermissionDataRule.setId(sysPermissionDataRule.getId());

        partialUpdatedSysPermissionDataRule.ruleName(UPDATED_RULE_NAME).createTime(UPDATED_CREATE_TIME).updateTime(UPDATED_UPDATE_TIME);

        restSysPermissionDataRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysPermissionDataRule.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysPermissionDataRule))
            )
            .andExpect(status().isOk());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeUpdate);
        SysPermissionDataRule testSysPermissionDataRule = sysPermissionDataRuleList.get(sysPermissionDataRuleList.size() - 1);
        assertThat(testSysPermissionDataRule.getPermissionId()).isEqualTo(DEFAULT_PERMISSION_ID);
        assertThat(testSysPermissionDataRule.getRuleName()).isEqualTo(UPDATED_RULE_NAME);
        assertThat(testSysPermissionDataRule.getRuleColumn()).isEqualTo(DEFAULT_RULE_COLUMN);
        assertThat(testSysPermissionDataRule.getRuleCOnditions()).isEqualTo(DEFAULT_RULE_C_ONDITIONS);
        assertThat(testSysPermissionDataRule.getRuleValue()).isEqualTo(DEFAULT_RULE_VALUE);
        assertThat(testSysPermissionDataRule.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysPermissionDataRule.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysPermissionDataRule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysPermissionDataRule.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysPermissionDataRule.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysPermissionDataRule.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysPermissionDataRuleWithPatch() throws Exception {
        // Initialize the database
        sysPermissionDataRule.setId(UUID.randomUUID().toString());
        sysPermissionDataRuleRepository.saveAndFlush(sysPermissionDataRule);

        int databaseSizeBeforeUpdate = sysPermissionDataRuleRepository.findAll().size();

        // Update the sysPermissionDataRule using partial update
        SysPermissionDataRule partialUpdatedSysPermissionDataRule = new SysPermissionDataRule();
        partialUpdatedSysPermissionDataRule.setId(sysPermissionDataRule.getId());

        partialUpdatedSysPermissionDataRule
            .permissionId(UPDATED_PERMISSION_ID)
            .ruleName(UPDATED_RULE_NAME)
            .ruleColumn(UPDATED_RULE_COLUMN)
            .ruleCOnditions(UPDATED_RULE_C_ONDITIONS)
            .ruleValue(UPDATED_RULE_VALUE)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysPermissionDataRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysPermissionDataRule.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysPermissionDataRule))
            )
            .andExpect(status().isOk());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeUpdate);
        SysPermissionDataRule testSysPermissionDataRule = sysPermissionDataRuleList.get(sysPermissionDataRuleList.size() - 1);
        assertThat(testSysPermissionDataRule.getPermissionId()).isEqualTo(UPDATED_PERMISSION_ID);
        assertThat(testSysPermissionDataRule.getRuleName()).isEqualTo(UPDATED_RULE_NAME);
        assertThat(testSysPermissionDataRule.getRuleColumn()).isEqualTo(UPDATED_RULE_COLUMN);
        assertThat(testSysPermissionDataRule.getRuleCOnditions()).isEqualTo(UPDATED_RULE_C_ONDITIONS);
        assertThat(testSysPermissionDataRule.getRuleValue()).isEqualTo(UPDATED_RULE_VALUE);
        assertThat(testSysPermissionDataRule.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysPermissionDataRule.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysPermissionDataRule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysPermissionDataRule.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysPermissionDataRule.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysPermissionDataRule.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysPermissionDataRule() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionDataRuleRepository.findAll().size();
        sysPermissionDataRule.setId(UUID.randomUUID().toString());

        // Create the SysPermissionDataRule
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO = sysPermissionDataRuleMapper.toDto(sysPermissionDataRule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysPermissionDataRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysPermissionDataRuleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDataRuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysPermissionDataRule() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionDataRuleRepository.findAll().size();
        sysPermissionDataRule.setId(UUID.randomUUID().toString());

        // Create the SysPermissionDataRule
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO = sysPermissionDataRuleMapper.toDto(sysPermissionDataRule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPermissionDataRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDataRuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysPermissionDataRule() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionDataRuleRepository.findAll().size();
        sysPermissionDataRule.setId(UUID.randomUUID().toString());

        // Create the SysPermissionDataRule
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO = sysPermissionDataRuleMapper.toDto(sysPermissionDataRule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPermissionDataRuleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDataRuleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysPermissionDataRule in the database
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysPermissionDataRule() throws Exception {
        // Initialize the database
        sysPermissionDataRule.setId(UUID.randomUUID().toString());
        sysPermissionDataRuleRepository.saveAndFlush(sysPermissionDataRule);

        int databaseSizeBeforeDelete = sysPermissionDataRuleRepository.findAll().size();

        // Delete the sysPermissionDataRule
        restSysPermissionDataRuleMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysPermissionDataRule.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysPermissionDataRule> sysPermissionDataRuleList = sysPermissionDataRuleRepository.findAll();
        assertThat(sysPermissionDataRuleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
