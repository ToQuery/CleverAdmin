package com.toquery.cleverweb.entity.po;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_sys_button")
public class TbSysButton {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "button_id", unique = true, nullable = false)
    private String buttonId;

    @Column(name = "button_name", unique = true, nullable = false)
    private String buttonName;

    @Column(name = "qx_name", unique = true, nullable = false)
    private String qxName;

    @Column(name = "remark", unique = true, nullable = false)
    private String remark;

    @ManyToMany(targetEntity = TbSysRoleButton.class)
     @JoinColumn(name = "button_id",referencedColumnName = "button_id")
    private Set<TbSysRoleButton> sysRoleButtonSet;

    public Set<TbSysRoleButton> getSysRoleButtonSet() {
        return sysRoleButtonSet;
    }

    public void setSysRoleButtonSet(Set<TbSysRoleButton> sysRoleButtonSet) {
        this.sysRoleButtonSet = sysRoleButtonSet;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId == null ? null : buttonId.trim();
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName == null ? null : buttonName.trim();
    }

    public String getQxName() {
        return qxName;
    }

    public void setQxName(String qxName) {
        this.qxName = qxName == null ? null : qxName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}