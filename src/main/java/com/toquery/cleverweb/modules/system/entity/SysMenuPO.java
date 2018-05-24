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
@Table(name = "tb_sys_menu")
@EqualsAndHashCode(callSuper = false)
public class SysMenuPO extends BasePoEntity {

    private String menuPath;

    private String menuTitle;
    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单编号
     */
    private String menuCode;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * url地址
     */
    private String url;
    /**
     * 菜单层级
     */
    private Integer menuLevel;
    /**
     * 是否是菜单（1：是  0：不是）
     */
    private Integer isMenu;

    /**
     * 菜单父编号
     */
    private String parentCode = "0";
    /**
     * 当前菜单的所有父菜单编号
     */
    private String parentCodes = "0";


    private String parentId = "0";


}
