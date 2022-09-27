package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysUserRole;
import com.viettel.solution.base.repository.SysUserRoleRepository;
import com.viettel.solution.base.service.dto.SysUserRoleDTO;
import com.viettel.solution.base.service.mapper.SysUserRoleMapper;
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
 * Integration tests for the {@link SysUserRoleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysUserRoleResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sys-user-roles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysUserRoleMockMvc;

    private SysUserRole sysUserRole;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysUserRole createEntity(EntityManager em) {
        SysUserRole sysUserRole = new SysUserRole().userId(DEFAULT_USER_ID).roleId(DEFAULT_ROLE_ID);
        return sysUserRole;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysUserRole createUpdatedEntity(EntityManager em) {
        SysUserRole sysUserRole = new SysUserRole().userId(UPDATED_USER_ID).roleId(UPDATED_ROLE_ID);
        return sysUserRole;
    }

    @BeforeEach
    public void initTest() {
        sysUserRole = createEntity(em);
    }

    @Test
    @Transactional
    void createSysUserRole() throws Exception {
        int databaseSizeBeforeCreate = sysUserRoleRepository.findAll().size();
        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);
        restSysUserRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeCreate + 1);
        SysUserRole testSysUserRole = sysUserRoleList.get(sysUserRoleList.size() - 1);
        assertThat(testSysUserRole.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testSysUserRole.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
    }

    @Test
    @Transactional
    void createSysUserRoleWithExistingId() throws Exception {
        // Create the SysUserRole with an existing ID
        sysUserRole.setId("existing_id");
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        int databaseSizeBeforeCreate = sysUserRoleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysUserRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysUserRoles() throws Exception {
        // Initialize the database
        sysUserRole.setId(UUID.randomUUID().toString());
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get all the sysUserRoleList
        restSysUserRoleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUserRole.getId())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID)));
    }

    @Test
    @Transactional
    void getSysUserRole() throws Exception {
        // Initialize the database
        sysUserRole.setId(UUID.randomUUID().toString());
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get the sysUserRole
        restSysUserRoleMockMvc
            .perform(get(ENTITY_API_URL_ID, sysUserRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysUserRole.getId()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysUserRole() throws Exception {
        // Get the sysUserRole
        restSysUserRoleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysUserRole() throws Exception {
        // Initialize the database
        sysUserRole.setId(UUID.randomUUID().toString());
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();

        // Update the sysUserRole
        SysUserRole updatedSysUserRole = sysUserRoleRepository.findById(sysUserRole.getId()).get();
        // Disconnect from session so that the updates on updatedSysUserRole are not directly saved in db
        em.detach(updatedSysUserRole);
        updatedSysUserRole.userId(UPDATED_USER_ID).roleId(UPDATED_ROLE_ID);
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(updatedSysUserRole);

        restSysUserRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysUserRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
        SysUserRole testSysUserRole = sysUserRoleList.get(sysUserRoleList.size() - 1);
        assertThat(testSysUserRole.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSysUserRole.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(UUID.randomUUID().toString());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysUserRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(UUID.randomUUID().toString());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(UUID.randomUUID().toString());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysUserRoleWithPatch() throws Exception {
        // Initialize the database
        sysUserRole.setId(UUID.randomUUID().toString());
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();

        // Update the sysUserRole using partial update
        SysUserRole partialUpdatedSysUserRole = new SysUserRole();
        partialUpdatedSysUserRole.setId(sysUserRole.getId());

        partialUpdatedSysUserRole.userId(UPDATED_USER_ID).roleId(UPDATED_ROLE_ID);

        restSysUserRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysUserRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysUserRole))
            )
            .andExpect(status().isOk());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
        SysUserRole testSysUserRole = sysUserRoleList.get(sysUserRoleList.size() - 1);
        assertThat(testSysUserRole.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSysUserRole.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysUserRoleWithPatch() throws Exception {
        // Initialize the database
        sysUserRole.setId(UUID.randomUUID().toString());
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();

        // Update the sysUserRole using partial update
        SysUserRole partialUpdatedSysUserRole = new SysUserRole();
        partialUpdatedSysUserRole.setId(sysUserRole.getId());

        partialUpdatedSysUserRole.userId(UPDATED_USER_ID).roleId(UPDATED_ROLE_ID);

        restSysUserRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysUserRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysUserRole))
            )
            .andExpect(status().isOk());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
        SysUserRole testSysUserRole = sysUserRoleList.get(sysUserRoleList.size() - 1);
        assertThat(testSysUserRole.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSysUserRole.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(UUID.randomUUID().toString());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysUserRoleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(UUID.randomUUID().toString());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(UUID.randomUUID().toString());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysUserRole() throws Exception {
        // Initialize the database
        sysUserRole.setId(UUID.randomUUID().toString());
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        int databaseSizeBeforeDelete = sysUserRoleRepository.findAll().size();

        // Delete the sysUserRole
        restSysUserRoleMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysUserRole.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
