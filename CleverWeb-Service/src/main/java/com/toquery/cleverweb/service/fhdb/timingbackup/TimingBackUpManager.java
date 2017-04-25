package com.cleverweb.service.fhdb.timingbackup;

import java.util.List;
import com.cleverweb.core.entity.Page;
import com.cleverweb.common.util.PageData;

/** 
 * 说明： 定时备份接口
 * 创建人：FH Q313596790
 * 创建时间：2016-04-09
 * @version
 */
public interface TimingBackUpManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	/**切换状态
	 * @param pd
	 * @throws Exception
	 */
	public void changeStatus(PageData pd)throws Exception;
	
}

