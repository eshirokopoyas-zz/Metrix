package com.iitdgroup.metrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.RequestFacade;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private final Logger logger = Logger.getLogger(HomeController.class);
	
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
	public @ResponseBody String test(HttpServletRequest request, HttpServletResponse response) throws IOException
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
		

		return "test";
	}
}
