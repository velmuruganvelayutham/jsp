package coreservlets.filters;

import java.io.*;
import java.util.zip.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Filter that compresses output with gzip
 *  (assuming that browser supports gzip).
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate, JPA, and Java</a>.
 */

public class CompressionFilter implements Filter {
  private FilterConfig config;

  /** If browser does not support gzip, invoke resource
   *  normally. If browser <I>does</I> support gzip,
   *  set the Content-Encoding response header and
   *  invoke resource with a wrapped response that
   *  collects all the output. Extract the output
   *  and write it into a gzipped byte array. Finally,
   *  write that array to the client's output stream.
   */
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest req = (HttpServletRequest)request;
    HttpServletResponse res = (HttpServletResponse)response;
    if (!isGzipSupported(req)) {
      // Invoke resource normally.
      chain.doFilter(req,res);
    } else {
      // Tell browser we are sending it gzipped data.
      res.setHeader("Content-Encoding", "gzip");
      // Invoke resource, accumulating output in the wrapper.
      StringWrapper responseWrapper =
        new StringWrapper(res);
      chain.doFilter(req,responseWrapper);
      // Make a writer that compresses data and puts
      // it into a byte array.
      ByteArrayOutputStream byteStream =
        new ByteArrayOutputStream();
      GZIPOutputStream zipOut =
        new GZIPOutputStream(byteStream);
      OutputStreamWriter tempOut =
        new OutputStreamWriter(zipOut);
      // Compress original output and put it into byte array.
      tempOut.write(responseWrapper.toString());
      // Gzip streams must be explicitly closed.
      tempOut.close();
      // Send compressed result to client.
      OutputStream realOut = res.getOutputStream();
      byteStream.writeTo(realOut);
    }
  }

  /** Store the FilterConfig object in case subclasses
   *  want it.
   */
  public void init(FilterConfig config)
      throws ServletException {
    this.config = config;
  }

  protected FilterConfig getFilterConfig() {
    return(config);
  }

  public void destroy() {}

  private boolean isGzipSupported(HttpServletRequest req) {
    String browserEncodings =
      req.getHeader("Accept-Encoding");
    return((browserEncodings != null) &&
           (browserEncodings.contains("gzip")));
  }
}
