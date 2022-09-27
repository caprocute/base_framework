package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysUserDepart;
import com.viettel.solution.base.repository.SysUserDepartRepository;
import com.viettel.solution.base.service.dto.SysUserDepartDTO;
import com.viettel.solution.base.service.mapper.SysUserDepartMapper;
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
 * Integration tests for the {@link SysUserDepartResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysUserDepartResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEP_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEP_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sys-user-departs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysUserDepartRepository sysUserDepartRepository;

    @Autowired
    private SysUserDepartMapper sysUserDepartMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysUserDepartMockMvc;

    private SysUserDepart sysUserDepart;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysUserDepart createEntity(EntityManager em) {
        SysUserDepart sysUserDepart = new SysUserDepart().userId(DEFAULT_USER_ID).depID(DEFAULT_DEP_ID);
        return sysUserDepart;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysUserDepart createUpdatedEntity(EntityManager em) {
        SysUserDepart sysUserDepart = new SysUserDepart().userId(UPDATED_USER_ID).depID(UPDATED_DEP_ID);
        return sysUserDepart;
    }

    @BeforeEach
    public void initTest() {
        sysUserDepart = createEntity(em);
    }

    @Test
    @Transactional
    void createSysUserDepart() throws Exception {
        int databaseSizeBeforeCreate = sysUserDepartRepository.findAll().size();
        // Create the SysUserDepart
        SysUserDepartDTO sysUserDepartDTO = sysUserDepartMapper.toDto(sysUserDepart);
        restSysUserDepartMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysUserDepartDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeCreate + 1);
        SysUserDepart testSysUserDepart = sysUserDepartList.get(sysUserDepartList.size() - 1);
        assertThat(testSysUserDepart.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testSysUserDepart.getDepID()).isEqualTo(DEFAULT_DEP_ID);
    }

    @Test
    @Transactional
    void createSysUserDepartWithExistingId() throws Exception {
        // Create the SysUserDepart with an existing ID
        sysUserDepart.setId("existing_id");
        SysUserDepartDTO sysUserDepartDTO = sysUserDepartMapper.toDto(sysUserDepart);

        int databaseSizeBeforeCreate = sysUserDepartRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysUserDepartMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysUserDepartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysUserDeparts() throws Exception {
        // Initialize the database
        sysUserDepart.setId(UUID.randomUUID().toString());
        sysUserDepartRepository.saveAndFlush(sysUserDepart);

        // Get all the sysUserDepartList
        restSysUserDepartMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUserDepart.getId())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].depID").value(hasItem(DEFAULT_DEP_ID)));
    }

    @Test
    @Transactional
    void getSysUserDepart() throws Exception {
        // Initialize the database
        sysUserDepart.setId(UUID.randomUUID().toString());
        sysUserDepartRepository.saveAndFlush(sysUserDepart);

        // Get the sysUserDepart
        restSysUserDepartMockMvc
            .perform(get(ENTITY_API_URL_ID, sysUserDepart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysUserDepart.getId()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.depID").value(DEFAULT_DEP_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysUserDepart() throws Exception {
        // Get the sysUserDepart
        restSysUserDepartMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysUserDepart() throws Exception {
        // Initialize the database
        sysUserDepart.setId(UUID.randomUUID().toString());
        sysUserDepartRepository.saveAndFlush(sysUserDepart);

        int databaseSizeBeforeUpdate = sysUserDepartRepository.findAll().size();

        // Update the sysUserDepart
        SysUserDepart updatedSysUserDepart = sysUserDepartRepository.findById(sysUserDepart.getId()).get();
        // Disconnect from session so that the updates on updatedSysUserDepart are not directly saved in db
        em.detach(updatedSysUserDepart);
        updatedSysUserDepart.userId(UPDATED_USER_ID).depID(UPDATED_DEP_ID);
        SysUserDepartDTO sysUserDepartDTO = sysUserDepartMapper.toDto(updatedSysUserDepart);

        restSysUserDepartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysUserDepartDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysUserDepartDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeUpdate);
        SysUserDepart testSysUserDepart = sysUserDepartList.get(sysUserDepartList.size() - 1);
        assertThat(testSysUserDepart.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSysUserDepart.getDepID()).isEqualTo(UPDATED_DEP_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysUserDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysUserDepartRepository.findAll().size();
        sysUserDepart.setId(UUID.randomUUID().toString());

        // Create the SysUserDepart
        SysUserDepartDTO sysUserDepartDTO = sysUserDepartMapper.toDto(sysUserDepart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysUserDepartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysUserDepartDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysUserDepartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysUserDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysUserDepartRepository.findAll().size();
        sysUserDepart.setId(UUID.randomUUID().toString());

        // Create the SysUserDepart
        SysUserDepartDTO sysUserDepartDTO = sysUserDepartMapper.toDto(sysUserDepart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserDepartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysUserDepartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysUserDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysUserDepartRepository.findAll().size();
        sysUserDepart.setId(UUID.randomUUID().toString());

        // Create the SysUserDepart
        SysUserDepartDTO sysUserDepartDTO = sysUserDepartMapper.toDto(sysUserDepart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserDepartMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysUserDepartDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysUserDepartWithPatch() throws Exception {
        // Initialize the database
        sysUserDepart.setId(UUID.randomUUID().toString());
        sysUserDepartRepository.saveAndFlush(sysUserDepart);

        int databaseSizeBeforeUpdate = sysUserDepartRepository.findAll().size();

        // Update the sysUserDepart using partial update
        SysUserDepart partialUpdatedSysUserDepart = new SysUserDepart();
        partialUpdatedSysUserDepart.setId(sysUserDepart.getId());

        restSysUserDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysUserDepart.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysUserDepart))
            )
            .andExpect(status().isOk());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeUpdate);
        SysUserDepart testSysUserDepart = sysUserDepartList.get(sysUserDepartList.size() - 1);
        assertThat(testSysUserDepart.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testSysUserDepart.getDepID()).isEqualTo(DEFAULT_DEP_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysUserDepartWithPatch() throws Exception {
        // Initialize the database
        sysUserDepart.setId(UUID.randomUUID().toString());
        sysUserDepartRepository.saveAndFlush(sysUserDepart);

        int databaseSizeBeforeUpdate = sysUserDepartRepository.findAll().size();

        // Update the sysUserDepart using partial update
        SysUserDepart partialUpdatedSysUserDepart = new SysUserDepart();
        partialUpdatedSysUserDepart.setId(sysUserDepart.getId());

        partialUpdatedSysUserDepart.userId(UPDATED_USER_ID).depID(UPDATED_DEP_ID);

        restSysUserDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysUserDepart.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysUserDepart))
            )
            .andExpect(status().isOk());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeUpdate);
        SysUserDepart testSysUserDepart = sysUserDepartList.get(sysUserDepartList.size() - 1);
        assertThat(testSysUserDepart.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSysUserDepart.getDepID()).isEqualTo(UPDATED_DEP_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysUserDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysUserDepartRepository.findAll().size();
        sysUserDepart.setId(UUID.randomUUID().toString());

        // Create the SysUserDepart
        SysUserDepartDTO sysUserDepartDTO = sysUserDepartMapper.toDto(sysUserDepart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysUserDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysUserDepartDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysUserDepartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysUserDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysUserDepartRepository.findAll().size();
        sysUserDepart.setId(UUID.randomUUID().toString());

        // Create the SysUserDepart
        SysUserDepartDTO sysUserDepartDTO = sysUserDepartMapper.toDto(sysUserDepart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysUserDepartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysUserDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysUserDepartRepository.findAll().size();
        sysUserDepart.setId(UUID.randomUUID().toString());

        // Create the SysUserDepart
        SysUserDepartDTO sysUserDepartDTO = sysUserDepartMapper.toDto(sysUserDepart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserDepartMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysUserDepartDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysUserDepart in the database
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysUserDepart() throws Exception {
        // Initialize the database
        sysUserDepart.setId(UUID.randomUUID().toString());
        sysUserDepartRepository.saveAndFlush(sysUserDepart);

        int databaseSizeBeforeDelete = sysUserDepartRepository.findAll().size();

        // Delete the sysUserDepart
        restSysUserDepartMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysUserDepart.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysUserDepart> sysUserDepartList = sysUserDepartRepository.findAll();
        assertThat(sysUserDepartList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
