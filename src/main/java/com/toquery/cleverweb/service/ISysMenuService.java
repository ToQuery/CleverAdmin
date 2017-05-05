package com.toquery.cleverweb.service;

import com.toquery.cleverweb.entity.po.TbSysMenu;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-23.
 */
public interface ISysMenuService {
    /**
     * 获取所有的菜单
     * @return  所有的菜单
     */
    List<TbSysMenu> findList();

    /**
     * 通过菜单ID获取菜单数据
     * @param menuId    菜单ID
     * @return  菜单数据
     */
    List<TbSysMenu> findListByMenuId(String menuId);


    /**
     * 通过父级ID获取菜单列表
     * @param parentMenuId  父级ID
     * @return  菜单列表
     */
    List<TbSysMenu> findListByParentId(String parentMenuId);

    public void saveAndFlush(TbSysMenu tbSysMenu);


    TbSysMenu getById(String menuId);

    void delete(String menuId);
}
