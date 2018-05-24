package com.toquery.cleverweb.modules.system.entity.vo;

import com.toquery.cleverweb.modules.system.entity.SysUserPO;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author toquery
 * @version 1
 */
@Projection(types = SysUserPO.class,name = "mgr")
public class SysUserVO {
}
