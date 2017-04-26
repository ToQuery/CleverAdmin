package com.toquery.cleverweb.dao;

import com.toquery.cleverweb.entity.po.TbSysMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author toquery
 * @version 1
 */
public interface ITbSysMessageDao extends JpaRepository<TbSysMessage, String> {
}
