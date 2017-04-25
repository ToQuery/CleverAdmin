package com.toquery.cleverweb.service.impl;

import com.alibaba.fastjson.JSON;
import com.toquery.cleverweb.dao.TbSysMenuMapper;
import com.toquery.cleverweb.entity.po.TbSysMenu;
import com.toquery.cleverweb.entity.vo.SysMenu;
import com.toquery.cleverweb.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-23.
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {
    @Autowired
    private TbSysMenuMapper sysMenuMapper;

    /**
     * 获取所有的菜单
     *
     * @return 所有的菜单
     */
    @Override
    public List<SysMenu> findList() {
        List<TbSysMenu> sysMenuList = sysMenuMapper.findList();
        return JSON.parseArray(JSON.toJSONString(sysMenuList), SysMenu.class);
    }

    /**
     * 通过菜单ID获取菜单数据
     *
     * @param menuId 菜单ID
     * @return 菜单数据
     */
    @Override
    public List<SysMenu> findListByMenuId(int menuId) {
        return null;
//        List<TbSysMenu> sysMenuList = sysMenuMapper.findListByMenuId(menuId);
//        return JSON.parseArray(JSON.toJSONString(sysMenuList), SysMenu.class);
    }

    /**
     * 通过父级ID获取菜单列表
     *
     * @param parentMenuId 父级ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> findListByParentId(int parentMenuId) {
        List<SysMenu> sysMenuList = JSON.parseArray(JSON.toJSONString(sysMenuMapper.findListByParentId(parentMenuId)), SysMenu.class);
        for (SysMenu sysMenu : sysMenuList) {
//          sysMenu.setMenuUrl("menu/toEdit.do?MENU_ID="+sysMenu.getMenuId());
            sysMenu.setSubMenu(this.findListByParentId(sysMenu.getMenuId()));
            sysMenu.setTarget("treeFrame");
        }
        return sysMenuList;
    }
}
