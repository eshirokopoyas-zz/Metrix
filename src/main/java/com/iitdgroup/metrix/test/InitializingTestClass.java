package com.iitdgroup.metrix.test;


import javax.sql.DataSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InitializingTestClass implements InitializingBean
{
	private final Logger logger = Logger.getLogger(InitializingBean.class);
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void afterPropertiesSet()
	{
		try
		{
			String sql = "select count(*) from public.test";
			logger.log(Level.ERROR, "!!!");
			jdbcTemplate.execute("insert into test values(123)");
			logger.log(Level.INFO,jdbcTemplate.queryForObject(sql, Integer.class).intValue());
		}
		catch (Exception e)
		{
			logger.log(Level.ERROR, e);
		}
	}
}
