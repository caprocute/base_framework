package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysDepartRole;
import com.viettel.solution.base.repository.SysDepartRoleRepository;
import com.viettel.solution.base.service.dto.SysDepartRoleDTO;
import com.viettel.solution.base.service.mapper.SysDepartRoleMapper;
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
 * Integration tests for the {@link SysDepartRoleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysDepartRoleResourceIT {

    private static final String DEFAULT_DEPART_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEPART_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/sys-depart-roles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysDepartRoleRepository sysDepartRoleRepository;

    @Autowired
    private SysDepartRoleMapper sysDepartRoleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysDepartRoleMockMvc;

    private SysDepartRole sysDepartRole;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDepartRole createEntity(EntityManager em) {
        SysDepartRole sysDepartRole = new SysDepartRole()
            .departID(DEFAULT_DEPART_ID)
            .roleName(DEFAULT_ROLE_NAME)
            .roleCode(DEFAULT_ROLE_CODE)
            .description(DEFAULT_DESCRIPTION)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateBy(DEFAULT_UPDATE_BY)
            .updateTime(DEFAULT_UPDATE_TIME)
            .tenantId(DEFAULT_TENANT_ID);
        return sysDepartRole;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDepartRole createUpdatedEntity(EntityManager em) {
        SysDepartRole sysDepartRole = new SysDepartRole()
            .departID(UPDATED_DEPART_ID)
            .roleName(UPDATED_ROLE_NAME)
            .roleCode(UPDATED_ROLE_CODE)
            .description(UPDATED_DESCRIPTION)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        return sysDepartRole;
    }

    @BeforeEach
    public void initTest() {
        sysDepartRole = createEntity(em);
    }

    @Test
    @Transactional
    void createSysDepartRole() throws Exception {
        int databaseSizeBeforeCreate = sysDepartRoleRepository.findAll().size();
        // Create the SysDepartRole
        SysDepartRoleDTO sysDepartRoleDTO = sysDepartRoleMapper.toDto(sysDepartRole);
        restSysDepartRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDepartRoleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeCreate + 1);
        SysDepartRole testSysDepartRole = sysDepartRoleList.get(sysDepartRoleList.size() - 1);
        assertThat(testSysDepartRole.getDepartID()).isEqualTo(DEFAULT_DEPART_ID);
        assertThat(testSysDepartRole.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
        assertThat(testSysDepartRole.getRoleCode()).isEqualTo(DEFAULT_ROLE_CODE);
        assertThat(testSysDepartRole.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSysDepartRole.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysDepartRole.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysDepartRole.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysDepartRole.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysDepartRole.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void createSysDepartRoleWithExistingId() throws Exception {
        // Create the SysDepartRole with an existing ID
        sysDepartRole.setId("existing_id");
        SysDepartRoleDTO sysDepartRoleDTO = sysDepartRoleMapper.toDto(sysDepartRole);

        int databaseSizeBeforeCreate = sysDepartRoleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysDepartRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDepartRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysDepartRoles() throws Exception {
        // Initialize the database
        sysDepartRole.setId(UUID.randomUUID().toString());
        sysDepartRoleRepository.saveAndFlush(sysDepartRole);

        // Get all the sysDepartRoleList
        restSysDepartRoleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysDepartRole.getId())))
            .andExpect(jsonPath("$.[*].departID").value(hasItem(DEFAULT_DEPART_ID)))
            .andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME)))
            .andExpect(jsonPath("$.[*].roleCode").value(hasItem(DEFAULT_ROLE_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }

    @Test
    @Transactional
    void getSysDepartRole() throws Exception {
        // Initialize the database
        sysDepartRole.setId(UUID.randomUUID().toString());
        sysDepartRoleRepository.saveAndFlush(sysDepartRole);

        // Get the sysDepartRole
        restSysDepartRoleMockMvc
            .perform(get(ENTITY_API_URL_ID, sysDepartRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysDepartRole.getId()))
            .andExpect(jsonPath("$.departID").value(DEFAULT_DEPART_ID))
            .andExpect(jsonPath("$.roleName").value(DEFAULT_ROLE_NAME))
            .andExpect(jsonPath("$.roleCode").value(DEFAULT_ROLE_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysDepartRole() throws Exception {
        // Get the sysDepartRole
        restSysDepartRoleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysDepartRole() throws Exception {
        // Initialize the database
        sysDepartRole.setId(UUID.randomUUID().toString());
        sysDepartRoleRepository.saveAndFlush(sysDepartRole);

        int databaseSizeBeforeUpdate = sysDepartRoleRepository.findAll().size();

        // Update the sysDepartRole
        SysDepartRole updatedSysDepartRole = sysDepartRoleRepository.findById(sysDepartRole.getId()).get();
        // Disconnect from session so that the updates on updatedSysDepartRole are not directly saved in db
        em.detach(updatedSysDepartRole);
        updatedSysDepartRole
            .departID(UPDATED_DEPART_ID)
            .roleName(UPDATED_ROLE_NAME)
            .roleCode(UPDATED_ROLE_CODE)
            .description(UPDATED_DESCRIPTION)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        SysDepartRoleDTO sysDepartRoleDTO = sysDepartRoleMapper.toDto(updatedSysDepartRole);

        restSysDepartRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysDepartRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartRoleDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeUpdate);
        SysDepartRole testSysDepartRole = sysDepartRoleList.get(sysDepartRoleList.size() - 1);
        assertThat(testSysDepartRole.getDepartID()).isEqualTo(UPDATED_DEPART_ID);
        assertThat(testSysDepartRole.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testSysDepartRole.getRoleCode()).isEqualTo(UPDATED_ROLE_CODE);
        assertThat(testSysDepartRole.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSysDepartRole.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDepartRole.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDepartRole.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysDepartRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDepartRole.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysDepartRole() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRoleRepository.findAll().size();
        sysDepartRole.setId(UUID.randomUUID().toString());

        // Create the SysDepartRole
        SysDepartRoleDTO sysDepartRoleDTO = sysDepartRoleMapper.toDto(sysDepartRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDepartRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysDepartRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysDepartRole() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRoleRepository.findAll().size();
        sysDepartRole.setId(UUID.randomUUID().toString());

        // Create the SysDepartRole
        SysDepartRoleDTO sysDepartRoleDTO = sysDepartRoleMapper.toDto(sysDepartRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDepartRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysDepartRole() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRoleRepository.findAll().size();
        sysDepartRole.setId(UUID.randomUUID().toString());

        // Create the SysDepartRole
        SysDepartRoleDTO sysDepartRoleDTO = sysDepartRoleMapper.toDto(sysDepartRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDepartRoleMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDepartRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysDepartRoleWithPatch() throws Exception {
        // Initialize the database
        sysDepartRole.setId(UUID.randomUUID().toString());
        sysDepartRoleRepository.saveAndFlush(sysDepartRole);

        int databaseSizeBeforeUpdate = sysDepartRoleRepository.findAll().size();

        // Update the sysDepartRole using partial update
        SysDepartRole partialUpdatedSysDepartRole = new SysDepartRole();
        partialUpdatedSysDepartRole.setId(sysDepartRole.getId());

        partialUpdatedSysDepartRole
            .departID(UPDATED_DEPART_ID)
            .roleName(UPDATED_ROLE_NAME)
            .roleCode(UPDATED_ROLE_CODE)
            .createBy(UPDATED_CREATE_BY)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysDepartRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysDepartRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysDepartRole))
            )
            .andExpect(status().isOk());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeUpdate);
        SysDepartRole testSysDepartRole = sysDepartRoleList.get(sysDepartRoleList.size() - 1);
        assertThat(testSysDepartRole.getDepartID()).isEqualTo(UPDATED_DEPART_ID);
        assertThat(testSysDepartRole.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testSysDepartRole.getRoleCode()).isEqualTo(UPDATED_ROLE_CODE);
        assertThat(testSysDepartRole.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSysDepartRole.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDepartRole.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysDepartRole.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysDepartRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDepartRole.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysDepartRoleWithPatch() throws Exception {
        // Initialize the database
        sysDepartRole.setId(UUID.randomUUID().toString());
        sysDepartRoleRepository.saveAndFlush(sysDepartRole);

        int databaseSizeBeforeUpdate = sysDepartRoleRepository.findAll().size();

        // Update the sysDepartRole using partial update
        SysDepartRole partialUpdatedSysDepartRole = new SysDepartRole();
        partialUpdatedSysDepartRole.setId(sysDepartRole.getId());

        partialUpdatedSysDepartRole
            .departID(UPDATED_DEPART_ID)
            .roleName(UPDATED_ROLE_NAME)
            .roleCode(UPDATED_ROLE_CODE)
            .description(UPDATED_DESCRIPTION)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysDepartRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysDepartRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysDepartRole))
            )
            .andExpect(status().isOk());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeUpdate);
        SysDepartRole testSysDepartRole = sysDepartRoleList.get(sysDepartRoleList.size() - 1);
        assertThat(testSysDepartRole.getDepartID()).isEqualTo(UPDATED_DEPART_ID);
        assertThat(testSysDepartRole.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testSysDepartRole.getRoleCode()).isEqualTo(UPDATED_ROLE_CODE);
        assertThat(testSysDepartRole.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSysDepartRole.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDepartRole.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDepartRole.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysDepartRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDepartRole.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysDepartRole() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRoleRepository.findAll().size();
        sysDepartRole.setId(UUID.randomUUID().toString());

        // Create the SysDepartRole
        SysDepartRoleDTO sysDepartRoleDTO = sysDepartRoleMapper.toDto(sysDepartRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDepartRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysDepartRoleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysDepartRole() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRoleRepository.findAll().size();
        sysDepartRole.setId(UUID.randomUUID().toString());

        // Create the SysDepartRole
        SysDepartRoleDTO sysDepartRoleDTO = sysDepartRoleMapper.toDto(sysDepartRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDepartRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysDepartRole() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRoleRepository.findAll().size();
        sysDepartRole.setId(UUID.randomUUID().toString());

        // Create the SysDepartRole
        SysDepartRoleDTO sysDepartRoleDTO = sysDepartRoleMapper.toDto(sysDepartRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDepartRoleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysDepartRole in the database
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysDepartRole() throws Exception {
        // Initialize the database
        sysDepartRole.setId(UUID.randomUUID().toString());
        sysDepartRoleRepository.saveAndFlush(sysDepartRole);

        int databaseSizeBeforeDelete = sysDepartRoleRepository.findAll().size();

        // Delete the sysDepartRole
        restSysDepartRoleMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysDepartRole.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysDepartRole> sysDepartRoleList = sysDepartRoleRepository.findAll();
        assertThat(sysDepartRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
