package com.toquery.cleverweb.dao;

import com.toquery.cleverweb.entity.po.TbSysButton;
import com.toquery.cleverweb.entity.po.TbSysRoleButton;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author toquery
 * @version 1
 */
public interface ITbSysButtonDao extends JpaRepository<TbSysButton, String> {

    /**
     * 通过角色的ID获取该角色下的所有按钮
     *
     * @param roleId 角色的ID
     * @return 角色下的所有按钮
     */
    List<TbSysButton> findByRoleId(String roleId);
}
