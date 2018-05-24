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
@Table(name = "tb_sys_org")
@EqualsAndHashCode(callSuper = false)
public class SysOrgPO extends BasePoEntity {

    /**
     * 组织名称
     */
    private String orgName;


    private int orgLevel = 1;
    /**
     * 父部门id
     */
    private String parentId;
    /**
     * 父级ids
     */
    private String parentIds;

}
