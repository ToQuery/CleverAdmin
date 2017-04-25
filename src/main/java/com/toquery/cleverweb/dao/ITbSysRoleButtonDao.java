package com.toquery.cleverweb.dao;

import com.toquery.cleverweb.entity.po.TbSysRole;
import com.toquery.cleverweb.entity.po.TbSysRoleButton;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author toquery
 * @version 1
 */
public interface ITbSysRoleButtonDao extends JpaRepository<TbSysRoleButton, String> {

    /**
     * 通过角色ID和按钮Id查询关联关系
     *
     */
    TbSysRoleButton findByRoleIdAndButtonId(String roleId, String buttonId);
}
