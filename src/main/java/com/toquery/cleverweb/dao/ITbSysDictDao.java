package com.toquery.cleverweb.dao;

import com.toquery.cleverweb.entity.po.TbSysButton;
import com.toquery.cleverweb.entity.po.TbSysDict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author toquery
 * @version 1
 */
public interface ITbSysDictDao extends JpaRepository<TbSysDict, String> {

}
