package com.toquery.cleverweb.service.impl;

import com.toquery.cleverweb.dao.ITbSysDictDao;
import com.toquery.cleverweb.entity.po.TbSysDict;
import com.toquery.cleverweb.service.ISysDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ToQuery
 * @version 1.0
 * @date 17-4-26.
 */
@Service
public class SysDictServiceImpl implements ISysDictService {
    @Resource
    private ITbSysDictDao sysDictDao;

    @Override
    public void save(TbSysDict tbSysDict) {
        sysDictDao.save(tbSysDict);
    }
}
