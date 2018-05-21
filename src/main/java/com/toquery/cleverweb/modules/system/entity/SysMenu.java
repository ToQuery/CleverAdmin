package com.toquery.cleverweb.modules.system.entity;

import com.toquery.cleverweb.core.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author toquery
 * @version 1
 */
@Entity
@Table(name = "tb_sys_menu")
public class SysMenu extends BaseEntity implements Serializable {


    private String menuName;

    public SysMenu() {
    }

    public SysMenu( String menuName) {
        this.menuName = menuName;
    }


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
