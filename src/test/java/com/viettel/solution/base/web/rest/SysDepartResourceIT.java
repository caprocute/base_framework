package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysDepart;
import com.viettel.solution.base.repository.SysDepartRepository;
import com.viettel.solution.base.service.dto.SysDepartDTO;
import com.viettel.solution.base.service.mapper.SysDepartMapper;
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
 * Integration tests for the {@link SysDepartResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysDepartResourceIT {

    private static final String DEFAULT_PARENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEPART_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPART_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DEPART_ORDER = 1;
    private static final Integer UPDATED_DEPART_ORDER = 2;

    private static final String DEFAULT_ORG_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_ORG_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ORG_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ORG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_MEMO = "AAAAAAAAAA";
    private static final String UPDATED_MEMO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    private static final Boolean DEFAULT_DEL_FLAG = false;
    private static final Boolean UPDATED_DEL_FLAG = true;

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

    private static final String ENTITY_API_URL = "/api/sys-departs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysDepartRepository sysDepartRepository;

    @Autowired
    private SysDepartMapper sysDepartMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysDepartMockMvc;

    private SysDepart sysDepart;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDepart createEntity(EntityManager em) {
        SysDepart sysDepart = new SysDepart()
            .parentId(DEFAULT_PARENT_ID)
            .departName(DEFAULT_DEPART_NAME)
            .departOrder(DEFAULT_DEPART_ORDER)
            .orgCategory(DEFAULT_ORG_CATEGORY)
            .orgType(DEFAULT_ORG_TYPE)
            .orgCode(DEFAULT_ORG_CODE)
            .mobile(DEFAULT_MOBILE)
            .fax(DEFAULT_FAX)
            .address(DEFAULT_ADDRESS)
            .memo(DEFAULT_MEMO)
            .status(DEFAULT_STATUS)
            .delFlag(DEFAULT_DEL_FLAG)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateBy(DEFAULT_UPDATE_BY)
            .updateTime(DEFAULT_UPDATE_TIME)
            .tenantId(DEFAULT_TENANT_ID);
        return sysDepart;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDepart createUpdatedEntity(EntityManager em) {
        SysDepart sysDepart = new SysDepart()
            .parentId(UPDATED_PARENT_ID)
            .departName(UPDATED_DEPART_NAME)
            .departOrder(UPDATED_DEPART_ORDER)
            .orgCategory(UPDATED_ORG_CATEGORY)
            .orgType(UPDATED_ORG_TYPE)
            .orgCode(UPDATED_ORG_CODE)
            .mobile(UPDATED_MOBILE)
            .fax(UPDATED_FAX)
            .address(UPDATED_ADDRESS)
            .memo(UPDATED_MEMO)
            .status(UPDATED_STATUS)
            .delFlag(UPDATED_DEL_FLAG)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        return sysDepart;
    }

    @BeforeEach
    public void initTest() {
        sysDepart = createEntity(em);
    }

    @Test
    @Transactional
    void createSysDepart() throws Exception {
        int databaseSizeBeforeCreate = sysDepartRepository.findAll().size();
        // Create the SysDepart
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);
        restSysDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDepartDTO)))
            .andExpect(status().isCreated());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeCreate + 1);
        SysDepart testSysDepart = sysDepartList.get(sysDepartList.size() - 1);
        assertThat(testSysDepart.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testSysDepart.getDepartName()).isEqualTo(DEFAULT_DEPART_NAME);
        assertThat(testSysDepart.getDepartOrder()).isEqualTo(DEFAULT_DEPART_ORDER);
        assertThat(testSysDepart.getOrgCategory()).isEqualTo(DEFAULT_ORG_CATEGORY);
        assertThat(testSysDepart.getOrgType()).isEqualTo(DEFAULT_ORG_TYPE);
        assertThat(testSysDepart.getOrgCode()).isEqualTo(DEFAULT_ORG_CODE);
        assertThat(testSysDepart.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testSysDepart.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testSysDepart.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSysDepart.getMemo()).isEqualTo(DEFAULT_MEMO);
        assertThat(testSysDepart.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysDepart.getDelFlag()).isEqualTo(DEFAULT_DEL_FLAG);
        assertThat(testSysDepart.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysDepart.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysDepart.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysDepart.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysDepart.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    @Transactional
    void createSysDepartWithExistingId() throws Exception {
        // Create the SysDepart with an existing ID
        sysDepart.setId("existing_id");
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);

        int databaseSizeBeforeCreate = sysDepartRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDepartDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDepartNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysDepartRepository.findAll().size();
        // set the field null
        sysDepart.setDepartName(null);

        // Create the SysDepart, which fails.
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);

        restSysDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDepartDTO)))
            .andExpect(status().isBadRequest());

        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOrgCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysDepartRepository.findAll().size();
        // set the field null
        sysDepart.setOrgCategory(null);

        // Create the SysDepart, which fails.
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);

        restSysDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDepartDTO)))
            .andExpect(status().isBadRequest());

        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOrgCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysDepartRepository.findAll().size();
        // set the field null
        sysDepart.setOrgCode(null);

        // Create the SysDepart, which fails.
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);

        restSysDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDepartDTO)))
            .andExpect(status().isBadRequest());

        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSysDeparts() throws Exception {
        // Initialize the database
        sysDepart.setId(UUID.randomUUID().toString());
        sysDepartRepository.saveAndFlush(sysDepart);

        // Get all the sysDepartList
        restSysDepartMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysDepart.getId())))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID)))
            .andExpect(jsonPath("$.[*].departName").value(hasItem(DEFAULT_DEPART_NAME)))
            .andExpect(jsonPath("$.[*].departOrder").value(hasItem(DEFAULT_DEPART_ORDER)))
            .andExpect(jsonPath("$.[*].orgCategory").value(hasItem(DEFAULT_ORG_CATEGORY)))
            .andExpect(jsonPath("$.[*].orgType").value(hasItem(DEFAULT_ORG_TYPE)))
            .andExpect(jsonPath("$.[*].orgCode").value(hasItem(DEFAULT_ORG_CODE)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].memo").value(hasItem(DEFAULT_MEMO)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].delFlag").value(hasItem(DEFAULT_DEL_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }

    @Test
    @Transactional
    void getSysDepart() throws Exception {
        // Initialize the database
        sysDepart.setId(UUID.randomUUID().toString());
        sysDepartRepository.saveAndFlush(sysDepart);

        // Get the sysDepart
        restSysDepartMockMvc
            .perform(get(ENTITY_API_URL_ID, sysDepart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysDepart.getId()))
            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID))
            .andExpect(jsonPath("$.departName").value(DEFAULT_DEPART_NAME))
            .andExpect(jsonPath("$.departOrder").value(DEFAULT_DEPART_ORDER))
            .andExpect(jsonPath("$.orgCategory").value(DEFAULT_ORG_CATEGORY))
            .andExpect(jsonPath("$.orgType").value(DEFAULT_ORG_TYPE))
            .andExpect(jsonPath("$.orgCode").value(DEFAULT_ORG_CODE))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.memo").value(DEFAULT_MEMO))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()))
            .andExpect(jsonPath("$.delFlag").value(DEFAULT_DEL_FLAG.booleanValue()))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    @Transactional
    void getNonExistingSysDepart() throws Exception {
        // Get the sysDepart
        restSysDepartMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysDepart() throws Exception {
        // Initialize the database
        sysDepart.setId(UUID.randomUUID().toString());
        sysDepartRepository.saveAndFlush(sysDepart);

        int databaseSizeBeforeUpdate = sysDepartRepository.findAll().size();

        // Update the sysDepart
        SysDepart updatedSysDepart = sysDepartRepository.findById(sysDepart.getId()).get();
        // Disconnect from session so that the updates on updatedSysDepart are not directly saved in db
        em.detach(updatedSysDepart);
        updatedSysDepart
            .parentId(UPDATED_PARENT_ID)
            .departName(UPDATED_DEPART_NAME)
            .departOrder(UPDATED_DEPART_ORDER)
            .orgCategory(UPDATED_ORG_CATEGORY)
            .orgType(UPDATED_ORG_TYPE)
            .orgCode(UPDATED_ORG_CODE)
            .mobile(UPDATED_MOBILE)
            .fax(UPDATED_FAX)
            .address(UPDATED_ADDRESS)
            .memo(UPDATED_MEMO)
            .status(UPDATED_STATUS)
            .delFlag(UPDATED_DEL_FLAG)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(updatedSysDepart);

        restSysDepartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysDepartDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeUpdate);
        SysDepart testSysDepart = sysDepartList.get(sysDepartList.size() - 1);
        assertThat(testSysDepart.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testSysDepart.getDepartName()).isEqualTo(UPDATED_DEPART_NAME);
        assertThat(testSysDepart.getDepartOrder()).isEqualTo(UPDATED_DEPART_ORDER);
        assertThat(testSysDepart.getOrgCategory()).isEqualTo(UPDATED_ORG_CATEGORY);
        assertThat(testSysDepart.getOrgType()).isEqualTo(UPDATED_ORG_TYPE);
        assertThat(testSysDepart.getOrgCode()).isEqualTo(UPDATED_ORG_CODE);
        assertThat(testSysDepart.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testSysDepart.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testSysDepart.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSysDepart.getMemo()).isEqualTo(UPDATED_MEMO);
        assertThat(testSysDepart.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysDepart.getDelFlag()).isEqualTo(UPDATED_DEL_FLAG);
        assertThat(testSysDepart.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDepart.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDepart.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysDepart.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDepart.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void putNonExistingSysDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRepository.findAll().size();
        sysDepart.setId(UUID.randomUUID().toString());

        // Create the SysDepart
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDepartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysDepartDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRepository.findAll().size();
        sysDepart.setId(UUID.randomUUID().toString());

        // Create the SysDepart
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDepartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRepository.findAll().size();
        sysDepart.setId(UUID.randomUUID().toString());

        // Create the SysDepart
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDepartMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysDepartDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysDepartWithPatch() throws Exception {
        // Initialize the database
        sysDepart.setId(UUID.randomUUID().toString());
        sysDepartRepository.saveAndFlush(sysDepart);

        int databaseSizeBeforeUpdate = sysDepartRepository.findAll().size();

        // Update the sysDepart using partial update
        SysDepart partialUpdatedSysDepart = new SysDepart();
        partialUpdatedSysDepart.setId(sysDepart.getId());

        partialUpdatedSysDepart
            .departOrder(UPDATED_DEPART_ORDER)
            .mobile(UPDATED_MOBILE)
            .delFlag(UPDATED_DEL_FLAG)
            .createBy(UPDATED_CREATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysDepart.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysDepart))
            )
            .andExpect(status().isOk());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeUpdate);
        SysDepart testSysDepart = sysDepartList.get(sysDepartList.size() - 1);
        assertThat(testSysDepart.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testSysDepart.getDepartName()).isEqualTo(DEFAULT_DEPART_NAME);
        assertThat(testSysDepart.getDepartOrder()).isEqualTo(UPDATED_DEPART_ORDER);
        assertThat(testSysDepart.getOrgCategory()).isEqualTo(DEFAULT_ORG_CATEGORY);
        assertThat(testSysDepart.getOrgType()).isEqualTo(DEFAULT_ORG_TYPE);
        assertThat(testSysDepart.getOrgCode()).isEqualTo(DEFAULT_ORG_CODE);
        assertThat(testSysDepart.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testSysDepart.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testSysDepart.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSysDepart.getMemo()).isEqualTo(DEFAULT_MEMO);
        assertThat(testSysDepart.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysDepart.getDelFlag()).isEqualTo(UPDATED_DEL_FLAG);
        assertThat(testSysDepart.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDepart.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysDepart.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysDepart.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDepart.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void fullUpdateSysDepartWithPatch() throws Exception {
        // Initialize the database
        sysDepart.setId(UUID.randomUUID().toString());
        sysDepartRepository.saveAndFlush(sysDepart);

        int databaseSizeBeforeUpdate = sysDepartRepository.findAll().size();

        // Update the sysDepart using partial update
        SysDepart partialUpdatedSysDepart = new SysDepart();
        partialUpdatedSysDepart.setId(sysDepart.getId());

        partialUpdatedSysDepart
            .parentId(UPDATED_PARENT_ID)
            .departName(UPDATED_DEPART_NAME)
            .departOrder(UPDATED_DEPART_ORDER)
            .orgCategory(UPDATED_ORG_CATEGORY)
            .orgType(UPDATED_ORG_TYPE)
            .orgCode(UPDATED_ORG_CODE)
            .mobile(UPDATED_MOBILE)
            .fax(UPDATED_FAX)
            .address(UPDATED_ADDRESS)
            .memo(UPDATED_MEMO)
            .status(UPDATED_STATUS)
            .delFlag(UPDATED_DEL_FLAG)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID);

        restSysDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysDepart.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysDepart))
            )
            .andExpect(status().isOk());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeUpdate);
        SysDepart testSysDepart = sysDepartList.get(sysDepartList.size() - 1);
        assertThat(testSysDepart.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testSysDepart.getDepartName()).isEqualTo(UPDATED_DEPART_NAME);
        assertThat(testSysDepart.getDepartOrder()).isEqualTo(UPDATED_DEPART_ORDER);
        assertThat(testSysDepart.getOrgCategory()).isEqualTo(UPDATED_ORG_CATEGORY);
        assertThat(testSysDepart.getOrgType()).isEqualTo(UPDATED_ORG_TYPE);
        assertThat(testSysDepart.getOrgCode()).isEqualTo(UPDATED_ORG_CODE);
        assertThat(testSysDepart.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testSysDepart.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testSysDepart.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSysDepart.getMemo()).isEqualTo(UPDATED_MEMO);
        assertThat(testSysDepart.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysDepart.getDelFlag()).isEqualTo(UPDATED_DEL_FLAG);
        assertThat(testSysDepart.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysDepart.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDepart.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysDepart.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDepart.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingSysDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRepository.findAll().size();
        sysDepart.setId(UUID.randomUUID().toString());

        // Create the SysDepart
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysDepartDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRepository.findAll().size();
        sysDepart.setId(UUID.randomUUID().toString());

        // Create the SysDepart
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysDepartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysDepart() throws Exception {
        int databaseSizeBeforeUpdate = sysDepartRepository.findAll().size();
        sysDepart.setId(UUID.randomUUID().toString());

        // Create the SysDepart
        SysDepartDTO sysDepartDTO = sysDepartMapper.toDto(sysDepart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysDepartMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysDepartDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysDepart in the database
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysDepart() throws Exception {
        // Initialize the database
        sysDepart.setId(UUID.randomUUID().toString());
        sysDepartRepository.saveAndFlush(sysDepart);

        int databaseSizeBeforeDelete = sysDepartRepository.findAll().size();

        // Delete the sysDepart
        restSysDepartMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysDepart.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysDepart> sysDepartList = sysDepartRepository.findAll();
        assertThat(sysDepartList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
