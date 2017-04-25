package com.toquery.cleverweb.service.system.fhbutton.impl;

import java.util.List;
import javax.annotation.Resource;

import com.toquery.cleverweb.service.system.fhbutton.FhbuttonManager;
import org.springframework.stereotype.Service;
import com.toquery.cleverweb.core.entity.Page;
import com.toquery.cleverweb.dao.mybatis.DaoSupport;
import com.toquery.cleverweb.common.util.PageData;

/**
 * 说明： 按钮管理
 * 创建人：FH Q313596790
 * 创建时间：2016-01-15
 */
@Service("fhbuttonService")
public class FhbuttonService implements FhbuttonManager {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /**
     * 新增
     *
     * @param pd
     * @throws Exception
     */
    public void save(PageData pd) throws Exception {
        dao.save("FhbuttonMapper.save", pd);
    }

    /**
     * 删除
     *
     * @param pd
     * @throws Exception
     */
    public void delete(PageData pd) throws Exception {
        dao.delete("FhbuttonMapper.delete", pd);
    }

    /**
     * 修改
     *
     * @param pd
     * @throws Exception
     */
    public void edit(PageData pd) throws Exception {
        dao.update("FhbuttonMapper.edit", pd);
    }

    /**
     * 列表
     *
     * @param page
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<PageData> list(Page page) throws Exception {
        return (List<PageData>) dao.findForList("FhbuttonMapper.datalistPage", page);
    }

    /**
     * 列表(全部)
     *
     * @param pd
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<PageData> listAll(PageData pd)  {
        return (List<PageData>) dao.findForList("FhbuttonMapper.listAll",pd);
    }

    /**
     * 通过id获取数据
     *
     * @param pd
     * @throws Exception
     */
    public PageData findById(PageData pd) throws Exception {
        return (PageData) dao.findForObject("FhbuttonMapper.findById", pd);
    }

    /**
     * 批量删除
     *
     * @param ArrayDATA_IDS
     * @throws Exception
     */
    public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
        dao.delete("FhbuttonMapper.deleteAll", ArrayDATA_IDS);
    }

}

