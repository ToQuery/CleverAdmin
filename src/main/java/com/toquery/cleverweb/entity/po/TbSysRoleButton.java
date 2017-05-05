package com.toquery.cleverweb.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_sys_role_button")
public class TbSysRoleButton {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rb_id", unique = true, nullable = false)
    private String rbId;

    @Column(name = "role_id", unique = true, nullable = false)
    private String roleId;

    @Column(name = "button_id", unique = true, nullable = false)
    private String buttonId;

    public String getRbId() {
        return rbId;
    }

    public void setRbId(String rbId) {
        this.rbId = rbId == null ? null : rbId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId == null ? null : buttonId.trim();
    }
}