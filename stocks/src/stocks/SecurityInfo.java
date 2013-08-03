package stocks;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.security.cert.*; // For X509Certificate

/** Servlet that prints information on SSL requests. Non-SSL
 *  requests get redirected to SSL.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate, JPA, and Java</a>.
 */

public class SecurityInfo extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    // Redirect non-SSL requests to the SSL equivalent.
    if (request.getScheme().equalsIgnoreCase("http")) {
      String origURL = request.getRequestURL().toString();
      String newURL = httpsURL(origURL);
      String formData = request.getQueryString();
      if (formData != null) {
        newURL = newURL + "?" + formData;
      }
      response.sendRedirect(newURL);
    } else {
      String currentURL = request.getRequestURL().toString();
      String formData = request.getQueryString();
  
      PrintWriter out = response.getWriter();
      String docType =
        "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
        "Transitional//EN\">\n";
      String title = "Security Info";
      out.println
        (docType +
         "<HTML>\n" +
         "<HEAD><TITLE>" + title +
         "</TITLE></HEAD>\n" +
         "<BODY BGCOLOR=\"#FDF5E6\">\n" +
         "<H1>" + title + "</H1>\n" +
         "<UL>\n" +
         "  <LI>URL: " + currentURL + "\n" +
         "  <LI>Data: " + formData);
      boolean isSecure = request.isSecure();
      if (isSecure) {
        String keyAttribute =
          "javax.servlet.request.key_size";
        // Available only with servlets 2.3
        Integer keySize =
          (Integer)request.getAttribute(keyAttribute);
        String sizeString =
          replaceNull(keySize, "Unknown");
        String cipherAttribute =
          "javax.servlet.request.cipher_suite";
        // Available only with servlets 2.3
        String cipherSuite =
          (String)request.getAttribute(cipherAttribute);
        String cipherString =
          replaceNull(cipherSuite, "Unknown");
        String certAttribute =
          "javax.servlet.request.X509Certificate";
        // Available with servlets 2.2 and 2.3
        X509Certificate certificate =
          (X509Certificate)request.getAttribute(certAttribute);
        String certificateString =
          replaceNull(certificate, "None");
        out.println
          ("  <LI>SSL: true\n" +
           "  <UL>\n" +
           "    <LI>Key Size: " + sizeString + "\n" +
           "    <LI>Cipher Suite: " + cipherString + "\n" +
           "    <LI>Client Certificate: " +
           certificateString + "\n" +
           "  </UL>");
      }
      out.println
        ("</UL>\n" +
         "</BODY></HTML>");
    }
  }

  // Given http://blah, return https://blah.
  
  private String httpsURL(String origURL) {
    int index = origURL.indexOf(":");
    StringBuffer newURL = new StringBuffer(origURL);
    newURL.insert(index, 's');
    return(newURL.toString());
  }

  // If the first argument is null, return the second argument.
  // Otherwise, convert first argument to a String and
  // return that String.

  private String replaceNull(Object obj, String fallback) {
    if (obj == null) {
      return(fallback);
    } else {
      return(obj.toString());
    }
  }
}

