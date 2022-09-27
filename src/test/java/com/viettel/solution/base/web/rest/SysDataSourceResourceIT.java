package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysDataSource;
import com.viettel.solution.base.repository.SysDataSourceRepository;
import com.viettel.solution.base.service.dto.SysDataSourceDTO;
import com.viettel.solution.base.service.mapper.SysDataSourceMapper;
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
 * Integration tests for the {@link SysDataSourceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysDataSourceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_DB_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DB_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DB_DRIVER = "AAAAAAAAAA";
    private static final String UPDATED_DB_DRIVER = "BBBBBBBBBB";

    private static final String DEFAULT_DB_URL = "AAAAAAAAAA";
    private static final String UPDATED_DB_URL = "BBBBBBBBBB";

    private static final String DEFAULT_DB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DB_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DB_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DB_DROWSSAP = "AAAAAAAAAA";
    private static final String UPDATED_DB_DROWSSAP = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/sys-data-sources";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysDataSourceRepository sysDataSourceRepository;

    @Autowired
    private SysDataSourceMapper sysDataSourceMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysDataSourceMockMvc;

    private SysDataSource sysDataSource;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDataSource createEntity(EntityManager em) {
        SysDataSource sysDataSource = new SysDataSource()
            .name(DEFAULT_NAME)
            .remark(DEFAULT_REMARK)
            .dbType(DEFAULT_DB_TYPE)
            .dbDriver(DEFAULT_DB_DRIVER)
            .dbUrl(DEFAULT_DB_URL)
            .dbName(DEFAULT_DB_NAME)
            .dbUserName(DEFAULT_DB_USER_NAME)
            .dbDrowssap(DEFAULT_DB_DROWSSAP)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateBy(DEFAULT_UPDATE_BY)
            .updateTime(DEFAULT_UPDATE_TIME)
            .tenantId(DEFAULT_TENANT_ID);
        return sysDataSource;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDataSource createUpdatedEntity(EntityManager em) {
        SysDataSource sysDataSource = new SysDataSource()
            .name(UPDATED_NAME)
            .remark(UPDATED_REMARK)
            .dbType(UPDATED_DB_TYPE)
            .dbDriver(UPDATED_DB_DRIVER)
            .dbUrl(UPDATED_DB_URL)
            .dbName(UPDATED_DB_NAME)
            .dbUserName(UPDATED_DB_USER_NAME)
            .dbDrowssap(UPDATED_DB_DROWSSAP)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        return sysDataSource;
    }

    @BeforeEach
    public void initTest() {
        sysDataSource = createEntity(em);
    }

    @Test
    @Transactional
    void createSysDataSource() throws Exception {
        int databaseSizeBeforeCreate = sysDataSourceRepository.findAll().size();
        // Create the SysDataSource
        SysDataSourceDTO sysDataSourceDTO = sysDataSourceMapper.toDto(sysDataSource);
        restSysDataSourceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDataSourceDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeCreate + 1);
        SysDataSource testSysDataSource = sysDataSourceList.get(sysDataSourceList.size() - 1);
        assertThat(testSysDataSource.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysDataSource.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testSysDataSource.getDbType()).isEqualTo(DEFAULT_DB_TYPE);
        assertThat(testSysDataSource.getDbDriver()).isEqualTo(DEFAULT_DB_DRIVER);
        assertThat(testSysDataSource.getDbUrl()).isEqualTo(DEFAULT_DB_URL);
        assertThat(testSysDataSource.getDbName()).isEqualTo(DEFAULT_DB_NAME);
        assertThat(testSysDataSource.getDbUserName()).isEqualTo(DEFAULT_DB_USER_NAME);
        assertThat(testSysDataSource.getDbDrowssap()).isEqualTo(DEFAULT_DB_DROWSSAP);
        assertThat(testSysDataSource.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysDataSource.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysDataSource.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysDataSource.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysDataSource.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void createSysDataSourceWithExistingId() throws Exception {
        // Create the SysDataSource with an existing ID
        sysDataSource.setId("existing_id");
        SysDataSourceDTO sysDataSourceDTO = sysDataSourceMapper.toDto(sysDataSource);

        int databaseSizeBeforeCreate = sysDataSourceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysDataSourceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDataSourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysDataSources() throws Exception {
        // Initialize the database
        sysDataSource.setId(UUID.randomUUID().toString());
        sysDataSourceRepository.saveAndFlush(sysDataSource);

        // Get all the sysDataSourceList
        restSysDataSourceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysDataSource.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].dbType").value(hasItem(DEFAULT_DB_TYPE)))
            .andExpect(jsonPath("$.[*].dbDriver").value(hasItem(DEFAULT_DB_DRIVER)))
            .andExpect(jsonPath("$.[*].dbUrl").value(hasItem(DEFAULT_DB_URL)))
            .andExpect(jsonPath("$.[*].dbName").value(hasItem(DEFAULT_DB_NAME)))
            .andExpect(jsonPath("$.[*].dbUserName").value(hasItem(DEFAULT_DB_USER_NAME)))
            .andExpect(jsonPath("$.[*].dbDrowssap").value(hasItem(DEFAULT_DB_DROWSSAP)))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }

    @Test
    @Transactional
    void getSysDataSource() throws Exception {
        // Initialize the database
        sysDataSource.setId(UUID.randomUUID().toString());
        sysDataSourceRepository.saveAndFlush(sysDataSource);

        // Get the sysDataSource
        restSysDataSourceMockMvc
            .perform(get(ENTITY_API_URL_ID, sysDataSource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysDataSource.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.dbType").value(DEFAULT_DB_TYPE))
            .andExpect(jsonPath("$.dbDriver").value(DEFAULT_DB_DRIVER))
            .andExpect(jsonPath("$.dbUrl").value(DEFAULT_DB_URL))
            .andExpect(jsonPath("$.dbName").value(DEFAULT_DB_NAME))
            .andExpect(jsonPath("$.dbUserName").value(DEFAULT_DB_USER_NAME))
            .andExpect(jsonPath("$.dbDrowssap").value(DEFAULT_DB_DROWSSAP))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysDataSource() throws Exception {
        // Get the sysDataSource
        restSysDataSourceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysDataSource() throws Exception {
        // Initialize the database
        sysDataSource.setId(UUID.randomUUID().toString());
        sysDataSourceRepository.saveAndFlush(sysDataSource);

        int databaseSizeBeforeUpdate = sysDataSourceRepository.findAll().size();

        // Update the sysDataSource
        SysDataSource updatedSysDataSource = sysDataSourceRepository.findById(sysDataSource.getId()).get();
        // Disconnect from session so that the updates on updatedSysDataSource are not directly saved in db
        em.detach(updatedSysDataSource);
        updatedSysDataSource
            .name(UPDATED_NAME)
            .remark(UPDATED_REMARK)
            .dbType(UPDATED_DB_TYPE)
            .dbDriver(UPDATED_DB_DRIVER)
            .dbUrl(UPDATED_DB_URL)
            .dbName(UPDATED_DB_NAME)
            .dbUserName(UPDATED_DB_USER_NAME)
            .dbDrowssap(UPDATED_DB_DROWSSAP)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        SysDataSourceDTO sysDataSourceDTO = sysDataSourceMapper.toDto(updatedSysDataSource);

        restSysDataSourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysDataSourceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDataSourceDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeUpdate);
        SysDataSource testSysDataSource = sysDataSourceList.get(sysDataSourceList.size() - 1);
        assertThat(testSysDataSource.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysDataSource.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSysDataSource.getDbType()).isEqualTo(UPDATED_DB_TYPE);
        assertThat(testSysDataSource.getDbDriver()).isEqualTo(UPDATED_DB_DRIVER);
        assertThat(testSysDataSource.getDbUrl()).isEqualTo(UPDATED_DB_URL);
        assertThat(testSysDataSource.getDbName()).isEqualTo(UPDATED_DB_NAME);
        assertThat(testSysDataSource.getDbUserName()).isEqualTo(UPDATED_DB_USER_NAME);
        assertThat(testSysDataSource.getDbDrowssap()).isEqualTo(UPDATED_DB_DROWSSAP);
        assertThat(testSysDataSource.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDataSource.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDataSource.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysDataSource.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDataSource.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysDataSource() throws Exception {
        int databaseSizeBeforeUpdate = sysDataSourceRepository.findAll().size();
        sysDataSource.setId(UUID.randomUUID().toString());

        // Create the SysDataSource
        SysDataSourceDTO sysDataSourceDTO = sysDataSourceMapper.toDto(sysDataSource);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDataSourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysDataSourceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDataSourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysDataSource() throws Exception {
        int databaseSizeBeforeUpdate = sysDataSourceRepository.findAll().size();
        sysDataSource.setId(UUID.randomUUID().toString());

        // Create the SysDataSource
        SysDataSourceDTO sysDataSourceDTO = sysDataSourceMapper.toDto(sysDataSource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDataSourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDataSourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysDataSource() throws Exception {
        int databaseSizeBeforeUpdate = sysDataSourceRepository.findAll().size();
        sysDataSource.setId(UUID.randomUUID().toString());

        // Create the SysDataSource
        SysDataSourceDTO sysDataSourceDTO = sysDataSourceMapper.toDto(sysDataSource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDataSourceMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDataSourceDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysDataSourceWithPatch() throws Exception {
        // Initialize the database
        sysDataSource.setId(UUID.randomUUID().toString());
        sysDataSourceRepository.saveAndFlush(sysDataSource);

        int databaseSizeBeforeUpdate = sysDataSourceRepository.findAll().size();

        // Update the sysDataSource using partial update
        SysDataSource partialUpdatedSysDataSource = new SysDataSource();
        partialUpdatedSysDataSource.setId(sysDataSource.getId());

        partialUpdatedSysDataSource
            .name(UPDATED_NAME)
            .remark(UPDATED_REMARK)
            .dbType(UPDATED_DB_TYPE)
            .dbUserName(UPDATED_DB_USER_NAME)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysDataSourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysDataSource.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysDataSource))
            )
            .andExpect(status().isOk());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeUpdate);
        SysDataSource testSysDataSource = sysDataSourceList.get(sysDataSourceList.size() - 1);
        assertThat(testSysDataSource.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysDataSource.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSysDataSource.getDbType()).isEqualTo(UPDATED_DB_TYPE);
        assertThat(testSysDataSource.getDbDriver()).isEqualTo(DEFAULT_DB_DRIVER);
        assertThat(testSysDataSource.getDbUrl()).isEqualTo(DEFAULT_DB_URL);
        assertThat(testSysDataSource.getDbName()).isEqualTo(DEFAULT_DB_NAME);
        assertThat(testSysDataSource.getDbUserName()).isEqualTo(UPDATED_DB_USER_NAME);
        assertThat(testSysDataSource.getDbDrowssap()).isEqualTo(DEFAULT_DB_DROWSSAP);
        assertThat(testSysDataSource.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDataSource.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDataSource.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysDataSource.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDataSource.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysDataSourceWithPatch() throws Exception {
        // Initialize the database
        sysDataSource.setId(UUID.randomUUID().toString());
        sysDataSourceRepository.saveAndFlush(sysDataSource);

        int databaseSizeBeforeUpdate = sysDataSourceRepository.findAll().size();

        // Update the sysDataSource using partial update
        SysDataSource partialUpdatedSysDataSource = new SysDataSource();
        partialUpdatedSysDataSource.setId(sysDataSource.getId());

        partialUpdatedSysDataSource
            .name(UPDATED_NAME)
            .remark(UPDATED_REMARK)
            .dbType(UPDATED_DB_TYPE)
            .dbDriver(UPDATED_DB_DRIVER)
            .dbUrl(UPDATED_DB_URL)
            .dbName(UPDATED_DB_NAME)
            .dbUserName(UPDATED_DB_USER_NAME)
            .dbDrowssap(UPDATED_DB_DROWSSAP)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysDataSourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysDataSource.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysDataSource))
            )
            .andExpect(status().isOk());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeUpdate);
        SysDataSource testSysDataSource = sysDataSourceList.get(sysDataSourceList.size() - 1);
        assertThat(testSysDataSource.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysDataSource.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSysDataSource.getDbType()).isEqualTo(UPDATED_DB_TYPE);
        assertThat(testSysDataSource.getDbDriver()).isEqualTo(UPDATED_DB_DRIVER);
        assertThat(testSysDataSource.getDbUrl()).isEqualTo(UPDATED_DB_URL);
        assertThat(testSysDataSource.getDbName()).isEqualTo(UPDATED_DB_NAME);
        assertThat(testSysDataSource.getDbUserName()).isEqualTo(UPDATED_DB_USER_NAME);
        assertThat(testSysDataSource.getDbDrowssap()).isEqualTo(UPDATED_DB_DROWSSAP);
        assertThat(testSysDataSource.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDataSource.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDataSource.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysDataSource.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDataSource.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysDataSource() throws Exception {
        int databaseSizeBeforeUpdate = sysDataSourceRepository.findAll().size();
        sysDataSource.setId(UUID.randomUUID().toString());

        // Create the SysDataSource
        SysDataSourceDTO sysDataSourceDTO = sysDataSourceMapper.toDto(sysDataSource);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDataSourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysDataSourceDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysDataSourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysDataSource() throws Exception {
        int databaseSizeBeforeUpdate = sysDataSourceRepository.findAll().size();
        sysDataSource.setId(UUID.randomUUID().toString());

        // Create the SysDataSource
        SysDataSourceDTO sysDataSourceDTO = sysDataSourceMapper.toDto(sysDataSource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDataSourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysDataSourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysDataSource() throws Exception {
        int databaseSizeBeforeUpdate = sysDataSourceRepository.findAll().size();
        sysDataSource.setId(UUID.randomUUID().toString());

        // Create the SysDataSource
        SysDataSourceDTO sysDataSourceDTO = sysDataSourceMapper.toDto(sysDataSource);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDataSourceMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysDataSourceDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysDataSource in the database
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysDataSource() throws Exception {
        // Initialize the database
        sysDataSource.setId(UUID.randomUUID().toString());
        sysDataSourceRepository.saveAndFlush(sysDataSource);

        int databaseSizeBeforeDelete = sysDataSourceRepository.findAll().size();

        // Delete the sysDataSource
        restSysDataSourceMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysDataSource.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysDataSource> sysDataSourceList = sysDataSourceRepository.findAll();
        assertThat(sysDataSourceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
