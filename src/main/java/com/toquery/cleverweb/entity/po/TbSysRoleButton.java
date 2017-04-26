package com.toquery.cleverweb.entity.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<TbSysButton> sysButtonSet;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<TbSysRole> sysRoleSet;

    public Set<TbSysButton> getSysButtonSet() {
        return sysButtonSet;
    }

    public void setSysButtonSet(Set<TbSysButton> sysButtonSet) {
        this.sysButtonSet = sysButtonSet;
    }

    public Set<TbSysRole> getSysRoleSet() {
        return sysRoleSet;
    }

    public void setSysRoleSet(Set<TbSysRole> sysRoleSet) {
        this.sysRoleSet = sysRoleSet;
    }

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