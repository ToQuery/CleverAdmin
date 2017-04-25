package com.toquery.cleverweb.dao;

import com.toquery.cleverweb.core.repository.MyBatisRepository;
import com.toquery.cleverweb.entity.po.TbSysMessage;

@MyBatisRepository
public interface TbSysMessageMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(TbSysMessage record);

    int insertSelective(TbSysMessage record);

    TbSysMessage selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(TbSysMessage record);

    int updateByPrimaryKey(TbSysMessage record);
}