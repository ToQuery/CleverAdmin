package com.toquery.cleverweb.service.impl;

import com.toquery.cleverweb.dao.jpa.ITbSysButtonDao;
import com.toquery.cleverweb.dao.mybatis.ITbSysButtonMapper;
import com.toquery.cleverweb.entity.po.TbSysButton;
import com.toquery.cleverweb.service.ISysButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysButtonServiceImpl implements ISysButtonService {
    @Resource
    private ITbSysButtonDao sysButtonDao;

    @Resource
    private ITbSysButtonMapper sysButtonMapper;

    /**
     * 获取所有的按钮
     *
     * @return 所有的按钮
     */
    @Override
    public Page<TbSysButton> findList(Pageable pageable) {
        return sysButtonDao.findAll(pageable);
    }

    @Override
    public List<TbSysButton> findList() {
        return sysButtonDao.findAll();
    }

    /**
     * 通过角色的ID获取该角色下的所有按钮
     *
     * @param roleId 角色的ID
     * @return 角色下的所有按钮
     */
    @Override
    public List<TbSysButton> findListByRoleId(String roleId) {
        return sysButtonMapper.findByRoleId(roleId);
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

    @Override
    public void saveAndFlush(TbSysButton sysButton) {
        sysButtonDao.saveAndFlush(sysButton);
    }
}
