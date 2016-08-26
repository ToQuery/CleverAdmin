package com.cleverweb.service;

import com.cleverweb.entity.po.TbSysRoleButton;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-23.
 */
public interface ISysRoleButtonService {
    /**
     * 获取所有的关联关系
     * @return
     */
    List<TbSysRoleButton> findList();

    /**
     * 通过角色ID和按钮Id查询关联关系
     * @param roleId
     * @param buttonId
     * @return
     */
    TbSysRoleButton findByRoleIdAndButtonId(String roleId, String buttonId);

    /**
     * 通过主键ID删除数据
     * @param rbId
     */
    void deleteById(String rbId);

    /**
     * 保存角色和按钮的映射关系
     * @param saveTbSysRoleButton
     */
    void save(TbSysRoleButton saveTbSysRoleButton);
}
