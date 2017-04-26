package com.toquery.cleverweb.service;

import com.toquery.cleverweb.entity.po.TbSysMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ToQuery
 * @version 1.0
 * @date 17-4-26.
 */
public interface ISysMessageService {

    /**
     * 新增
     */
    public void save(TbSysMessage sysMessage) ;

    /**
     * 删除
     */
    public void delete(String messageId) ;

    /**
     * 修改
     */
    public void edit(TbSysMessage tbSysMessage) ;

    /**
     * 列表
     *
     */
    public Page<TbSysMessage> list(Pageable pageable) ;

    /**
     * 列表(全部)
     */
    public List<TbSysMessage> findAll() ;

    /**
     * 通过id获取数据
     */
    public TbSysMessage findById(String id) ;

    /**
     * 获取未读总数
     */
    public Integer findFhsmsCount(String USERNAME);

    /**
     * 批量删除
     *
     * @param ArrayDATA_IDS
     */
    public void deleteAll(String[] ArrayDATA_IDS) ;
}
