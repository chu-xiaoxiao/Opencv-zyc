package com.zyc.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Init
 *
 */
@WebListener
public class Init implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Init() {
    	System.out.println("**********************************");
    	System.out.println("*    识别程序服务端启动完成                    *");
    	System.out.println("*       by智能媒体信息处理研究室             *");
    	System.out.println("**********************************");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    		 sce.getServletContext().setAttribute("counter", 0);
    }
	
}
