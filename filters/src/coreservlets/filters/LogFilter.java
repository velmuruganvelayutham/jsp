package coreservlets.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*; 

/** Simple filter that prints a report in the log file
 *  whenever the associated servlets or JSP pages
 *  are accessed.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate, JPA, and Java</a>.
 */

public class LogFilter implements Filter {
  protected FilterConfig config;
  private ServletContext context;
  private String filterName;
  
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest req = (HttpServletRequest)request;
    context.log(req.getRemoteHost() +
                " tried to access " +
                req.getRequestURL() +
                " on " + new Date() + ". " +
                "(Reported by " + filterName + ".)");
    chain.doFilter(request,response);
  }

  public void init(FilterConfig config)
      throws ServletException {
    this.config = config; // In case it is needed by subclass.
    context = config.getServletContext();
    filterName = config.getFilterName();
  }
  
  public void destroy() {}
}
