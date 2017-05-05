package com.toquery.cleverweb.service.impl;

import com.toquery.cleverweb.dao.jpa.ITbSysMessageDao;
import com.toquery.cleverweb.entity.po.TbSysMessage;
import com.toquery.cleverweb.service.ISysMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ToQuery
 * @version 1.0
 * @date 17-4-26.
 */
@Service
public class SysMessageServiceImpl implements ISysMessageService {
    @Autowired
    private ITbSysMessageDao sysMessageDao;

    /**
     * 新增
     *
     * @param sysMessage
     */
    @Override
    public void save(TbSysMessage sysMessage) {
        sysMessageDao.save(sysMessage);
    }

    /**
     * 删除
     *
     * @param messageId
     */
    @Override
    public void delete(String messageId) {
        sysMessageDao.delete(messageId);
    }

    /**
     * 修改
     *
     * @param tbSysMessage
     */
    @Override
    public void edit(TbSysMessage tbSysMessage) {
        sysMessageDao.saveAndFlush(tbSysMessage);
    }

    /**
     * 列表
     *
     * @param pageable
     */
    @Override
    public Page<TbSysMessage> list(Pageable pageable) {
        return sysMessageDao.findAll(pageable);
    }

    /**
     * 列表(全部)
     */
    @Override
    public List<TbSysMessage> findAll() {
        return sysMessageDao.findAll();
    }

    /**
     * 通过id获取数据
     *
     * @param id
     */
    @Override
    public TbSysMessage findById(String id) {
        return sysMessageDao.findOne(id);
    }

    /**
     * 获取未读总数
     *
     * @param USERNAME
     */
    @Override
    public Integer findFhsmsCount(String USERNAME) {
       // return sysMessageDao.count(null);
        return 1;
    }

    /**
     * 批量删除
     *
     * @param ArrayDATA_IDS
     */
    @Override
    public void deleteAll(String[] ArrayDATA_IDS) {
    }
}
