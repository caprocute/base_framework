package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysLog;
import com.viettel.solution.base.repository.SysLogRepository;
import com.viettel.solution.base.service.dto.SysLogDTO;
import com.viettel.solution.base.service.mapper.SysLogMapper;
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
 * Integration tests for the {@link SysLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysLogResourceIT {

    private static final Integer DEFAULT_LOG_TYPE = 1;
    private static final Integer UPDATED_LOG_TYPE = 2;

    private static final String DEFAULT_LOG_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_LOG_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IP = "AAAAAAAAAA";
    private static final String UPDATED_IP = "BBBBBBBBBB";

    private static final String DEFAULT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_URL = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_URL = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_COST_TIME = 1L;
    private static final Long UPDATED_COST_TIME = 2L;

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

    private static final String ENTITY_API_URL = "/api/sys-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysLogRepository sysLogRepository;

    @Autowired
    private SysLogMapper sysLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysLogMockMvc;

    private SysLog sysLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysLog createEntity(EntityManager em) {
        SysLog sysLog = new SysLog()
            .logType(DEFAULT_LOG_TYPE)
            .logContent(DEFAULT_LOG_CONTENT)
            .operateType(DEFAULT_OPERATE_TYPE)
            .userName(DEFAULT_USER_NAME)
            .ip(DEFAULT_IP)
            .method(DEFAULT_METHOD)
            .requestUrl(DEFAULT_REQUEST_URL)
            .requestParam(DEFAULT_REQUEST_PARAM)
            .requestType(DEFAULT_REQUEST_TYPE)
            .costTime(DEFAULT_COST_TIME)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateBy(DEFAULT_UPDATE_BY)
            .updateTime(DEFAULT_UPDATE_TIME)
            .tenantId(DEFAULT_TENANT_ID);
        return sysLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysLog createUpdatedEntity(EntityManager em) {
        SysLog sysLog = new SysLog()
            .logType(UPDATED_LOG_TYPE)
            .logContent(UPDATED_LOG_CONTENT)
            .operateType(UPDATED_OPERATE_TYPE)
            .userName(UPDATED_USER_NAME)
            .ip(UPDATED_IP)
            .method(UPDATED_METHOD)
            .requestUrl(UPDATED_REQUEST_URL)
            .requestParam(UPDATED_REQUEST_PARAM)
            .requestType(UPDATED_REQUEST_TYPE)
            .costTime(UPDATED_COST_TIME)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        return sysLog;
    }

    @BeforeEach
    public void initTest() {
        sysLog = createEntity(em);
    }

    @Test
    @Transactional
    void createSysLog() throws Exception {
        int databaseSizeBeforeCreate = sysLogRepository.findAll().size();
        // Create the SysLog
        SysLogDTO sysLogDTO = sysLogMapper.toDto(sysLog);
        restSysLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysLogDTO)))
            .andExpect(status().isCreated());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeCreate + 1);
        SysLog testSysLog = sysLogList.get(sysLogList.size() - 1);
        assertThat(testSysLog.getLogType()).isEqualTo(DEFAULT_LOG_TYPE);
        assertThat(testSysLog.getLogContent()).isEqualTo(DEFAULT_LOG_CONTENT);
        assertThat(testSysLog.getOperateType()).isEqualTo(DEFAULT_OPERATE_TYPE);
        assertThat(testSysLog.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testSysLog.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testSysLog.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testSysLog.getRequestUrl()).isEqualTo(DEFAULT_REQUEST_URL);
        assertThat(testSysLog.getRequestParam()).isEqualTo(DEFAULT_REQUEST_PARAM);
        assertThat(testSysLog.getRequestType()).isEqualTo(DEFAULT_REQUEST_TYPE);
        assertThat(testSysLog.getCostTime()).isEqualTo(DEFAULT_COST_TIME);
        assertThat(testSysLog.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysLog.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysLog.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysLog.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysLog.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void createSysLogWithExistingId() throws Exception {
        // Create the SysLog with an existing ID
        sysLog.setId("existing_id");
        SysLogDTO sysLogDTO = sysLogMapper.toDto(sysLog);

        int databaseSizeBeforeCreate = sysLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysLogs() throws Exception {
        // Initialize the database
        sysLog.setId(UUID.randomUUID().toString());
        sysLogRepository.saveAndFlush(sysLog);

        // Get all the sysLogList
        restSysLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysLog.getId())))
            .andExpect(jsonPath("$.[*].logType").value(hasItem(DEFAULT_LOG_TYPE)))
            .andExpect(jsonPath("$.[*].logContent").value(hasItem(DEFAULT_LOG_CONTENT)))
            .andExpect(jsonPath("$.[*].operateType").value(hasItem(DEFAULT_OPERATE_TYPE)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP)))
            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD)))
            .andExpect(jsonPath("$.[*].requestUrl").value(hasItem(DEFAULT_REQUEST_URL)))
            .andExpect(jsonPath("$.[*].requestParam").value(hasItem(DEFAULT_REQUEST_PARAM)))
            .andExpect(jsonPath("$.[*].requestType").value(hasItem(DEFAULT_REQUEST_TYPE)))
            .andExpect(jsonPath("$.[*].costTime").value(hasItem(DEFAULT_COST_TIME.intValue())))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }

    @Test
    @Transactional
    void getSysLog() throws Exception {
        // Initialize the database
        sysLog.setId(UUID.randomUUID().toString());
        sysLogRepository.saveAndFlush(sysLog);

        // Get the sysLog
        restSysLogMockMvc
            .perform(get(ENTITY_API_URL_ID, sysLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysLog.getId()))
            .andExpect(jsonPath("$.logType").value(DEFAULT_LOG_TYPE))
            .andExpect(jsonPath("$.logContent").value(DEFAULT_LOG_CONTENT))
            .andExpect(jsonPath("$.operateType").value(DEFAULT_OPERATE_TYPE))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP))
            .andExpect(jsonPath("$.method").value(DEFAULT_METHOD))
            .andExpect(jsonPath("$.requestUrl").value(DEFAULT_REQUEST_URL))
            .andExpect(jsonPath("$.requestParam").value(DEFAULT_REQUEST_PARAM))
            .andExpect(jsonPath("$.requestType").value(DEFAULT_REQUEST_TYPE))
            .andExpect(jsonPath("$.costTime").value(DEFAULT_COST_TIME.intValue()))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysLog() throws Exception {
        // Get the sysLog
        restSysLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysLog() throws Exception {
        // Initialize the database
        sysLog.setId(UUID.randomUUID().toString());
        sysLogRepository.saveAndFlush(sysLog);

        int databaseSizeBeforeUpdate = sysLogRepository.findAll().size();

        // Update the sysLog
        SysLog updatedSysLog = sysLogRepository.findById(sysLog.getId()).get();
        // Disconnect from session so that the updates on updatedSysLog are not directly saved in db
        em.detach(updatedSysLog);
        updatedSysLog
            .logType(UPDATED_LOG_TYPE)
            .logContent(UPDATED_LOG_CONTENT)
            .operateType(UPDATED_OPERATE_TYPE)
            .userName(UPDATED_USER_NAME)
            .ip(UPDATED_IP)
            .method(UPDATED_METHOD)
            .requestUrl(UPDATED_REQUEST_URL)
            .requestParam(UPDATED_REQUEST_PARAM)
            .requestType(UPDATED_REQUEST_TYPE)
            .costTime(UPDATED_COST_TIME)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        SysLogDTO sysLogDTO = sysLogMapper.toDto(updatedSysLog);

        restSysLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysLogDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeUpdate);
        SysLog testSysLog = sysLogList.get(sysLogList.size() - 1);
        assertThat(testSysLog.getLogType()).isEqualTo(UPDATED_LOG_TYPE);
        assertThat(testSysLog.getLogContent()).isEqualTo(UPDATED_LOG_CONTENT);
        assertThat(testSysLog.getOperateType()).isEqualTo(UPDATED_OPERATE_TYPE);
        assertThat(testSysLog.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testSysLog.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testSysLog.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testSysLog.getRequestUrl()).isEqualTo(UPDATED_REQUEST_URL);
        assertThat(testSysLog.getRequestParam()).isEqualTo(UPDATED_REQUEST_PARAM);
        assertThat(testSysLog.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testSysLog.getCostTime()).isEqualTo(UPDATED_COST_TIME);
        assertThat(testSysLog.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysLog.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysLog.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysLog.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysLog.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysLog() throws Exception {
        int databaseSizeBeforeUpdate = sysLogRepository.findAll().size();
        sysLog.setId(UUID.randomUUID().toString());

        // Create the SysLog
        SysLogDTO sysLogDTO = sysLogMapper.toDto(sysLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysLogDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysLog() throws Exception {
        int databaseSizeBeforeUpdate = sysLogRepository.findAll().size();
        sysLog.setId(UUID.randomUUID().toString());

        // Create the SysLog
        SysLogDTO sysLogDTO = sysLogMapper.toDto(sysLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysLog() throws Exception {
        int databaseSizeBeforeUpdate = sysLogRepository.findAll().size();
        sysLog.setId(UUID.randomUUID().toString());

        // Create the SysLog
        SysLogDTO sysLogDTO = sysLogMapper.toDto(sysLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysLogMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysLogDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysLogWithPatch() throws Exception {
        // Initialize the database
        sysLog.setId(UUID.randomUUID().toString());
        sysLogRepository.saveAndFlush(sysLog);

        int databaseSizeBeforeUpdate = sysLogRepository.findAll().size();

        // Update the sysLog using partial update
        SysLog partialUpdatedSysLog = new SysLog();
        partialUpdatedSysLog.setId(sysLog.getId());

        partialUpdatedSysLog
            .operateType(UPDATED_OPERATE_TYPE)
            .userName(UPDATED_USER_NAME)
            .ip(UPDATED_IP)
            .method(UPDATED_METHOD)
            .requestUrl(UPDATED_REQUEST_URL)
            .requestParam(UPDATED_REQUEST_PARAM)
            .requestType(UPDATED_REQUEST_TYPE)
            .costTime(UPDATED_COST_TIME)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSysLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysLog))
            )
            .andExpect(status().isOk());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeUpdate);
        SysLog testSysLog = sysLogList.get(sysLogList.size() - 1);
        assertThat(testSysLog.getLogType()).isEqualTo(DEFAULT_LOG_TYPE);
        assertThat(testSysLog.getLogContent()).isEqualTo(DEFAULT_LOG_CONTENT);
        assertThat(testSysLog.getOperateType()).isEqualTo(UPDATED_OPERATE_TYPE);
        assertThat(testSysLog.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testSysLog.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testSysLog.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testSysLog.getRequestUrl()).isEqualTo(UPDATED_REQUEST_URL);
        assertThat(testSysLog.getRequestParam()).isEqualTo(UPDATED_REQUEST_PARAM);
        assertThat(testSysLog.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testSysLog.getCostTime()).isEqualTo(UPDATED_COST_TIME);
        assertThat(testSysLog.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysLog.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysLog.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysLog.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysLog.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysLogWithPatch() throws Exception {
        // Initialize the database
        sysLog.setId(UUID.randomUUID().toString());
        sysLogRepository.saveAndFlush(sysLog);

        int databaseSizeBeforeUpdate = sysLogRepository.findAll().size();

        // Update the sysLog using partial update
        SysLog partialUpdatedSysLog = new SysLog();
        partialUpdatedSysLog.setId(sysLog.getId());

        partialUpdatedSysLog
            .logType(UPDATED_LOG_TYPE)
            .logContent(UPDATED_LOG_CONTENT)
            .operateType(UPDATED_OPERATE_TYPE)
            .userName(UPDATED_USER_NAME)
            .ip(UPDATED_IP)
            .method(UPDATED_METHOD)
            .requestUrl(UPDATED_REQUEST_URL)
            .requestParam(UPDATED_REQUEST_PARAM)
            .requestType(UPDATED_REQUEST_TYPE)
            .costTime(UPDATED_COST_TIME)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysLog))
            )
            .andExpect(status().isOk());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeUpdate);
        SysLog testSysLog = sysLogList.get(sysLogList.size() - 1);
        assertThat(testSysLog.getLogType()).isEqualTo(UPDATED_LOG_TYPE);
        assertThat(testSysLog.getLogContent()).isEqualTo(UPDATED_LOG_CONTENT);
        assertThat(testSysLog.getOperateType()).isEqualTo(UPDATED_OPERATE_TYPE);
        assertThat(testSysLog.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testSysLog.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testSysLog.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testSysLog.getRequestUrl()).isEqualTo(UPDATED_REQUEST_URL);
        assertThat(testSysLog.getRequestParam()).isEqualTo(UPDATED_REQUEST_PARAM);
        assertThat(testSysLog.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testSysLog.getCostTime()).isEqualTo(UPDATED_COST_TIME);
        assertThat(testSysLog.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysLog.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysLog.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysLog.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysLog.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysLog() throws Exception {
        int databaseSizeBeforeUpdate = sysLogRepository.findAll().size();
        sysLog.setId(UUID.randomUUID().toString());

        // Create the SysLog
        SysLogDTO sysLogDTO = sysLogMapper.toDto(sysLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysLogDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysLog() throws Exception {
        int databaseSizeBeforeUpdate = sysLogRepository.findAll().size();
        sysLog.setId(UUID.randomUUID().toString());

        // Create the SysLog
        SysLogDTO sysLogDTO = sysLogMapper.toDto(sysLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysLog() throws Exception {
        int databaseSizeBeforeUpdate = sysLogRepository.findAll().size();
        sysLog.setId(UUID.randomUUID().toString());

        // Create the SysLog
        SysLogDTO sysLogDTO = sysLogMapper.toDto(sysLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysLogMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysLog in the database
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysLog() throws Exception {
        // Initialize the database
        sysLog.setId(UUID.randomUUID().toString());
        sysLogRepository.saveAndFlush(sysLog);

        int databaseSizeBeforeDelete = sysLogRepository.findAll().size();

        // Delete the sysLog
        restSysLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysLog> sysLogList = sysLogRepository.findAll();
        assertThat(sysLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
