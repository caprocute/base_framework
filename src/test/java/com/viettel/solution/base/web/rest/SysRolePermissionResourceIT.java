package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysRolePermission;
import com.viettel.solution.base.repository.SysRolePermissionRepository;
import com.viettel.solution.base.service.dto.SysRolePermissionDTO;
import com.viettel.solution.base.service.mapper.SysRolePermissionMapper;
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
 * Integration tests for the {@link SysRolePermissionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysRolePermissionResourceIT {

    private static final String DEFAULT_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PERMISSION_ID = "AAAAAAAAAA";
    private static final String UPDATED_PERMISSION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_RULE_IDS = "AAAAAAAAAA";
    private static final String UPDATED_DATA_RULE_IDS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OPERATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OPERATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OPERATE_IP = "AAAAAAAAAA";
    private static final String UPDATED_OPERATE_IP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sys-role-permissions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysRolePermissionRepository sysRolePermissionRepository;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysRolePermissionMockMvc;

    private SysRolePermission sysRolePermission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRolePermission createEntity(EntityManager em) {
        SysRolePermission sysRolePermission = new SysRolePermission()
            .roleId(DEFAULT_ROLE_ID)
            .permissionId(DEFAULT_PERMISSION_ID)
            .dataRuleIds(DEFAULT_DATA_RULE_IDS)
            .operateDate(DEFAULT_OPERATE_DATE)
            .operateIp(DEFAULT_OPERATE_IP);
        return sysRolePermission;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRolePermission createUpdatedEntity(EntityManager em) {
        SysRolePermission sysRolePermission = new SysRolePermission()
            .roleId(UPDATED_ROLE_ID)
            .permissionId(UPDATED_PERMISSION_ID)
            .dataRuleIds(UPDATED_DATA_RULE_IDS)
            .operateDate(UPDATED_OPERATE_DATE)
            .operateIp(UPDATED_OPERATE_IP);
        return sysRolePermission;
    }

    @BeforeEach
    public void initTest() {
        sysRolePermission = createEntity(em);
    }

    @Test
    @Transactional
    void createSysRolePermission() throws Exception {
        int databaseSizeBeforeCreate = sysRolePermissionRepository.findAll().size();
        // Create the SysRolePermission
        SysRolePermissionDTO sysRolePermissionDTO = sysRolePermissionMapper.toDto(sysRolePermission);
        restSysRolePermissionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRolePermissionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeCreate + 1);
        SysRolePermission testSysRolePermission = sysRolePermissionList.get(sysRolePermissionList.size() - 1);
        assertThat(testSysRolePermission.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testSysRolePermission.getPermissionId()).isEqualTo(DEFAULT_PERMISSION_ID);
        assertThat(testSysRolePermission.getDataRuleIds()).isEqualTo(DEFAULT_DATA_RULE_IDS);
        assertThat(testSysRolePermission.getOperateDate()).isEqualTo(DEFAULT_OPERATE_DATE);
        assertThat(testSysRolePermission.getOperateIp()).isEqualTo(DEFAULT_OPERATE_IP);
    }

    @Test
    @Transactional
    void createSysRolePermissionWithExistingId() throws Exception {
        // Create the SysRolePermission with an existing ID
        sysRolePermission.setId("existing_id");
        SysRolePermissionDTO sysRolePermissionDTO = sysRolePermissionMapper.toDto(sysRolePermission);

        int databaseSizeBeforeCreate = sysRolePermissionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysRolePermissionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRolePermissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysRolePermissions() throws Exception {
        // Initialize the database
        sysRolePermission.setId(UUID.randomUUID().toString());
        sysRolePermissionRepository.saveAndFlush(sysRolePermission);

        // Get all the sysRolePermissionList
        restSysRolePermissionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRolePermission.getId())))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID)))
            .andExpect(jsonPath("$.[*].permissionId").value(hasItem(DEFAULT_PERMISSION_ID)))
            .andExpect(jsonPath("$.[*].dataRuleIds").value(hasItem(DEFAULT_DATA_RULE_IDS)))
            .andExpect(jsonPath("$.[*].operateDate").value(hasItem(DEFAULT_OPERATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].operateIp").value(hasItem(DEFAULT_OPERATE_IP)));
    }

    @Test
    @Transactional
    void getSysRolePermission() throws Exception {
        // Initialize the database
        sysRolePermission.setId(UUID.randomUUID().toString());
        sysRolePermissionRepository.saveAndFlush(sysRolePermission);

        // Get the sysRolePermission
        restSysRolePermissionMockMvc
            .perform(get(ENTITY_API_URL_ID, sysRolePermission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysRolePermission.getId()))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID))
            .andExpect(jsonPath("$.permissionId").value(DEFAULT_PERMISSION_ID))
            .andExpect(jsonPath("$.dataRuleIds").value(DEFAULT_DATA_RULE_IDS))
            .andExpect(jsonPath("$.operateDate").value(DEFAULT_OPERATE_DATE.toString()))
            .andExpect(jsonPath("$.operateIp").value(DEFAULT_OPERATE_IP));
    }

    @Test
    @Transactional
    void getNonExistingSysRolePermission() throws Exception {
        // Get the sysRolePermission
        restSysRolePermissionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysRolePermission() throws Exception {
        // Initialize the database
        sysRolePermission.setId(UUID.randomUUID().toString());
        sysRolePermissionRepository.saveAndFlush(sysRolePermission);

        int databaseSizeBeforeUpdate = sysRolePermissionRepository.findAll().size();

        // Update the sysRolePermission
        SysRolePermission updatedSysRolePermission = sysRolePermissionRepository.findById(sysRolePermission.getId()).get();
        // Disconnect from session so that the updates on updatedSysRolePermission are not directly saved in db
        em.detach(updatedSysRolePermission);
        updatedSysRolePermission
            .roleId(UPDATED_ROLE_ID)
            .permissionId(UPDATED_PERMISSION_ID)
            .dataRuleIds(UPDATED_DATA_RULE_IDS)
            .operateDate(UPDATED_OPERATE_DATE)
            .operateIp(UPDATED_OPERATE_IP);
        SysRolePermissionDTO sysRolePermissionDTO = sysRolePermissionMapper.toDto(updatedSysRolePermission);

        restSysRolePermissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysRolePermissionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRolePermissionDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeUpdate);
        SysRolePermission testSysRolePermission = sysRolePermissionList.get(sysRolePermissionList.size() - 1);
        assertThat(testSysRolePermission.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testSysRolePermission.getPermissionId()).isEqualTo(UPDATED_PERMISSION_ID);
        assertThat(testSysRolePermission.getDataRuleIds()).isEqualTo(UPDATED_DATA_RULE_IDS);
        assertThat(testSysRolePermission.getOperateDate()).isEqualTo(UPDATED_OPERATE_DATE);
        assertThat(testSysRolePermission.getOperateIp()).isEqualTo(UPDATED_OPERATE_IP);
    }

    @Test
    @Transactional
    void putNonExistingSysRolePermission() throws Exception {
        int databaseSizeBeforeUpdate = sysRolePermissionRepository.findAll().size();
        sysRolePermission.setId(UUID.randomUUID().toString());

        // Create the SysRolePermission
        SysRolePermissionDTO sysRolePermissionDTO = sysRolePermissionMapper.toDto(sysRolePermission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRolePermissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysRolePermissionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRolePermissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysRolePermission() throws Exception {
        int databaseSizeBeforeUpdate = sysRolePermissionRepository.findAll().size();
        sysRolePermission.setId(UUID.randomUUID().toString());

        // Create the SysRolePermission
        SysRolePermissionDTO sysRolePermissionDTO = sysRolePermissionMapper.toDto(sysRolePermission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRolePermissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRolePermissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysRolePermission() throws Exception {
        int databaseSizeBeforeUpdate = sysRolePermissionRepository.findAll().size();
        sysRolePermission.setId(UUID.randomUUID().toString());

        // Create the SysRolePermission
        SysRolePermissionDTO sysRolePermissionDTO = sysRolePermissionMapper.toDto(sysRolePermission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRolePermissionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysRolePermissionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysRolePermissionWithPatch() throws Exception {
        // Initialize the database
        sysRolePermission.setId(UUID.randomUUID().toString());
        sysRolePermissionRepository.saveAndFlush(sysRolePermission);

        int databaseSizeBeforeUpdate = sysRolePermissionRepository.findAll().size();

        // Update the sysRolePermission using partial update
        SysRolePermission partialUpdatedSysRolePermission = new SysRolePermission();
        partialUpdatedSysRolePermission.setId(sysRolePermission.getId());

        partialUpdatedSysRolePermission
            .permissionId(UPDATED_PERMISSION_ID)
            .dataRuleIds(UPDATED_DATA_RULE_IDS)
            .operateIp(UPDATED_OPERATE_IP);

        restSysRolePermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysRolePermission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysRolePermission))
            )
            .andExpect(status().isOk());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeUpdate);
        SysRolePermission testSysRolePermission = sysRolePermissionList.get(sysRolePermissionList.size() - 1);
        assertThat(testSysRolePermission.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testSysRolePermission.getPermissionId()).isEqualTo(UPDATED_PERMISSION_ID);
        assertThat(testSysRolePermission.getDataRuleIds()).isEqualTo(UPDATED_DATA_RULE_IDS);
        assertThat(testSysRolePermission.getOperateDate()).isEqualTo(DEFAULT_OPERATE_DATE);
        assertThat(testSysRolePermission.getOperateIp()).isEqualTo(UPDATED_OPERATE_IP);
    }

    @Test
    @Transactional
    void fullUpdateSysRolePermissionWithPatch() throws Exception {
        // Initialize the database
        sysRolePermission.setId(UUID.randomUUID().toString());
        sysRolePermissionRepository.saveAndFlush(sysRolePermission);

        int databaseSizeBeforeUpdate = sysRolePermissionRepository.findAll().size();

        // Update the sysRolePermission using partial update
        SysRolePermission partialUpdatedSysRolePermission = new SysRolePermission();
        partialUpdatedSysRolePermission.setId(sysRolePermission.getId());

        partialUpdatedSysRolePermission
            .roleId(UPDATED_ROLE_ID)
            .permissionId(UPDATED_PERMISSION_ID)
            .dataRuleIds(UPDATED_DATA_RULE_IDS)
            .operateDate(UPDATED_OPERATE_DATE)
            .operateIp(UPDATED_OPERATE_IP);

        restSysRolePermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysRolePermission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysRolePermission))
            )
            .andExpect(status().isOk());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeUpdate);
        SysRolePermission testSysRolePermission = sysRolePermissionList.get(sysRolePermissionList.size() - 1);
        assertThat(testSysRolePermission.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testSysRolePermission.getPermissionId()).isEqualTo(UPDATED_PERMISSION_ID);
        assertThat(testSysRolePermission.getDataRuleIds()).isEqualTo(UPDATED_DATA_RULE_IDS);
        assertThat(testSysRolePermission.getOperateDate()).isEqualTo(UPDATED_OPERATE_DATE);
        assertThat(testSysRolePermission.getOperateIp()).isEqualTo(UPDATED_OPERATE_IP);
    }

    @Test
    @Transactional
    void patchNonExistingSysRolePermission() throws Exception {
        int databaseSizeBeforeUpdate = sysRolePermissionRepository.findAll().size();
        sysRolePermission.setId(UUID.randomUUID().toString());

        // Create the SysRolePermission
        SysRolePermissionDTO sysRolePermissionDTO = sysRolePermissionMapper.toDto(sysRolePermission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRolePermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysRolePermissionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysRolePermissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysRolePermission() throws Exception {
        int databaseSizeBeforeUpdate = sysRolePermissionRepository.findAll().size();
        sysRolePermission.setId(UUID.randomUUID().toString());

        // Create the SysRolePermission
        SysRolePermissionDTO sysRolePermissionDTO = sysRolePermissionMapper.toDto(sysRolePermission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRolePermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysRolePermissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysRolePermission() throws Exception {
        int databaseSizeBeforeUpdate = sysRolePermissionRepository.findAll().size();
        sysRolePermission.setId(UUID.randomUUID().toString());

        // Create the SysRolePermission
        SysRolePermissionDTO sysRolePermissionDTO = sysRolePermissionMapper.toDto(sysRolePermission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRolePermissionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysRolePermissionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysRolePermission in the database
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysRolePermission() throws Exception {
        // Initialize the database
        sysRolePermission.setId(UUID.randomUUID().toString());
        sysRolePermissionRepository.saveAndFlush(sysRolePermission);

        int databaseSizeBeforeDelete = sysRolePermissionRepository.findAll().size();

        // Delete the sysRolePermission
        restSysRolePermissionMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysRolePermission.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionRepository.findAll();
        assertThat(sysRolePermissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
