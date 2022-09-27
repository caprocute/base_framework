package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysDataLog;
import com.viettel.solution.base.repository.SysDataLogRepository;
import com.viettel.solution.base.service.dto.SysDataLogDTO;
import com.viettel.solution.base.service.mapper.SysDataLogMapper;
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
 * Integration tests for the {@link SysDataLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysDataLogResourceIT {

    private static final String DEFAULT_DATA_TABLE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_TABLE = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_ID = "AAAAAAAAAA";
    private static final String UPDATED_DATA_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_DATA_CONTENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_DATA_VERSION = 1;
    private static final Integer UPDATED_DATA_VERSION = 2;

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

    private static final String ENTITY_API_URL = "/api/sys-data-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysDataLogRepository sysDataLogRepository;

    @Autowired
    private SysDataLogMapper sysDataLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysDataLogMockMvc;

    private SysDataLog sysDataLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDataLog createEntity(EntityManager em) {
        SysDataLog sysDataLog = new SysDataLog()
            .dataTable(DEFAULT_DATA_TABLE)
            .dataId(DEFAULT_DATA_ID)
            .dataContent(DEFAULT_DATA_CONTENT)
            .dataVersion(DEFAULT_DATA_VERSION)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateBy(DEFAULT_UPDATE_BY)
            .updateTime(DEFAULT_UPDATE_TIME)
            .tenantId(DEFAULT_TENANT_ID);
        return sysDataLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDataLog createUpdatedEntity(EntityManager em) {
        SysDataLog sysDataLog = new SysDataLog()
            .dataTable(UPDATED_DATA_TABLE)
            .dataId(UPDATED_DATA_ID)
            .dataContent(UPDATED_DATA_CONTENT)
            .dataVersion(UPDATED_DATA_VERSION)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        return sysDataLog;
    }

    @BeforeEach
    public void initTest() {
        sysDataLog = createEntity(em);
    }

    @Test
    @Transactional
    void createSysDataLog() throws Exception {
        int databaseSizeBeforeCreate = sysDataLogRepository.findAll().size();
        // Create the SysDataLog
        SysDataLogDTO sysDataLogDTO = sysDataLogMapper.toDto(sysDataLog);
        restSysDataLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDataLogDTO)))
            .andExpect(status().isCreated());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeCreate + 1);
        SysDataLog testSysDataLog = sysDataLogList.get(sysDataLogList.size() - 1);
        assertThat(testSysDataLog.getDataTable()).isEqualTo(DEFAULT_DATA_TABLE);
        assertThat(testSysDataLog.getDataId()).isEqualTo(DEFAULT_DATA_ID);
        assertThat(testSysDataLog.getDataContent()).isEqualTo(DEFAULT_DATA_CONTENT);
        assertThat(testSysDataLog.getDataVersion()).isEqualTo(DEFAULT_DATA_VERSION);
        assertThat(testSysDataLog.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysDataLog.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysDataLog.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysDataLog.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysDataLog.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void createSysDataLogWithExistingId() throws Exception {
        // Create the SysDataLog with an existing ID
        sysDataLog.setId("existing_id");
        SysDataLogDTO sysDataLogDTO = sysDataLogMapper.toDto(sysDataLog);

        int databaseSizeBeforeCreate = sysDataLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysDataLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDataLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysDataLogs() throws Exception {
        // Initialize the database
        sysDataLog.setId(UUID.randomUUID().toString());
        sysDataLogRepository.saveAndFlush(sysDataLog);

        // Get all the sysDataLogList
        restSysDataLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysDataLog.getId())))
            .andExpect(jsonPath("$.[*].dataTable").value(hasItem(DEFAULT_DATA_TABLE)))
            .andExpect(jsonPath("$.[*].dataId").value(hasItem(DEFAULT_DATA_ID)))
            .andExpect(jsonPath("$.[*].dataContent").value(hasItem(DEFAULT_DATA_CONTENT)))
            .andExpect(jsonPath("$.[*].dataVersion").value(hasItem(DEFAULT_DATA_VERSION)))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }

    @Test
    @Transactional
    void getSysDataLog() throws Exception {
        // Initialize the database
        sysDataLog.setId(UUID.randomUUID().toString());
        sysDataLogRepository.saveAndFlush(sysDataLog);

        // Get the sysDataLog
        restSysDataLogMockMvc
            .perform(get(ENTITY_API_URL_ID, sysDataLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysDataLog.getId()))
            .andExpect(jsonPath("$.dataTable").value(DEFAULT_DATA_TABLE))
            .andExpect(jsonPath("$.dataId").value(DEFAULT_DATA_ID))
            .andExpect(jsonPath("$.dataContent").value(DEFAULT_DATA_CONTENT))
            .andExpect(jsonPath("$.dataVersion").value(DEFAULT_DATA_VERSION))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysDataLog() throws Exception {
        // Get the sysDataLog
        restSysDataLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysDataLog() throws Exception {
        // Initialize the database
        sysDataLog.setId(UUID.randomUUID().toString());
        sysDataLogRepository.saveAndFlush(sysDataLog);

        int databaseSizeBeforeUpdate = sysDataLogRepository.findAll().size();

        // Update the sysDataLog
        SysDataLog updatedSysDataLog = sysDataLogRepository.findById(sysDataLog.getId()).get();
        // Disconnect from session so that the updates on updatedSysDataLog are not directly saved in db
        em.detach(updatedSysDataLog);
        updatedSysDataLog
            .dataTable(UPDATED_DATA_TABLE)
            .dataId(UPDATED_DATA_ID)
            .dataContent(UPDATED_DATA_CONTENT)
            .dataVersion(UPDATED_DATA_VERSION)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        SysDataLogDTO sysDataLogDTO = sysDataLogMapper.toDto(updatedSysDataLog);

        restSysDataLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysDataLogDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDataLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeUpdate);
        SysDataLog testSysDataLog = sysDataLogList.get(sysDataLogList.size() - 1);
        assertThat(testSysDataLog.getDataTable()).isEqualTo(UPDATED_DATA_TABLE);
        assertThat(testSysDataLog.getDataId()).isEqualTo(UPDATED_DATA_ID);
        assertThat(testSysDataLog.getDataContent()).isEqualTo(UPDATED_DATA_CONTENT);
        assertThat(testSysDataLog.getDataVersion()).isEqualTo(UPDATED_DATA_VERSION);
        assertThat(testSysDataLog.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDataLog.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDataLog.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysDataLog.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDataLog.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysDataLog() throws Exception {
        int databaseSizeBeforeUpdate = sysDataLogRepository.findAll().size();
        sysDataLog.setId(UUID.randomUUID().toString());

        // Create the SysDataLog
        SysDataLogDTO sysDataLogDTO = sysDataLogMapper.toDto(sysDataLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDataLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysDataLogDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDataLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysDataLog() throws Exception {
        int databaseSizeBeforeUpdate = sysDataLogRepository.findAll().size();
        sysDataLog.setId(UUID.randomUUID().toString());

        // Create the SysDataLog
        SysDataLogDTO sysDataLogDTO = sysDataLogMapper.toDto(sysDataLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDataLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDataLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysDataLog() throws Exception {
        int databaseSizeBeforeUpdate = sysDataLogRepository.findAll().size();
        sysDataLog.setId(UUID.randomUUID().toString());

        // Create the SysDataLog
        SysDataLogDTO sysDataLogDTO = sysDataLogMapper.toDto(sysDataLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDataLogMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDataLogDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysDataLogWithPatch() throws Exception {
        // Initialize the database
        sysDataLog.setId(UUID.randomUUID().toString());
        sysDataLogRepository.saveAndFlush(sysDataLog);

        int databaseSizeBeforeUpdate = sysDataLogRepository.findAll().size();

        // Update the sysDataLog using partial update
        SysDataLog partialUpdatedSysDataLog = new SysDataLog();
        partialUpdatedSysDataLog.setId(sysDataLog.getId());

        partialUpdatedSysDataLog
            .dataTable(UPDATED_DATA_TABLE)
            .dataId(UPDATED_DATA_ID)
            .dataContent(UPDATED_DATA_CONTENT)
            .dataVersion(UPDATED_DATA_VERSION)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY);

        restSysDataLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysDataLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysDataLog))
            )
            .andExpect(status().isOk());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeUpdate);
        SysDataLog testSysDataLog = sysDataLogList.get(sysDataLogList.size() - 1);
        assertThat(testSysDataLog.getDataTable()).isEqualTo(UPDATED_DATA_TABLE);
        assertThat(testSysDataLog.getDataId()).isEqualTo(UPDATED_DATA_ID);
        assertThat(testSysDataLog.getDataContent()).isEqualTo(UPDATED_DATA_CONTENT);
        assertThat(testSysDataLog.getDataVersion()).isEqualTo(UPDATED_DATA_VERSION);
        assertThat(testSysDataLog.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysDataLog.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDataLog.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysDataLog.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysDataLog.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysDataLogWithPatch() throws Exception {
        // Initialize the database
        sysDataLog.setId(UUID.randomUUID().toString());
        sysDataLogRepository.saveAndFlush(sysDataLog);

        int databaseSizeBeforeUpdate = sysDataLogRepository.findAll().size();

        // Update the sysDataLog using partial update
        SysDataLog partialUpdatedSysDataLog = new SysDataLog();
        partialUpdatedSysDataLog.setId(sysDataLog.getId());

        partialUpdatedSysDataLog
            .dataTable(UPDATED_DATA_TABLE)
            .dataId(UPDATED_DATA_ID)
            .dataContent(UPDATED_DATA_CONTENT)
            .dataVersion(UPDATED_DATA_VERSION)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysDataLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysDataLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysDataLog))
            )
            .andExpect(status().isOk());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeUpdate);
        SysDataLog testSysDataLog = sysDataLogList.get(sysDataLogList.size() - 1);
        assertThat(testSysDataLog.getDataTable()).isEqualTo(UPDATED_DATA_TABLE);
        assertThat(testSysDataLog.getDataId()).isEqualTo(UPDATED_DATA_ID);
        assertThat(testSysDataLog.getDataContent()).isEqualTo(UPDATED_DATA_CONTENT);
        assertThat(testSysDataLog.getDataVersion()).isEqualTo(UPDATED_DATA_VERSION);
        assertThat(testSysDataLog.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDataLog.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDataLog.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysDataLog.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDataLog.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysDataLog() throws Exception {
        int databaseSizeBeforeUpdate = sysDataLogRepository.findAll().size();
        sysDataLog.setId(UUID.randomUUID().toString());

        // Create the SysDataLog
        SysDataLogDTO sysDataLogDTO = sysDataLogMapper.toDto(sysDataLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDataLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysDataLogDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysDataLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysDataLog() throws Exception {
        int databaseSizeBeforeUpdate = sysDataLogRepository.findAll().size();
        sysDataLog.setId(UUID.randomUUID().toString());

        // Create the SysDataLog
        SysDataLogDTO sysDataLogDTO = sysDataLogMapper.toDto(sysDataLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDataLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysDataLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysDataLog() throws Exception {
        int databaseSizeBeforeUpdate = sysDataLogRepository.findAll().size();
        sysDataLog.setId(UUID.randomUUID().toString());

        // Create the SysDataLog
        SysDataLogDTO sysDataLogDTO = sysDataLogMapper.toDto(sysDataLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDataLogMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysDataLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysDataLog in the database
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysDataLog() throws Exception {
        // Initialize the database
        sysDataLog.setId(UUID.randomUUID().toString());
        sysDataLogRepository.saveAndFlush(sysDataLog);

        int databaseSizeBeforeDelete = sysDataLogRepository.findAll().size();

        // Delete the sysDataLog
        restSysDataLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysDataLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysDataLog> sysDataLogList = sysDataLogRepository.findAll();
        assertThat(sysDataLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
