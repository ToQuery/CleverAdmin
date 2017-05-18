package com.toquery.cleverweb.dao.mybatis;

import com.toquery.cleverweb.entity.po.TbSysButton;
import com.toquery.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ToQuery
 * @version 1.0
 */
@Mapper
public interface ITbSysButtonMapper extends BaseMapper<TbSysButton> {

    /**
     * 通过角色的ID获取该角色下的所有按钮
     *
     * @param roleId 角色的ID
     * @return 角色下的所有按钮
     */
    List<TbSysButton> findByRoleId(@Param("rodeId") String roleId);
}
