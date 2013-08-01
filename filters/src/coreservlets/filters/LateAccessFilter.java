package coreservlets.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

/** Filter that keeps track of accesses that occur
 *  at unusual hours.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate, JPA, and Java</a>.
 */

public class LateAccessFilter implements Filter {
  private ServletContext context;
  private int startTime, endTime;
  private DateFormat formatter;
  
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest req = (HttpServletRequest)request;
    GregorianCalendar calendar = new GregorianCalendar();
    int currentTime = calendar.get(Calendar.HOUR_OF_DAY);
    if (isUnusualTime(currentTime, startTime, endTime)) {
      context.log("WARNING: " +
                  req.getRemoteHost() +
                  " accessed " +
                  req.getRequestURL() +
                  " on " + 
                  formatter.format(calendar.getTime()));
    }
    chain.doFilter(request,response);
  }

  public void init(FilterConfig config)
      throws ServletException {
    context = config.getServletContext();
    formatter =
      DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                                     DateFormat.MEDIUM);
    try {
      startTime =
        Integer.parseInt(config.getInitParameter("startTime"));
      endTime =
        Integer.parseInt(config.getInitParameter("endTime"));
    } catch(NumberFormatException nfe) { // Malformed or null
      // Default: access at or after 10 p.m. but before 6 a.m.
      // is considered unusual.
      startTime = 22; // 10:00 p.m.
      endTime = 6;    //  6:00 a.m.
    }
  }
  
  public void destroy() {}

  // Is the current time between the start and end
  // times that are marked as abnormal access times?

  private boolean isUnusualTime(int currentTime,
                                int startTime,
                                int endTime) {
    // If the start time is less than the end time (i.e.,
    // they are two times on the same day), then the
    // current time is considered unusual if it is
    // between the start and end times.
    if (startTime < endTime) {
      return((currentTime >= startTime) &&
             (currentTime < endTime));
    }
    // If the start time is greater than or equal to the
    // end time (i.e., the start time is on one day and
    // the end time is on the next day), then the current
    // time is considered unusual if it is NOT between
    // the end and start times.
    else {
      return(!isUnusualTime(currentTime, endTime, startTime));
    }
  } 
}
