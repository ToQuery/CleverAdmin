package com.cleverweb.dao;

import com.cleverweb.core.repository.MyBatisRepository;
import com.cleverweb.entity.po.TbSysMessage;

@MyBatisRepository
public interface TbSysMessageMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(TbSysMessage record);

    int insertSelective(TbSysMessage record);

    TbSysMessage selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(TbSysMessage record);

    int updateByPrimaryKey(TbSysMessage record);
}