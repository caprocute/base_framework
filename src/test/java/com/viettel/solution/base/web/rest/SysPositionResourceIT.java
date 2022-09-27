package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysPosition;
import com.viettel.solution.base.repository.SysPositionRepository;
import com.viettel.solution.base.service.dto.SysPositionDTO;
import com.viettel.solution.base.service.mapper.SysPositionMapper;
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
 * Integration tests for the {@link SysPositionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysPositionResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_POST_RANK = 1;
    private static final Integer UPDATED_POST_RANK = 2;

    private static final String DEFAULT_COMPANY_ID = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_ORG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SYS_ORG_CODE = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/sys-positions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysPositionRepository sysPositionRepository;

    @Autowired
    private SysPositionMapper sysPositionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysPositionMockMvc;

    private SysPosition sysPosition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysPosition createEntity(EntityManager em) {
        SysPosition sysPosition = new SysPosition()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .postRank(DEFAULT_POST_RANK)
            .companyId(DEFAULT_COMPANY_ID)
            .sysOrgCode(DEFAULT_SYS_ORG_CODE)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateBy(DEFAULT_UPDATE_BY)
            .updateTime(DEFAULT_UPDATE_TIME)
            .tenantId(DEFAULT_TENANT_ID);
        return sysPosition;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysPosition createUpdatedEntity(EntityManager em) {
        SysPosition sysPosition = new SysPosition()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .postRank(UPDATED_POST_RANK)
            .companyId(UPDATED_COMPANY_ID)
            .sysOrgCode(UPDATED_SYS_ORG_CODE)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        return sysPosition;
    }

    @BeforeEach
    public void initTest() {
        sysPosition = createEntity(em);
    }

    @Test
    @Transactional
    void createSysPosition() throws Exception {
        int databaseSizeBeforeCreate = sysPositionRepository.findAll().size();
        // Create the SysPosition
        SysPositionDTO sysPositionDTO = sysPositionMapper.toDto(sysPosition);
        restSysPositionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysPositionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeCreate + 1);
        SysPosition testSysPosition = sysPositionList.get(sysPositionList.size() - 1);
        assertThat(testSysPosition.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSysPosition.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysPosition.getPostRank()).isEqualTo(DEFAULT_POST_RANK);
        assertThat(testSysPosition.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testSysPosition.getSysOrgCode()).isEqualTo(DEFAULT_SYS_ORG_CODE);
        assertThat(testSysPosition.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysPosition.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysPosition.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysPosition.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysPosition.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void createSysPositionWithExistingId() throws Exception {
        // Create the SysPosition with an existing ID
        sysPosition.setId("existing_id");
        SysPositionDTO sysPositionDTO = sysPositionMapper.toDto(sysPosition);

        int databaseSizeBeforeCreate = sysPositionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysPositionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysPositionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysPositions() throws Exception {
        // Initialize the database
        sysPosition.setId(UUID.randomUUID().toString());
        sysPositionRepository.saveAndFlush(sysPosition);

        // Get all the sysPositionList
        restSysPositionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysPosition.getId())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].postRank").value(hasItem(DEFAULT_POST_RANK)))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID)))
            .andExpect(jsonPath("$.[*].sysOrgCode").value(hasItem(DEFAULT_SYS_ORG_CODE)))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }

    @Test
    @Transactional
    void getSysPosition() throws Exception {
        // Initialize the database
        sysPosition.setId(UUID.randomUUID().toString());
        sysPositionRepository.saveAndFlush(sysPosition);

        // Get the sysPosition
        restSysPositionMockMvc
            .perform(get(ENTITY_API_URL_ID, sysPosition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysPosition.getId()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.postRank").value(DEFAULT_POST_RANK))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID))
            .andExpect(jsonPath("$.sysOrgCode").value(DEFAULT_SYS_ORG_CODE))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysPosition() throws Exception {
        // Get the sysPosition
        restSysPositionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysPosition() throws Exception {
        // Initialize the database
        sysPosition.setId(UUID.randomUUID().toString());
        sysPositionRepository.saveAndFlush(sysPosition);

        int databaseSizeBeforeUpdate = sysPositionRepository.findAll().size();

        // Update the sysPosition
        SysPosition updatedSysPosition = sysPositionRepository.findById(sysPosition.getId()).get();
        // Disconnect from session so that the updates on updatedSysPosition are not directly saved in db
        em.detach(updatedSysPosition);
        updatedSysPosition
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .postRank(UPDATED_POST_RANK)
            .companyId(UPDATED_COMPANY_ID)
            .sysOrgCode(UPDATED_SYS_ORG_CODE)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        SysPositionDTO sysPositionDTO = sysPositionMapper.toDto(updatedSysPosition);

        restSysPositionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysPositionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPositionDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeUpdate);
        SysPosition testSysPosition = sysPositionList.get(sysPositionList.size() - 1);
        assertThat(testSysPosition.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSysPosition.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysPosition.getPostRank()).isEqualTo(UPDATED_POST_RANK);
        assertThat(testSysPosition.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testSysPosition.getSysOrgCode()).isEqualTo(UPDATED_SYS_ORG_CODE);
        assertThat(testSysPosition.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysPosition.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysPosition.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysPosition.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysPosition.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysPosition() throws Exception {
        int databaseSizeBeforeUpdate = sysPositionRepository.findAll().size();
        sysPosition.setId(UUID.randomUUID().toString());

        // Create the SysPosition
        SysPositionDTO sysPositionDTO = sysPositionMapper.toDto(sysPosition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysPositionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysPositionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPositionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysPosition() throws Exception {
        int databaseSizeBeforeUpdate = sysPositionRepository.findAll().size();
        sysPosition.setId(UUID.randomUUID().toString());

        // Create the SysPosition
        SysPositionDTO sysPositionDTO = sysPositionMapper.toDto(sysPosition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPositionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPositionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysPosition() throws Exception {
        int databaseSizeBeforeUpdate = sysPositionRepository.findAll().size();
        sysPosition.setId(UUID.randomUUID().toString());

        // Create the SysPosition
        SysPositionDTO sysPositionDTO = sysPositionMapper.toDto(sysPosition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPositionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysPositionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysPositionWithPatch() throws Exception {
        // Initialize the database
        sysPosition.setId(UUID.randomUUID().toString());
        sysPositionRepository.saveAndFlush(sysPosition);

        int databaseSizeBeforeUpdate = sysPositionRepository.findAll().size();

        // Update the sysPosition using partial update
        SysPosition partialUpdatedSysPosition = new SysPosition();
        partialUpdatedSysPosition.setId(sysPosition.getId());

        partialUpdatedSysPosition
            .sysOrgCode(UPDATED_SYS_ORG_CODE)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSysPositionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysPosition.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysPosition))
            )
            .andExpect(status().isOk());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeUpdate);
        SysPosition testSysPosition = sysPositionList.get(sysPositionList.size() - 1);
        assertThat(testSysPosition.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSysPosition.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysPosition.getPostRank()).isEqualTo(DEFAULT_POST_RANK);
        assertThat(testSysPosition.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testSysPosition.getSysOrgCode()).isEqualTo(UPDATED_SYS_ORG_CODE);
        assertThat(testSysPosition.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysPosition.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysPosition.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysPosition.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysPosition.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysPositionWithPatch() throws Exception {
        // Initialize the database
        sysPosition.setId(UUID.randomUUID().toString());
        sysPositionRepository.saveAndFlush(sysPosition);

        int databaseSizeBeforeUpdate = sysPositionRepository.findAll().size();

        // Update the sysPosition using partial update
        SysPosition partialUpdatedSysPosition = new SysPosition();
        partialUpdatedSysPosition.setId(sysPosition.getId());

        partialUpdatedSysPosition
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .postRank(UPDATED_POST_RANK)
            .companyId(UPDATED_COMPANY_ID)
            .sysOrgCode(UPDATED_SYS_ORG_CODE)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysPositionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysPosition.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysPosition))
            )
            .andExpect(status().isOk());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeUpdate);
        SysPosition testSysPosition = sysPositionList.get(sysPositionList.size() - 1);
        assertThat(testSysPosition.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSysPosition.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysPosition.getPostRank()).isEqualTo(UPDATED_POST_RANK);
        assertThat(testSysPosition.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testSysPosition.getSysOrgCode()).isEqualTo(UPDATED_SYS_ORG_CODE);
        assertThat(testSysPosition.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysPosition.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysPosition.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysPosition.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysPosition.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysPosition() throws Exception {
        int databaseSizeBeforeUpdate = sysPositionRepository.findAll().size();
        sysPosition.setId(UUID.randomUUID().toString());

        // Create the SysPosition
        SysPositionDTO sysPositionDTO = sysPositionMapper.toDto(sysPosition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysPositionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysPositionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysPositionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysPosition() throws Exception {
        int databaseSizeBeforeUpdate = sysPositionRepository.findAll().size();
        sysPosition.setId(UUID.randomUUID().toString());

        // Create the SysPosition
        SysPositionDTO sysPositionDTO = sysPositionMapper.toDto(sysPosition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPositionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysPositionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysPosition() throws Exception {
        int databaseSizeBeforeUpdate = sysPositionRepository.findAll().size();
        sysPosition.setId(UUID.randomUUID().toString());

        // Create the SysPosition
        SysPositionDTO sysPositionDTO = sysPositionMapper.toDto(sysPosition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPositionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysPositionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysPosition in the database
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysPosition() throws Exception {
        // Initialize the database
        sysPosition.setId(UUID.randomUUID().toString());
        sysPositionRepository.saveAndFlush(sysPosition);

        int databaseSizeBeforeDelete = sysPositionRepository.findAll().size();

        // Delete the sysPosition
        restSysPositionMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysPosition.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysPosition> sysPositionList = sysPositionRepository.findAll();
        assertThat(sysPositionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
