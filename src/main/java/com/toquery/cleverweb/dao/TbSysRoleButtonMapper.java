package com.toquery.cleverweb.dao;

import com.toquery.cleverweb.core.repository.MyBatisRepository;
import com.toquery.cleverweb.entity.po.TbSysRoleButton;

import java.util.List;

@MyBatisRepository
public interface TbSysRoleButtonMapper {
    int deleteByPrimaryKey(String rbId);

    int insert(TbSysRoleButton record);

    int insertSelective(TbSysRoleButton record);

    TbSysRoleButton selectByPrimaryKey(String rbId);

    int updateByPrimaryKeySelective(TbSysRoleButton record);

    int updateByPrimaryKey(TbSysRoleButton record);

    /**
     * 获取所有的关联关系
     *
     * @return
     */
    List<TbSysRoleButton> findList();

    /**
     * 通过角色ID和按钮Id查询关联关系
     *
     * @param roleId
     * @param buttonId
     * @return
     */
    TbSysRoleButton findByRoleIdAndButtonId(String roleId, String buttonId);
}