package com.viettel.solution.base.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.viettel.solution.base.IntegrationTest;
import com.viettel.solution.base.domain.SysPermission;
import com.viettel.solution.base.repository.SysPermissionRepository;
import com.viettel.solution.base.service.dto.SysPermissionDTO;
import com.viettel.solution.base.service.mapper.SysPermissionMapper;
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
 * Integration tests for the {@link SysPermissionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysPermissionResourceIT {

    private static final String DEFAULT_PARENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_COMPONENT = "AAAAAAAAAA";
    private static final String UPDATED_COMPONENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ROUTE = false;
    private static final Boolean UPDATED_IS_ROUTE = true;

    private static final String DEFAULT_COMPONENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPONENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REDIRECT = "AAAAAAAAAA";
    private static final String UPDATED_REDIRECT = "BBBBBBBBBB";

    private static final Integer DEFAULT_MENU_TYPE = 1;
    private static final Integer UPDATED_MENU_TYPE = 2;

    private static final String DEFAULT_PERMS = "AAAAAAAAAA";
    private static final String UPDATED_PERMS = "BBBBBBBBBB";

    private static final String DEFAULT_PERMS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PERMS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SORT_NO = "AAAAAAAAAA";
    private static final String UPDATED_SORT_NO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ALWAYS_SHOW = false;
    private static final Boolean UPDATED_ALWAYS_SHOW = true;

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_LEAF = false;
    private static final Boolean UPDATED_IS_LEAF = true;

    private static final Boolean DEFAULT_KEEP_ALIVE = false;
    private static final Boolean UPDATED_KEEP_ALIVE = true;

    private static final Boolean DEFAULT_HIDDEN = false;
    private static final Boolean UPDATED_HIDDEN = true;

    private static final Boolean DEFAULT_HIDE_TAB = false;
    private static final Boolean UPDATED_HIDE_TAB = true;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DEL_FLAG = false;
    private static final Boolean UPDATED_DEL_FLAG = true;

    private static final Boolean DEFAULT_RULE_F_LAG = false;
    private static final Boolean UPDATED_RULE_F_LAG = true;

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

    private static final Boolean DEFAULT_INTERNAL_OR_EXTERNAL = false;
    private static final Boolean UPDATED_INTERNAL_OR_EXTERNAL = true;

    private static final String ENTITY_API_URL = "/api/sys-permissions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysPermissionMockMvc;

    private SysPermission sysPermission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysPermission createEntity(EntityManager em) {
        SysPermission sysPermission = new SysPermission()
            .parentId(DEFAULT_PARENT_ID)
            .name(DEFAULT_NAME)
            .url(DEFAULT_URL)
            .component(DEFAULT_COMPONENT)
            .isRoute(DEFAULT_IS_ROUTE)
            .componentName(DEFAULT_COMPONENT_NAME)
            .redirect(DEFAULT_REDIRECT)
            .menuType(DEFAULT_MENU_TYPE)
            .perms(DEFAULT_PERMS)
            .permsType(DEFAULT_PERMS_TYPE)
            .sortNo(DEFAULT_SORT_NO)
            .alwaysShow(DEFAULT_ALWAYS_SHOW)
            .icon(DEFAULT_ICON)
            .isLeaf(DEFAULT_IS_LEAF)
            .keepAlive(DEFAULT_KEEP_ALIVE)
            .hidden(DEFAULT_HIDDEN)
            .hideTab(DEFAULT_HIDE_TAB)
            .description(DEFAULT_DESCRIPTION)
            .delFlag(DEFAULT_DEL_FLAG)
            .ruleFLag(DEFAULT_RULE_F_LAG)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .updateBy(DEFAULT_UPDATE_BY)
            .updateTime(DEFAULT_UPDATE_TIME)
            .tenantId(DEFAULT_TENANT_ID)
            .internalOrExternal(DEFAULT_INTERNAL_OR_EXTERNAL);
        return sysPermission;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysPermission createUpdatedEntity(EntityManager em) {
        SysPermission sysPermission = new SysPermission()
            .parentId(UPDATED_PARENT_ID)
            .name(UPDATED_NAME)
            .url(UPDATED_URL)
            .component(UPDATED_COMPONENT)
            .isRoute(UPDATED_IS_ROUTE)
            .componentName(UPDATED_COMPONENT_NAME)
            .redirect(UPDATED_REDIRECT)
            .menuType(UPDATED_MENU_TYPE)
            .perms(UPDATED_PERMS)
            .permsType(UPDATED_PERMS_TYPE)
            .sortNo(UPDATED_SORT_NO)
            .alwaysShow(UPDATED_ALWAYS_SHOW)
            .icon(UPDATED_ICON)
            .isLeaf(UPDATED_IS_LEAF)
            .keepAlive(UPDATED_KEEP_ALIVE)
            .hidden(UPDATED_HIDDEN)
            .hideTab(UPDATED_HIDE_TAB)
            .description(UPDATED_DESCRIPTION)
            .delFlag(UPDATED_DEL_FLAG)
            .ruleFLag(UPDATED_RULE_F_LAG)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID)
            .internalOrExternal(UPDATED_INTERNAL_OR_EXTERNAL);
        return sysPermission;
    }

    @BeforeEach
    public void initTest() {
        sysPermission = createEntity(em);
    }

    @Test
    @Transactional
    void createSysPermission() throws Exception {
        int databaseSizeBeforeCreate = sysPermissionRepository.findAll().size();
        // Create the SysPermission
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);
        restSysPermissionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeCreate + 1);
        SysPermission testSysPermission = sysPermissionList.get(sysPermissionList.size() - 1);
        assertThat(testSysPermission.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testSysPermission.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysPermission.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testSysPermission.getComponent()).isEqualTo(DEFAULT_COMPONENT);
        assertThat(testSysPermission.getIsRoute()).isEqualTo(DEFAULT_IS_ROUTE);
        assertThat(testSysPermission.getComponentName()).isEqualTo(DEFAULT_COMPONENT_NAME);
        assertThat(testSysPermission.getRedirect()).isEqualTo(DEFAULT_REDIRECT);
        assertThat(testSysPermission.getMenuType()).isEqualTo(DEFAULT_MENU_TYPE);
        assertThat(testSysPermission.getPerms()).isEqualTo(DEFAULT_PERMS);
        assertThat(testSysPermission.getPermsType()).isEqualTo(DEFAULT_PERMS_TYPE);
        assertThat(testSysPermission.getSortNo()).isEqualTo(DEFAULT_SORT_NO);
        assertThat(testSysPermission.getAlwaysShow()).isEqualTo(DEFAULT_ALWAYS_SHOW);
        assertThat(testSysPermission.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testSysPermission.getIsLeaf()).isEqualTo(DEFAULT_IS_LEAF);
        assertThat(testSysPermission.getKeepAlive()).isEqualTo(DEFAULT_KEEP_ALIVE);
        assertThat(testSysPermission.getHidden()).isEqualTo(DEFAULT_HIDDEN);
        assertThat(testSysPermission.getHideTab()).isEqualTo(DEFAULT_HIDE_TAB);
        assertThat(testSysPermission.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSysPermission.getDelFlag()).isEqualTo(DEFAULT_DEL_FLAG);
        assertThat(testSysPermission.getRuleFLag()).isEqualTo(DEFAULT_RULE_F_LAG);
        assertThat(testSysPermission.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysPermission.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysPermission.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysPermission.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysPermission.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testSysPermission.getInternalOrExternal()).isEqualTo(DEFAULT_INTERNAL_OR_EXTERNAL);
    }

    @Test
    @Transactional
    void createSysPermissionWithExistingId() throws Exception {
        // Create the SysPermission with an existing ID
        sysPermission.setId("existing_id");
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);

        int databaseSizeBeforeCreate = sysPermissionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysPermissionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysPermissions() throws Exception {
        // Initialize the database
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermissionRepository.saveAndFlush(sysPermission);

        // Get all the sysPermissionList
        restSysPermissionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysPermission.getId())))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].component").value(hasItem(DEFAULT_COMPONENT)))
            .andExpect(jsonPath("$.[*].isRoute").value(hasItem(DEFAULT_IS_ROUTE.booleanValue())))
            .andExpect(jsonPath("$.[*].componentName").value(hasItem(DEFAULT_COMPONENT_NAME)))
            .andExpect(jsonPath("$.[*].redirect").value(hasItem(DEFAULT_REDIRECT)))
            .andExpect(jsonPath("$.[*].menuType").value(hasItem(DEFAULT_MENU_TYPE)))
            .andExpect(jsonPath("$.[*].perms").value(hasItem(DEFAULT_PERMS)))
            .andExpect(jsonPath("$.[*].permsType").value(hasItem(DEFAULT_PERMS_TYPE)))
            .andExpect(jsonPath("$.[*].sortNo").value(hasItem(DEFAULT_SORT_NO)))
            .andExpect(jsonPath("$.[*].alwaysShow").value(hasItem(DEFAULT_ALWAYS_SHOW.booleanValue())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].isLeaf").value(hasItem(DEFAULT_IS_LEAF.booleanValue())))
            .andExpect(jsonPath("$.[*].keepAlive").value(hasItem(DEFAULT_KEEP_ALIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].hidden").value(hasItem(DEFAULT_HIDDEN.booleanValue())))
            .andExpect(jsonPath("$.[*].hideTab").value(hasItem(DEFAULT_HIDE_TAB.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].delFlag").value(hasItem(DEFAULT_DEL_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].ruleFLag").value(hasItem(DEFAULT_RULE_F_LAG.booleanValue())))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)))
            .andExpect(jsonPath("$.[*].internalOrExternal").value(hasItem(DEFAULT_INTERNAL_OR_EXTERNAL.booleanValue())));
    }

    @Test
    @Transactional
    void getSysPermission() throws Exception {
        // Initialize the database
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermissionRepository.saveAndFlush(sysPermission);

        // Get the sysPermission
        restSysPermissionMockMvc
            .perform(get(ENTITY_API_URL_ID, sysPermission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysPermission.getId()))
            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.component").value(DEFAULT_COMPONENT))
            .andExpect(jsonPath("$.isRoute").value(DEFAULT_IS_ROUTE.booleanValue()))
            .andExpect(jsonPath("$.componentName").value(DEFAULT_COMPONENT_NAME))
            .andExpect(jsonPath("$.redirect").value(DEFAULT_REDIRECT))
            .andExpect(jsonPath("$.menuType").value(DEFAULT_MENU_TYPE))
            .andExpect(jsonPath("$.perms").value(DEFAULT_PERMS))
            .andExpect(jsonPath("$.permsType").value(DEFAULT_PERMS_TYPE))
            .andExpect(jsonPath("$.sortNo").value(DEFAULT_SORT_NO))
            .andExpect(jsonPath("$.alwaysShow").value(DEFAULT_ALWAYS_SHOW.booleanValue()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.isLeaf").value(DEFAULT_IS_LEAF.booleanValue()))
            .andExpect(jsonPath("$.keepAlive").value(DEFAULT_KEEP_ALIVE.booleanValue()))
            .andExpect(jsonPath("$.hidden").value(DEFAULT_HIDDEN.booleanValue()))
            .andExpect(jsonPath("$.hideTab").value(DEFAULT_HIDE_TAB.booleanValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.delFlag").value(DEFAULT_DEL_FLAG.booleanValue()))
            .andExpect(jsonPath("$.ruleFLag").value(DEFAULT_RULE_F_LAG.booleanValue()))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID))
            .andExpect(jsonPath("$.internalOrExternal").value(DEFAULT_INTERNAL_OR_EXTERNAL.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingSysPermission() throws Exception {
        // Get the sysPermission
        restSysPermissionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysPermission() throws Exception {
        // Initialize the database
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermissionRepository.saveAndFlush(sysPermission);

        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();

        // Update the sysPermission
        SysPermission updatedSysPermission = sysPermissionRepository.findById(sysPermission.getId()).get();
        // Disconnect from session so that the updates on updatedSysPermission are not directly saved in db
        em.detach(updatedSysPermission);
        updatedSysPermission
            .parentId(UPDATED_PARENT_ID)
            .name(UPDATED_NAME)
            .url(UPDATED_URL)
            .component(UPDATED_COMPONENT)
            .isRoute(UPDATED_IS_ROUTE)
            .componentName(UPDATED_COMPONENT_NAME)
            .redirect(UPDATED_REDIRECT)
            .menuType(UPDATED_MENU_TYPE)
            .perms(UPDATED_PERMS)
            .permsType(UPDATED_PERMS_TYPE)
            .sortNo(UPDATED_SORT_NO)
            .alwaysShow(UPDATED_ALWAYS_SHOW)
            .icon(UPDATED_ICON)
            .isLeaf(UPDATED_IS_LEAF)
            .keepAlive(UPDATED_KEEP_ALIVE)
            .hidden(UPDATED_HIDDEN)
            .hideTab(UPDATED_HIDE_TAB)
            .description(UPDATED_DESCRIPTION)
            .delFlag(UPDATED_DEL_FLAG)
            .ruleFLag(UPDATED_RULE_F_LAG)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID)
            .internalOrExternal(UPDATED_INTERNAL_OR_EXTERNAL);
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(updatedSysPermission);

        restSysPermissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysPermissionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
        SysPermission testSysPermission = sysPermissionList.get(sysPermissionList.size() - 1);
        assertThat(testSysPermission.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testSysPermission.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysPermission.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testSysPermission.getComponent()).isEqualTo(UPDATED_COMPONENT);
        assertThat(testSysPermission.getIsRoute()).isEqualTo(UPDATED_IS_ROUTE);
        assertThat(testSysPermission.getComponentName()).isEqualTo(UPDATED_COMPONENT_NAME);
        assertThat(testSysPermission.getRedirect()).isEqualTo(UPDATED_REDIRECT);
        assertThat(testSysPermission.getMenuType()).isEqualTo(UPDATED_MENU_TYPE);
        assertThat(testSysPermission.getPerms()).isEqualTo(UPDATED_PERMS);
        assertThat(testSysPermission.getPermsType()).isEqualTo(UPDATED_PERMS_TYPE);
        assertThat(testSysPermission.getSortNo()).isEqualTo(UPDATED_SORT_NO);
        assertThat(testSysPermission.getAlwaysShow()).isEqualTo(UPDATED_ALWAYS_SHOW);
        assertThat(testSysPermission.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testSysPermission.getIsLeaf()).isEqualTo(UPDATED_IS_LEAF);
        assertThat(testSysPermission.getKeepAlive()).isEqualTo(UPDATED_KEEP_ALIVE);
        assertThat(testSysPermission.getHidden()).isEqualTo(UPDATED_HIDDEN);
        assertThat(testSysPermission.getHideTab()).isEqualTo(UPDATED_HIDE_TAB);
        assertThat(testSysPermission.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSysPermission.getDelFlag()).isEqualTo(UPDATED_DEL_FLAG);
        assertThat(testSysPermission.getRuleFLag()).isEqualTo(UPDATED_RULE_F_LAG);
        assertThat(testSysPermission.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysPermission.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysPermission.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysPermission.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysPermission.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testSysPermission.getInternalOrExternal()).isEqualTo(UPDATED_INTERNAL_OR_EXTERNAL);
    }

    @Test
    @Transactional
    void putNonExistingSysPermission() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();
        sysPermission.setId(UUID.randomUUID().toString());

        // Create the SysPermission
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysPermissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysPermissionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysPermission() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();
        sysPermission.setId(UUID.randomUUID().toString());

        // Create the SysPermission
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPermissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysPermission() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();
        sysPermission.setId(UUID.randomUUID().toString());

        // Create the SysPermission
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPermissionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysPermissionWithPatch() throws Exception {
        // Initialize the database
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermissionRepository.saveAndFlush(sysPermission);

        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();

        // Update the sysPermission using partial update
        SysPermission partialUpdatedSysPermission = new SysPermission();
        partialUpdatedSysPermission.setId(sysPermission.getId());

        partialUpdatedSysPermission
            .name(UPDATED_NAME)
            .url(UPDATED_URL)
            .isRoute(UPDATED_IS_ROUTE)
            .redirect(UPDATED_REDIRECT)
            .menuType(UPDATED_MENU_TYPE)
            .delFlag(UPDATED_DEL_FLAG)
            .tenantId(UPDATED_TENANT_ID)
            .internalOrExternal(UPDATED_INTERNAL_OR_EXTERNAL);

        restSysPermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysPermission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysPermission))
            )
            .andExpect(status().isOk());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
        SysPermission testSysPermission = sysPermissionList.get(sysPermissionList.size() - 1);
        assertThat(testSysPermission.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testSysPermission.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysPermission.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testSysPermission.getComponent()).isEqualTo(DEFAULT_COMPONENT);
        assertThat(testSysPermission.getIsRoute()).isEqualTo(UPDATED_IS_ROUTE);
        assertThat(testSysPermission.getComponentName()).isEqualTo(DEFAULT_COMPONENT_NAME);
        assertThat(testSysPermission.getRedirect()).isEqualTo(UPDATED_REDIRECT);
        assertThat(testSysPermission.getMenuType()).isEqualTo(UPDATED_MENU_TYPE);
        assertThat(testSysPermission.getPerms()).isEqualTo(DEFAULT_PERMS);
        assertThat(testSysPermission.getPermsType()).isEqualTo(DEFAULT_PERMS_TYPE);
        assertThat(testSysPermission.getSortNo()).isEqualTo(DEFAULT_SORT_NO);
        assertThat(testSysPermission.getAlwaysShow()).isEqualTo(DEFAULT_ALWAYS_SHOW);
        assertThat(testSysPermission.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testSysPermission.getIsLeaf()).isEqualTo(DEFAULT_IS_LEAF);
        assertThat(testSysPermission.getKeepAlive()).isEqualTo(DEFAULT_KEEP_ALIVE);
        assertThat(testSysPermission.getHidden()).isEqualTo(DEFAULT_HIDDEN);
        assertThat(testSysPermission.getHideTab()).isEqualTo(DEFAULT_HIDE_TAB);
        assertThat(testSysPermission.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSysPermission.getDelFlag()).isEqualTo(UPDATED_DEL_FLAG);
        assertThat(testSysPermission.getRuleFLag()).isEqualTo(DEFAULT_RULE_F_LAG);
        assertThat(testSysPermission.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testSysPermission.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysPermission.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testSysPermission.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysPermission.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testSysPermission.getInternalOrExternal()).isEqualTo(UPDATED_INTERNAL_OR_EXTERNAL);
    }

    @Test
    @Transactional
    void fullUpdateSysPermissionWithPatch() throws Exception {
        // Initialize the database
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermissionRepository.saveAndFlush(sysPermission);

        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();

        // Update the sysPermission using partial update
        SysPermission partialUpdatedSysPermission = new SysPermission();
        partialUpdatedSysPermission.setId(sysPermission.getId());

        partialUpdatedSysPermission
            .parentId(UPDATED_PARENT_ID)
            .name(UPDATED_NAME)
            .url(UPDATED_URL)
            .component(UPDATED_COMPONENT)
            .isRoute(UPDATED_IS_ROUTE)
            .componentName(UPDATED_COMPONENT_NAME)
            .redirect(UPDATED_REDIRECT)
            .menuType(UPDATED_MENU_TYPE)
            .perms(UPDATED_PERMS)
            .permsType(UPDATED_PERMS_TYPE)
            .sortNo(UPDATED_SORT_NO)
            .alwaysShow(UPDATED_ALWAYS_SHOW)
            .icon(UPDATED_ICON)
            .isLeaf(UPDATED_IS_LEAF)
            .keepAlive(UPDATED_KEEP_ALIVE)
            .hidden(UPDATED_HIDDEN)
            .hideTab(UPDATED_HIDE_TAB)
            .description(UPDATED_DESCRIPTION)
            .delFlag(UPDATED_DEL_FLAG)
            .ruleFLag(UPDATED_RULE_F_LAG)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .updateBy(UPDATED_UPDATE_BY)
            .updateTime(UPDATED_UPDATE_TIME)
            .tenantId(UPDATED_TENANT_ID)
            .internalOrExternal(UPDATED_INTERNAL_OR_EXTERNAL);

        restSysPermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysPermission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysPermission))
            )
            .andExpect(status().isOk());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
        SysPermission testSysPermission = sysPermissionList.get(sysPermissionList.size() - 1);
        assertThat(testSysPermission.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testSysPermission.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysPermission.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testSysPermission.getComponent()).isEqualTo(UPDATED_COMPONENT);
        assertThat(testSysPermission.getIsRoute()).isEqualTo(UPDATED_IS_ROUTE);
        assertThat(testSysPermission.getComponentName()).isEqualTo(UPDATED_COMPONENT_NAME);
        assertThat(testSysPermission.getRedirect()).isEqualTo(UPDATED_REDIRECT);
        assertThat(testSysPermission.getMenuType()).isEqualTo(UPDATED_MENU_TYPE);
        assertThat(testSysPermission.getPerms()).isEqualTo(UPDATED_PERMS);
        assertThat(testSysPermission.getPermsType()).isEqualTo(UPDATED_PERMS_TYPE);
        assertThat(testSysPermission.getSortNo()).isEqualTo(UPDATED_SORT_NO);
        assertThat(testSysPermission.getAlwaysShow()).isEqualTo(UPDATED_ALWAYS_SHOW);
        assertThat(testSysPermission.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testSysPermission.getIsLeaf()).isEqualTo(UPDATED_IS_LEAF);
        assertThat(testSysPermission.getKeepAlive()).isEqualTo(UPDATED_KEEP_ALIVE);
        assertThat(testSysPermission.getHidden()).isEqualTo(UPDATED_HIDDEN);
        assertThat(testSysPermission.getHideTab()).isEqualTo(UPDATED_HIDE_TAB);
        assertThat(testSysPermission.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSysPermission.getDelFlag()).isEqualTo(UPDATED_DEL_FLAG);
        assertThat(testSysPermission.getRuleFLag()).isEqualTo(UPDATED_RULE_F_LAG);
        assertThat(testSysPermission.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testSysPermission.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysPermission.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testSysPermission.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysPermission.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testSysPermission.getInternalOrExternal()).isEqualTo(UPDATED_INTERNAL_OR_EXTERNAL);
    }

    @Test
    @Transactional
    void patchNonExistingSysPermission() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();
        sysPermission.setId(UUID.randomUUID().toString());

        // Create the SysPermission
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysPermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysPermissionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysPermission() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();
        sysPermission.setId(UUID.randomUUID().toString());

        // Create the SysPermission
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysPermission() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();
        sysPermission.setId(UUID.randomUUID().toString());

        // Create the SysPermission
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysPermissionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysPermission() throws Exception {
        // Initialize the database
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermissionRepository.saveAndFlush(sysPermission);

        int databaseSizeBeforeDelete = sysPermissionRepository.findAll().size();

        // Delete the sysPermission
        restSysPermissionMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysPermission.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
