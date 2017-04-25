package com.toquery.cleverweb.dao;

import com.toquery.cleverweb.entity.po.TbSysRole;
import com.toquery.cleverweb.entity.po.TbSysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author toquery
 * @version 1
 */
public interface ITbSysRoleDao extends JpaRepository<TbSysRole, String> {

    /**
     * 通过父级ID获取所有权限信息
     *
     * @param parentId
     * @return
     */
    List<TbSysRole> findByParentId(String parentId);
}
