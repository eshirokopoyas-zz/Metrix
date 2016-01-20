package com.iitdgroup.metrix.hsqldb;

import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.springframework.context.SmartLifecycle;

public class HyperSqlDbServer implements SmartLifecycle
{
	private final Logger logger = Logger.getLogger(HyperSqlDbServer.class);
	private HsqlProperties properties;
	private Server server;
	private boolean running = false;
	
	public HyperSqlDbServer(Properties props)
	{
		properties = new HsqlProperties(props);
	}
	
	@Override
	public boolean isRunning()
	{
		if (server!=null)
		{
			server.checkRunning(running);
		}
		return running;
	}

	@Override
	public void start()
	{
		if (server == null)
		{
			logger.log(Level.INFO, "Starting HSQL Server...");
			server = new Server();
			try
			{
				server.setProperties(properties);
				server.start();
				running = true;
			}
			catch (Exception e)
			{
				logger.log(Level.ERROR, "Error starting HSQL server.");
			}
		}
	}

	@Override
	public void stop()
	{
		logger.log(Level.INFO, "Stopping HSQL Server...");
		if (server != null)
		{
			server.stop();
			running = false;
		}
	}

	@Override
	public int getPhase()
	{
		return 0;
	}

	@Override
	public boolean isAutoStartup()
	{
		return true;
	}

	@Override
	public void stop(Runnable runnable)
	{
		stop();
		runnable.run();
	}

}
