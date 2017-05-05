package com.toquery.cleverweb.dao.mybatis;

import com.toquery.cleverweb.entity.po.TbSysButton;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ToQuery
 * @version 1.0
 */
@Component
public interface ITbSysButtonMapper  {

    /**
     * 通过角色的ID获取该角色下的所有按钮
     *
     * @param roleId 角色的ID
     * @return 角色下的所有按钮
     */
    List<TbSysButton> findByRoleId(@Param("rodeId") String roleId);
}
