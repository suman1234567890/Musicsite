package listener;

import java.util.GregorianCalendar;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class ApplicationListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
      
    	ArrayList a=new ArrayList();
    	sce.getServletContext().setAttribute("CurrentUser", a);


  }

    public void contextDestroyed(ServletContextEvent sce) {
       
    }

}