package com.toquery.cleverweb.service.impl;

import com.toquery.cleverweb.dao.ITbSysButtonDao;
import com.toquery.cleverweb.entity.po.TbSysButton;
import com.toquery.cleverweb.service.ISysButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-23.
 */
@Service
public class SysButtonServiceImpl implements ISysButtonService {
    @Autowired
    private ITbSysButtonDao sysButtonDao;
    /**
     * 获取所有的按钮
     *
     * @return  所有的按钮
     */
    @Override
    public Page<TbSysButton> findList(Pageable pageable) {
        return sysButtonDao.findAll(pageable);
    }

    /**
     * 通过角色的ID获取该角色下的所有按钮
     *
     * @param roleId 角色的ID
     * @return 角色下的所有按钮
     */
    @Override
    public List<TbSysButton> findListByRoleId(String roleId) {
        return sysButtonDao.findByRoleId(roleId);
    }

    /**
     * 保存按钮
     *
     * @param sysButton
     */
    @Override
    public void save(TbSysButton sysButton) {
        sysButtonDao.save(sysButton);
    }
}
