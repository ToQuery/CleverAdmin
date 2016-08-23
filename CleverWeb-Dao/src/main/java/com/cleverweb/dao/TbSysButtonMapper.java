package com.cleverweb.dao;

import com.cleverweb.core.repository.MyBatisRepository;
import com.cleverweb.entity.po.TbSysButton;

import java.util.List;

@MyBatisRepository
public interface TbSysButtonMapper {
    int deleteByPrimaryKey(String buttonId);

    int insert(TbSysButton record);

    int insertSelective(TbSysButton record);

    TbSysButton selectByPrimaryKey(String buttonId);

    int updateByPrimaryKeySelective(TbSysButton record);

    int updateByPrimaryKey(TbSysButton record);

    /**
     * 获取所有的按钮
     *
     * @return  所有的按钮
     */
    List<TbSysButton> findList();

    /**
     * 通过角色的ID获取该角色下的所有按钮
     *
     * @param roleId 角色的ID
     * @return 角色下的所有按钮
     */
    List<TbSysButton> findListByRoleId(String roleId);
}