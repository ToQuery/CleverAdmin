package com.toquery.cleverweb.modules.system.entity;

import java.io.Serializable;

/**
 * @author toquery
 * @version 1
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String menuName;

    public SysMenu() {
    }

    public SysMenu(String id, String menuName) {
        this.id = id;
        this.menuName = menuName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
