package com.iitdgroup.metrix;

import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iitdgroup.metrix.model.SystemLogon;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private final Logger logger = Logger.getLogger(HomeController.class);
	
	@Resource
	private EntityManagerFactory entityManagerFactory;
	
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home (Locale locale, Model model) {
		logger.log(Level.INFO,String.format("Welcome home! The client locale is %s.", locale));
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public String authors(Locale locale, Model model)
	{
		return "authors";
	}
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help(Locale locale, Model model)
	{
		return "help";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model)
	{
		return "login";
	}
	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String reg(Locale locale, Model model)
	{
		return "reg";
	}
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request)
	{
		Enumeration<String> a = request.getAttributeNames();
		Enumeration<String> b = request.getHeaderNames();
		Enumeration<String> c = request.getParameterNames();
		while(a.hasMoreElements())
		{
			String temp = a.nextElement();
			logger.log(Level.INFO, temp);
			logger.log(Level.INFO, request.getAttribute(temp));
			
		}
		while(b.hasMoreElements())
		{
			String temp = b.nextElement();
			logger.log(Level.INFO, temp);
			logger.log(Level.INFO, request.getHeader(temp));
		}
		while(c.hasMoreElements())
		{
			String temp = c.nextElement();
			logger.log(Level.INFO, temp);
			logger.log(Level.INFO, request.getParameter(temp));
		}
		try
		{
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			SystemLogon systemLogon = new SystemLogon();
			systemLogon.setTimestamp(new Date());
			systemLogon.setCookie(request.getHeader("cookie"));
			systemLogon.setUser_agent(request.getHeader("user-agent"));
			entityManager.persist(systemLogon);
			entityTransaction.commit();
		}
		catch (Exception e)
		{
			logger.log(Level.ERROR, e);
		}
		finally
		{
			if (entityManager!=null && entityManager.isOpen())
			{
				entityManager.close();
			}
			if (entityTransaction!=null && entityTransaction.isActive())
			{
				entityTransaction.rollback();
			}
		}

		return "test";
	}
}
