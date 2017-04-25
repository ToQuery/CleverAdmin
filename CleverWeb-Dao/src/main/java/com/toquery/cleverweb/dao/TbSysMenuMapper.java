package com.cleverweb.dao;

import com.cleverweb.core.repository.MyBatisRepository;
import com.cleverweb.entity.po.TbSysMenu;

import java.util.List;

@MyBatisRepository
public interface TbSysMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(TbSysMenu record);

    int insertSelective(TbSysMenu record);

    TbSysMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(TbSysMenu record);

    int updateByPrimaryKey(TbSysMenu record);

    /**
     * 获取所有的菜单
     *
     * @return 所有的菜单
     */
    List<TbSysMenu> findList();

    /**
     * 通过父级ID获取菜单列表
     *
     * @param parentMenuId 父级ID
     * @return 菜单列表
     */
    List<TbSysMenu> findListByParentId(int parentMenuId);
}