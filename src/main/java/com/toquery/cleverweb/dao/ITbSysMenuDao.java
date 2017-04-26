package com.toquery.cleverweb.dao;

import com.toquery.cleverweb.entity.po.TbSysMenu;
import com.toquery.cleverweb.entity.po.TbSysRoleButton;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author toquery
 * @version 1
 */
public interface ITbSysMenuDao  extends JpaRepository<TbSysMenu, String> {


    /**
     * 通过父级ID获取菜单列表
     *
     * @param parentMenuId 父级ID
     * @return 菜单列表
     */
    List<TbSysMenu> findByParentId(String parentMenuId);
}
