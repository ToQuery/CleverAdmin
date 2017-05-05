package com.toquery.cleverweb.dao.jpa;

import com.toquery.cleverweb.entity.po.TbSysButton;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author toquery
 * @version 1
 */
public interface ITbSysButtonDao extends JpaRepository<TbSysButton, String> {

}
