package com.toquery.cleverweb.entity.vo;

import com.alibaba.fastjson.JSON;
import com.toquery.cleverweb.entity.po.TbSysRole;
import com.toquery.cleverweb.entity.po.TbSysUser;

/**
 * Created by ToQuery on 2016-08-20.
 */
public class SysUserRole extends TbSysUser {

    public SysUserRole() {

    }

    public SysUserRole convert(TbSysUser sysUser) {
        SysUserRole sysUserRole = JSON.parseObject(JSON.toJSONString(sysUser), this.getClass());
        return sysUserRole;
    }

    private TbSysRole role;

    public TbSysRole getRole() {
        return role;
    }

    public void setRole(TbSysRole role) {
        this.role = role;
    }
}
