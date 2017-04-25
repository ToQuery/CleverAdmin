package com.toquery.cleverweb.service.impl;

import com.toquery.cleverweb.dao.TbSysRoleButtonMapper;
import com.toquery.cleverweb.entity.po.TbSysRoleButton;
import com.toquery.cleverweb.service.ISysRoleButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-23.
 */
@Service
public class SysRoleButtonServiceImpl implements ISysRoleButtonService {

    @Autowired
    private TbSysRoleButtonMapper sysRoleButtonMapper;

    /**
     * 获取所有的关联关系
     *
     * @return
     */
    @Override
    public List<TbSysRoleButton> findList() {
        return sysRoleButtonMapper.findList();
    }

    /**
     * 通过角色ID和按钮Id查询关联关系
     *
     * @param roleId
     * @param buttonId
     * @return
     */
    @Override
    public TbSysRoleButton findByRoleIdAndButtonId(String roleId, String buttonId) {
        return sysRoleButtonMapper.findByRoleIdAndButtonId(roleId, buttonId);
    }

    /**
     * 通过主键ID删除数据
     *
     * @param rbId
     */
    @Override
    public void deleteById(String rbId) {
        sysRoleButtonMapper.deleteByPrimaryKey(rbId);
    }

    /**
     * 保存角色和按钮的映射关系
     *
     * @param saveTbSysRoleButton
     */
    @Override
    public void save(TbSysRoleButton saveTbSysRoleButton) {
        sysRoleButtonMapper.insert(saveTbSysRoleButton);
    }
}
