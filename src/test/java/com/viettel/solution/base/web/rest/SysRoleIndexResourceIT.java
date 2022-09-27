package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysRoleIndex;
import com.viettel.solution.base.repository.SysRoleIndexRepository;
import com.viettel.solution.base.service.dto.SysRoleIndexDTO;
import com.viettel.solution.base.service.mapper.SysRoleIndexMapper;
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
 * Integration tests for the {@link SysRoleIndexResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysRoleIndexResourceIT {

    private static final String DEFAULT_ROLE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_COMPONENT = "AAAAAAAAAA";
    private static final String UPDATED_COMPONENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ROUTE = false;
    private static final Boolean UPDATED_IS_ROUTE = true;

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

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

    private static final String ENTITY_API_URL = "/api/sys-role-indices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysRoleIndexRepository sysRoleIndexRepository;

    @Autowired
    private SysRoleIndexMapper sysRoleIndexMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysRoleIndexMockMvc;

    private SysRoleIndex sysRoleIndex;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRoleIndex createEntity(EntityManager em) {
        SysRoleIndex sysRoleIndex = new SysRoleIndex()
            .roleCode(DEFAULT_ROLE_CODE)
            .url(DEFAULT_URL)
            .component(DEFAULT_COMPONENT)
            .isRoute(DEFAULT_IS_ROUTE)
            .priority(DEFAULT_PRIORITY)
            .status(DEFAULT_STATUS)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateBy(DEFAULT_UPDATE_BY)
            .updateTime(DEFAULT_UPDATE_TIME)
            .tenantId(DEFAULT_TENANT_ID);
        return sysRoleIndex;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRoleIndex createUpdatedEntity(EntityManager em) {
        SysRoleIndex sysRoleIndex = new SysRoleIndex()
            .roleCode(UPDATED_ROLE_CODE)
            .url(UPDATED_URL)
            .component(UPDATED_COMPONENT)
            .isRoute(UPDATED_IS_ROUTE)
            .priority(UPDATED_PRIORITY)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        return sysRoleIndex;
    }

    @BeforeEach
    public void initTest() {
        sysRoleIndex = createEntity(em);
    }

    @Test
    @Transactional
    void createSysRoleIndex() throws Exception {
        int databaseSizeBeforeCreate = sysRoleIndexRepository.findAll().size();
        // Create the SysRoleIndex
        SysRoleIndexDTO sysRoleIndexDTO = sysRoleIndexMapper.toDto(sysRoleIndex);
        restSysRoleIndexMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysRoleIndexDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeCreate + 1);
        SysRoleIndex testSysRoleIndex = sysRoleIndexList.get(sysRoleIndexList.size() - 1);
        assertThat(testSysRoleIndex.getRoleCode()).isEqualTo(DEFAULT_ROLE_CODE);
        assertThat(testSysRoleIndex.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testSysRoleIndex.getComponent()).isEqualTo(DEFAULT_COMPONENT);
        assertThat(testSysRoleIndex.getIsRoute()).isEqualTo(DEFAULT_IS_ROUTE);
        assertThat(testSysRoleIndex.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testSysRoleIndex.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysRoleIndex.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysRoleIndex.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysRoleIndex.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysRoleIndex.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysRoleIndex.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void createSysRoleIndexWithExistingId() throws Exception {
        // Create the SysRoleIndex with an existing ID
        sysRoleIndex.setId("existing_id");
        SysRoleIndexDTO sysRoleIndexDTO = sysRoleIndexMapper.toDto(sysRoleIndex);

        int databaseSizeBeforeCreate = sysRoleIndexRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysRoleIndexMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysRoleIndexDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysRoleIndices() throws Exception {
        // Initialize the database
        sysRoleIndex.setId(UUID.randomUUID().toString());
        sysRoleIndexRepository.saveAndFlush(sysRoleIndex);

        // Get all the sysRoleIndexList
        restSysRoleIndexMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRoleIndex.getId())))
            .andExpect(jsonPath("$.[*].roleCode").value(hasItem(DEFAULT_ROLE_CODE)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].component").value(hasItem(DEFAULT_COMPONENT)))
            .andExpect(jsonPath("$.[*].isRoute").value(hasItem(DEFAULT_IS_ROUTE.booleanValue())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }

    @Test
    @Transactional
    void getSysRoleIndex() throws Exception {
        // Initialize the database
        sysRoleIndex.setId(UUID.randomUUID().toString());
        sysRoleIndexRepository.saveAndFlush(sysRoleIndex);

        // Get the sysRoleIndex
        restSysRoleIndexMockMvc
            .perform(get(ENTITY_API_URL_ID, sysRoleIndex.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysRoleIndex.getId()))
            .andExpect(jsonPath("$.roleCode").value(DEFAULT_ROLE_CODE))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.component").value(DEFAULT_COMPONENT))
            .andExpect(jsonPath("$.isRoute").value(DEFAULT_IS_ROUTE.booleanValue()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysRoleIndex() throws Exception {
        // Get the sysRoleIndex
        restSysRoleIndexMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysRoleIndex() throws Exception {
        // Initialize the database
        sysRoleIndex.setId(UUID.randomUUID().toString());
        sysRoleIndexRepository.saveAndFlush(sysRoleIndex);

        int databaseSizeBeforeUpdate = sysRoleIndexRepository.findAll().size();

        // Update the sysRoleIndex
        SysRoleIndex updatedSysRoleIndex = sysRoleIndexRepository.findById(sysRoleIndex.getId()).get();
        // Disconnect from session so that the updates on updatedSysRoleIndex are not directly saved in db
        em.detach(updatedSysRoleIndex);
        updatedSysRoleIndex
            .roleCode(UPDATED_ROLE_CODE)
            .url(UPDATED_URL)
            .component(UPDATED_COMPONENT)
            .isRoute(UPDATED_IS_ROUTE)
            .priority(UPDATED_PRIORITY)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        SysRoleIndexDTO sysRoleIndexDTO = sysRoleIndexMapper.toDto(updatedSysRoleIndex);

        restSysRoleIndexMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysRoleIndexDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleIndexDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeUpdate);
        SysRoleIndex testSysRoleIndex = sysRoleIndexList.get(sysRoleIndexList.size() - 1);
        assertThat(testSysRoleIndex.getRoleCode()).isEqualTo(UPDATED_ROLE_CODE);
        assertThat(testSysRoleIndex.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testSysRoleIndex.getComponent()).isEqualTo(UPDATED_COMPONENT);
        assertThat(testSysRoleIndex.getIsRoute()).isEqualTo(UPDATED_IS_ROUTE);
        assertThat(testSysRoleIndex.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testSysRoleIndex.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysRoleIndex.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysRoleIndex.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysRoleIndex.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysRoleIndex.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysRoleIndex.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysRoleIndex() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleIndexRepository.findAll().size();
        sysRoleIndex.setId(UUID.randomUUID().toString());

        // Create the SysRoleIndex
        SysRoleIndexDTO sysRoleIndexDTO = sysRoleIndexMapper.toDto(sysRoleIndex);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRoleIndexMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysRoleIndexDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleIndexDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysRoleIndex() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleIndexRepository.findAll().size();
        sysRoleIndex.setId(UUID.randomUUID().toString());

        // Create the SysRoleIndex
        SysRoleIndexDTO sysRoleIndexDTO = sysRoleIndexMapper.toDto(sysRoleIndex);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRoleIndexMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleIndexDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysRoleIndex() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleIndexRepository.findAll().size();
        sysRoleIndex.setId(UUID.randomUUID().toString());

        // Create the SysRoleIndex
        SysRoleIndexDTO sysRoleIndexDTO = sysRoleIndexMapper.toDto(sysRoleIndex);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRoleIndexMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysRoleIndexDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysRoleIndexWithPatch() throws Exception {
        // Initialize the database
        sysRoleIndex.setId(UUID.randomUUID().toString());
        sysRoleIndexRepository.saveAndFlush(sysRoleIndex);

        int databaseSizeBeforeUpdate = sysRoleIndexRepository.findAll().size();

        // Update the sysRoleIndex using partial update
        SysRoleIndex partialUpdatedSysRoleIndex = new SysRoleIndex();
        partialUpdatedSysRoleIndex.setId(sysRoleIndex.getId());

        partialUpdatedSysRoleIndex.isRoute(UPDATED_IS_ROUTE).createTime(UPDATED_CREATE_TIME).tenantId(UPDATED_TENANT_ID);

        restSysRoleIndexMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysRoleIndex.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysRoleIndex))
            )
            .andExpect(status().isOk());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeUpdate);
        SysRoleIndex testSysRoleIndex = sysRoleIndexList.get(sysRoleIndexList.size() - 1);
        assertThat(testSysRoleIndex.getRoleCode()).isEqualTo(DEFAULT_ROLE_CODE);
        assertThat(testSysRoleIndex.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testSysRoleIndex.getComponent()).isEqualTo(DEFAULT_COMPONENT);
        assertThat(testSysRoleIndex.getIsRoute()).isEqualTo(UPDATED_IS_ROUTE);
        assertThat(testSysRoleIndex.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testSysRoleIndex.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysRoleIndex.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysRoleIndex.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysRoleIndex.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysRoleIndex.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysRoleIndex.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysRoleIndexWithPatch() throws Exception {
        // Initialize the database
        sysRoleIndex.setId(UUID.randomUUID().toString());
        sysRoleIndexRepository.saveAndFlush(sysRoleIndex);

        int databaseSizeBeforeUpdate = sysRoleIndexRepository.findAll().size();

        // Update the sysRoleIndex using partial update
        SysRoleIndex partialUpdatedSysRoleIndex = new SysRoleIndex();
        partialUpdatedSysRoleIndex.setId(sysRoleIndex.getId());

        partialUpdatedSysRoleIndex
            .roleCode(UPDATED_ROLE_CODE)
            .url(UPDATED_URL)
            .component(UPDATED_COMPONENT)
            .isRoute(UPDATED_IS_ROUTE)
            .priority(UPDATED_PRIORITY)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysRoleIndexMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysRoleIndex.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysRoleIndex))
            )
            .andExpect(status().isOk());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeUpdate);
        SysRoleIndex testSysRoleIndex = sysRoleIndexList.get(sysRoleIndexList.size() - 1);
        assertThat(testSysRoleIndex.getRoleCode()).isEqualTo(UPDATED_ROLE_CODE);
        assertThat(testSysRoleIndex.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testSysRoleIndex.getComponent()).isEqualTo(UPDATED_COMPONENT);
        assertThat(testSysRoleIndex.getIsRoute()).isEqualTo(UPDATED_IS_ROUTE);
        assertThat(testSysRoleIndex.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testSysRoleIndex.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysRoleIndex.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysRoleIndex.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysRoleIndex.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysRoleIndex.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysRoleIndex.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysRoleIndex() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleIndexRepository.findAll().size();
        sysRoleIndex.setId(UUID.randomUUID().toString());

        // Create the SysRoleIndex
        SysRoleIndexDTO sysRoleIndexDTO = sysRoleIndexMapper.toDto(sysRoleIndex);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRoleIndexMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysRoleIndexDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleIndexDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysRoleIndex() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleIndexRepository.findAll().size();
        sysRoleIndex.setId(UUID.randomUUID().toString());

        // Create the SysRoleIndex
        SysRoleIndexDTO sysRoleIndexDTO = sysRoleIndexMapper.toDto(sysRoleIndex);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRoleIndexMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleIndexDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysRoleIndex() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleIndexRepository.findAll().size();
        sysRoleIndex.setId(UUID.randomUUID().toString());

        // Create the SysRoleIndex
        SysRoleIndexDTO sysRoleIndexDTO = sysRoleIndexMapper.toDto(sysRoleIndex);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRoleIndexMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleIndexDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysRoleIndex in the database
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysRoleIndex() throws Exception {
        // Initialize the database
        sysRoleIndex.setId(UUID.randomUUID().toString());
        sysRoleIndexRepository.saveAndFlush(sysRoleIndex);

        int databaseSizeBeforeDelete = sysRoleIndexRepository.findAll().size();

        // Delete the sysRoleIndex
        restSysRoleIndexMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysRoleIndex.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysRoleIndex> sysRoleIndexList = sysRoleIndexRepository.findAll();
        assertThat(sysRoleIndexList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
