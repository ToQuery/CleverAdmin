package com.cleverweb.entity.vo;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.cleverweb.entity.po.TbSysRole;
import com.cleverweb.entity.po.TbSysUser;

/**
 * Created by ToQuery on 2016-08-20.
 */
public class TbSysUserRole extends TbSysUser {

    public TbSysUserRole() {

    }

    public TbSysUserRole convert(TbSysUser sysUser) {
        TbSysUserRole sysUserRole = JSON.parseObject(JSON.toJSONString(sysUser), this.getClass());
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
