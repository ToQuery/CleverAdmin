package com.cleverweb.service.impl;

import com.cleverweb.dao.TbSysButtonMapper;
import com.cleverweb.entity.po.TbSysButton;
import com.cleverweb.service.ISysButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-23.
 */
@Service
public class SysButtonServiceImpl implements ISysButtonService {
    @Autowired
    private TbSysButtonMapper sysButtonMapper;
    /**
     * 获取所有的按钮
     *
     * @return  所有的按钮
     */
    @Override
    public List<TbSysButton> findList() {
        return sysButtonMapper.findList();
    }

    /**
     * 通过角色的ID获取该角色下的所有按钮
     *
     * @param roleId 角色的ID
     * @return 角色下的所有按钮
     */
    @Override
    public List<TbSysButton> findListByRoleId(String roleId) {
        return sysButtonMapper.findListByRoleId(roleId);
    }

    /**
     * 保存按钮
     *
     * @param sysButton
     */
    @Override
    public void save(TbSysButton sysButton) {
        sysButtonMapper.insert(sysButton);
    }
}
