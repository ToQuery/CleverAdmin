package com.toquery.cleverweb.service;

import com.toquery.cleverweb.entity.po.TbSysMenu;
import com.toquery.cleverweb.entity.vo.SysMenu;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-23.
 */
public interface ISysMenuService {
    /**
     * 获取所有的菜单
     * @return  所有的菜单
     */
    List<SysMenu> findList();

    /**
     * 通过菜单ID获取菜单数据
     * @param menuId    菜单ID
     * @return  菜单数据
     */
    List<SysMenu> findListByMenuId(int menuId);


    /**
     * 通过父级ID获取菜单列表
     * @param parentMenuId  父级ID
     * @return  菜单列表
     */
    List<SysMenu> findListByParentId(int parentMenuId);
}
