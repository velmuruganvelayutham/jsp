package stocks;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

/**
 * Servlet that gives very hot stock tips. So hot that only authorized users (presumably ones who
 * have paid the steep financial advisory fee) can access the servlet.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the coreservlets.com tutorials
 * on servlets, JSP, Struts, JSF, Ajax, GWT, Spring, Hibernate, JPA, and Java</a>.
 */

public class StockTip extends HttpServlet {

  /**
   * Denies access to all users except those who know the magic secret username/password
   * combination.
   */

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String authorization = request.getHeader("Authorization");
    if (authorization == null) {
      askForPassword(response);
    } else {
      // Authorization headers looks like "Basic blahblah",
      // where blahblah is the base64 encoded username and
      // password. We want the part after "Basic ".
      String userInfo = authorization.substring(6).trim();
      BASE64Decoder decoder = new BASE64Decoder();
      String nameAndPassword = new String(decoder.decodeBuffer(userInfo));
      // Decoded part looks like "username:password".
      int index = nameAndPassword.indexOf(":");
      String user = nameAndPassword.substring(0, index);
      String password = nameAndPassword.substring(index + 1);
      // High security: username must be reverse of password.
      if (areEqualReversed(user, password)) {
        showStock(request, response);
      } else {
        askForPassword(response);
      }
    }
  }

  // Show a Web page giving the symbol of the next hot stock.

  private void showStock(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
    out.println(docType + "<HTML>\n" + "<HEAD><TITLE>Hot Stock Tip!</TITLE></HEAD>\n"
        + "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1>Today's Hot Stock:");
    for (int i = 0; i < 3; i++) {
      out.print(randomLetter());
    }
    out.println("</H1>\n" + "</BODY></HTML>");
  }

  // If no Authorization header was supplied in the request.

  private void askForPassword(HttpServletResponse response) {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // I.e., 401
    response.setHeader("WWW-Authenticate", "BASIC realm=\"Insider-Trading\"");
  }

  // Returns true if s1 is the reverse of s2.
  // Empty strings don't count.

  private boolean areEqualReversed(String s1, String s2) {
    s2 = (new StringBuffer(s2)).reverse().toString();
    return ((s1.length() > 0) && s1.equals(s2));
  }

  private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  // Returns a random number from 0 to n-1 inclusive.

  private int randomInt(int n) {
    return ((int) (Math.random() * n));
  }

  // A random letter from the alphabet.

  private char randomLetter() {
    return (ALPHABET.charAt(randomInt(ALPHABET.length())));
  }
}
