package com.toquery.cleverweb.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "tb_sys_role")
public class TbSysRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", unique = true, nullable = false)
    private String roleId;

    @Column(name = "role_name", unique = true, nullable = false)
    private String roleName;

    @Column(name = "rights", unique = true, nullable = false)
    private String rights;

    @Column(name = "parent_id", unique = true, nullable = false)
    private String parentId;

    @Column(name = "add_qx", unique = true, nullable = false)
    private String addQx;

    @Column(name = "del_qx", unique = true, nullable = false)
    private String delQx;

    @Column(name = "edit_qx", unique = true, nullable = false)
    private String editQx;

    @Column(name = "cha_qx", unique = true, nullable = false)
    private String chaQx;

    @ManyToMany
    @JoinTable(name = "tb_sys_role_button", joinColumns = @JoinColumn(name = "role_id"))
    private Set<TbSysRoleButton> sysRoleButtonSet;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights == null ? null : rights.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getAddQx() {
        return addQx;
    }

    public void setAddQx(String addQx) {
        this.addQx = addQx == null ? null : addQx.trim();
    }

    public String getDelQx() {
        return delQx;
    }

    public void setDelQx(String delQx) {
        this.delQx = delQx == null ? null : delQx.trim();
    }

    public String getEditQx() {
        return editQx;
    }

    public void setEditQx(String editQx) {
        this.editQx = editQx == null ? null : editQx.trim();
    }

    public String getChaQx() {
        return chaQx;
    }

    public void setChaQx(String chaQx) {
        this.chaQx = chaQx == null ? null : chaQx.trim();
    }
}