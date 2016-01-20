package com.iitdgroup.metrix.context;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.ehcache.CacheManager;
import org.ehcache.CacheManagerBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class ContextBuilder implements InitializingBean
{	
	private Logger logger = Logger.getLogger(ContextBuilder.class);
	@SuppressWarnings("unused")
	private CacheManager cchm;
	@Override
	public void afterPropertiesSet()
	{
		logger.log(Level.INFO,"Context initializing...");
		try
		{
			cchm = CacheManagerBuilder
					.newCacheManagerBuilder()
					.build(true);
		}
		catch (Exception exception)
		{
			logger.log(Level.ERROR, "CacheManager building error!");
			logger.log(Level.ERROR, exception);
		}
		logger.log(Level.INFO, "Initializing completed.");
	}
}