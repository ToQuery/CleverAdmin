package com.toquery.cleverweb.web.controller;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author FH Q313596790
 * 修改时间：2015、12、11
 */
public class BaseController {

	@Value(value = "#{COMMON['system.name']}")
	public String systemName = "";
	
	protected Logger logger = LogManager.getLogger(BaseController.class);

}
