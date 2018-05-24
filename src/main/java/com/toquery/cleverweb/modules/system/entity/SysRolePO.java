package com.toquery.cleverweb.modules.system.entity;

import com.toquery.cleverweb.core.entity.BasePoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author toquery
 * @version 1
 */

@Data
@Entity
@Table(name = "tb_sys_role")
@EqualsAndHashCode(callSuper = false)
public class SysRolePO extends BasePoEntity {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 父角色id
     */
    private String parentId;

    /**
     * 组织架构
     */
    private String orgId;

}
