package com.toquery.cleverweb.entity.po;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_sys_menu")
public class TbSysMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menu_id", unique = true, nullable = false)
    private String menuId;

    @Column(name = "menu_name", unique = true, nullable = false)
    private String menuName;

    @Column(name = "menu_url", unique = true, nullable = false)
    private String menuUrl;

    @Column(name = "parent_id", unique = true, nullable = false)
    private String parentId;

    @Column(name = "menu_order", unique = true, nullable = false)
    private Integer menuOrder;

    @Column(name = "menu_icon", unique = true, nullable = false)
    private String menuIcon;

    @Column(name = "menu_type", unique = true, nullable = false)
    private Integer menuType;

    @Column(name = "menu_state", unique = true, nullable = false)
    private Integer menuState;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }


    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getMenuState() {
        return menuState;
    }

    public void setMenuState(Integer menuState) {
        this.menuState = menuState;
    }
}