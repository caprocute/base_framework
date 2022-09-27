package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysTenant;
import com.viettel.solution.base.repository.SysTenantRepository;
import com.viettel.solution.base.service.dto.SysTenantDTO;
import com.viettel.solution.base.service.mapper.SysTenantMapper;
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
 * Integration tests for the {@link SysTenantResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysTenantResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/sys-tenants";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysTenantRepository sysTenantRepository;

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysTenantMockMvc;

    private SysTenant sysTenant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysTenant createEntity(EntityManager em) {
        SysTenant sysTenant = new SysTenant()
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateBy(DEFAULT_UPDATE_BY)
            .updateTime(DEFAULT_UPDATE_TIME)
            .tenantId(DEFAULT_TENANT_ID);
        return sysTenant;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysTenant createUpdatedEntity(EntityManager em) {
        SysTenant sysTenant = new SysTenant()
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        return sysTenant;
    }

    @BeforeEach
    public void initTest() {
        sysTenant = createEntity(em);
    }

    @Test
    @Transactional
    void createSysTenant() throws Exception {
        int databaseSizeBeforeCreate = sysTenantRepository.findAll().size();
        // Create the SysTenant
        SysTenantDTO sysTenantDTO = sysTenantMapper.toDto(sysTenant);
        restSysTenantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysTenantDTO)))
            .andExpect(status().isCreated());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeCreate + 1);
        SysTenant testSysTenant = sysTenantList.get(sysTenantList.size() - 1);
        assertThat(testSysTenant.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysTenant.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysTenant.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysTenant.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysTenant.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysTenant.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysTenant.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void createSysTenantWithExistingId() throws Exception {
        // Create the SysTenant with an existing ID
        sysTenant.setId("existing_id");
        SysTenantDTO sysTenantDTO = sysTenantMapper.toDto(sysTenant);

        int databaseSizeBeforeCreate = sysTenantRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysTenantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysTenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysTenants() throws Exception {
        // Initialize the database
        sysTenant.setId(UUID.randomUUID().toString());
        sysTenantRepository.saveAndFlush(sysTenant);

        // Get all the sysTenantList
        restSysTenantMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysTenant.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }

    @Test
    @Transactional
    void getSysTenant() throws Exception {
        // Initialize the database
        sysTenant.setId(UUID.randomUUID().toString());
        sysTenantRepository.saveAndFlush(sysTenant);

        // Get the sysTenant
        restSysTenantMockMvc
            .perform(get(ENTITY_API_URL_ID, sysTenant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysTenant.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysTenant() throws Exception {
        // Get the sysTenant
        restSysTenantMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysTenant() throws Exception {
        // Initialize the database
        sysTenant.setId(UUID.randomUUID().toString());
        sysTenantRepository.saveAndFlush(sysTenant);

        int databaseSizeBeforeUpdate = sysTenantRepository.findAll().size();

        // Update the sysTenant
        SysTenant updatedSysTenant = sysTenantRepository.findById(sysTenant.getId()).get();
        // Disconnect from session so that the updates on updatedSysTenant are not directly saved in db
        em.detach(updatedSysTenant);
        updatedSysTenant
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        SysTenantDTO sysTenantDTO = sysTenantMapper.toDto(updatedSysTenant);

        restSysTenantMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysTenantDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysTenantDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeUpdate);
        SysTenant testSysTenant = sysTenantList.get(sysTenantList.size() - 1);
        assertThat(testSysTenant.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysTenant.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysTenant.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysTenant.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysTenant.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysTenant.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysTenant.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysTenant() throws Exception {
        int databaseSizeBeforeUpdate = sysTenantRepository.findAll().size();
        sysTenant.setId(UUID.randomUUID().toString());

        // Create the SysTenant
        SysTenantDTO sysTenantDTO = sysTenantMapper.toDto(sysTenant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysTenantMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysTenantDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysTenantDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysTenant() throws Exception {
        int databaseSizeBeforeUpdate = sysTenantRepository.findAll().size();
        sysTenant.setId(UUID.randomUUID().toString());

        // Create the SysTenant
        SysTenantDTO sysTenantDTO = sysTenantMapper.toDto(sysTenant);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysTenantMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysTenantDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysTenant() throws Exception {
        int databaseSizeBeforeUpdate = sysTenantRepository.findAll().size();
        sysTenant.setId(UUID.randomUUID().toString());

        // Create the SysTenant
        SysTenantDTO sysTenantDTO = sysTenantMapper.toDto(sysTenant);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysTenantMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysTenantDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysTenantWithPatch() throws Exception {
        // Initialize the database
        sysTenant.setId(UUID.randomUUID().toString());
        sysTenantRepository.saveAndFlush(sysTenant);

        int databaseSizeBeforeUpdate = sysTenantRepository.findAll().size();

        // Update the sysTenant using partial update
        SysTenant partialUpdatedSysTenant = new SysTenant();
        partialUpdatedSysTenant.setId(sysTenant.getId());

        partialUpdatedSysTenant
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY);

        restSysTenantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysTenant.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysTenant))
            )
            .andExpect(status().isOk());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeUpdate);
        SysTenant testSysTenant = sysTenantList.get(sysTenantList.size() - 1);
        assertThat(testSysTenant.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysTenant.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysTenant.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysTenant.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysTenant.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysTenant.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysTenant.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysTenantWithPatch() throws Exception {
        // Initialize the database
        sysTenant.setId(UUID.randomUUID().toString());
        sysTenantRepository.saveAndFlush(sysTenant);

        int databaseSizeBeforeUpdate = sysTenantRepository.findAll().size();

        // Update the sysTenant using partial update
        SysTenant partialUpdatedSysTenant = new SysTenant();
        partialUpdatedSysTenant.setId(sysTenant.getId());

        partialUpdatedSysTenant
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysTenantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysTenant.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysTenant))
            )
            .andExpect(status().isOk());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeUpdate);
        SysTenant testSysTenant = sysTenantList.get(sysTenantList.size() - 1);
        assertThat(testSysTenant.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysTenant.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysTenant.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysTenant.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysTenant.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysTenant.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysTenant.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysTenant() throws Exception {
        int databaseSizeBeforeUpdate = sysTenantRepository.findAll().size();
        sysTenant.setId(UUID.randomUUID().toString());

        // Create the SysTenant
        SysTenantDTO sysTenantDTO = sysTenantMapper.toDto(sysTenant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysTenantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysTenantDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysTenantDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysTenant() throws Exception {
        int databaseSizeBeforeUpdate = sysTenantRepository.findAll().size();
        sysTenant.setId(UUID.randomUUID().toString());

        // Create the SysTenant
        SysTenantDTO sysTenantDTO = sysTenantMapper.toDto(sysTenant);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysTenantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysTenantDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysTenant() throws Exception {
        int databaseSizeBeforeUpdate = sysTenantRepository.findAll().size();
        sysTenant.setId(UUID.randomUUID().toString());

        // Create the SysTenant
        SysTenantDTO sysTenantDTO = sysTenantMapper.toDto(sysTenant);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysTenantMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysTenantDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysTenant in the database
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysTenant() throws Exception {
        // Initialize the database
        sysTenant.setId(UUID.randomUUID().toString());
        sysTenantRepository.saveAndFlush(sysTenant);

        int databaseSizeBeforeDelete = sysTenantRepository.findAll().size();

        // Delete the sysTenant
        restSysTenantMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysTenant.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysTenant> sysTenantList = sysTenantRepository.findAll();
        assertThat(sysTenantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
