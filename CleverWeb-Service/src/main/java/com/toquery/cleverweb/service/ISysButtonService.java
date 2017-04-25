package com.cleverweb.service;

import com.cleverweb.entity.po.TbSysButton;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-23.
 */
public interface ISysButtonService {
    /**
     * 获取所有的按钮
     * @return
     */
    List<TbSysButton> findList();

    /**
     * 通过角色的ID获取该角色下的所有按钮
     * @param roleId    角色的ID
     * @return  角色下的所有按钮
     */
    List<TbSysButton> findListByRoleId(String roleId);

    /**
     * 保存按钮
     * @param sysButton
     */
    void save(TbSysButton sysButton);
}
