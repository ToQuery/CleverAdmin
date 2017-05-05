package com.toquery.cleverweb.service.impl;

import com.toquery.cleverweb.dao.jpa.ITbSysMenuDao;
import com.toquery.cleverweb.entity.po.TbSysMenu;
import com.toquery.cleverweb.service.ISysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ToQuery on 2016-08-23.
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Resource
    private ITbSysMenuDao sysMenuDao;

    /**
     * 获取所有的菜单
     *
     * @return 所有的菜单
     */
    @Override
    public List<TbSysMenu> findList() {
        return sysMenuDao.findAll();
    }

    /**
     * 通过菜单ID获取菜单数据
     *
     * @param menuId 菜单ID
     * @return 菜单数据
     */
    @Override
    public List<TbSysMenu> findListByMenuId(String menuId) {
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
    public List<TbSysMenu> findListByParentId(String parentMenuId) {
        List<TbSysMenu> sysMenuList = sysMenuDao.findByParentId(parentMenuId);
        //  for (TbSysMenu sysMenu : sysMenuList) {
//          sysMenu.setMenuUrl("menu/toEdit.do?MENU_ID="+sysMenu.getMenuId());
//            sysMenu.setSubMenu(this.findListByParentId(sysMenu.getMenuId()));
//            sysMenu.setTarget("treeFrame");
        //  }
        return sysMenuList;
    }

    @Override
    public void saveAndFlush(TbSysMenu tbSysMenu) {
        sysMenuDao.saveAndFlush(tbSysMenu);
    }

    @Override
    public TbSysMenu getById(String menuId) {
        return sysMenuDao.findOne(menuId);
    }

    @Override
    public void delete(String menuId) {
        sysMenuDao.delete(menuId);
    }
}
