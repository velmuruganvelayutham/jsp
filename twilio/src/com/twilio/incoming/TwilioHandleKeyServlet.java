
package com.twilio.incoming;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
 
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.Dial;
 
public class TwilioHandleKeyServlet extends HttpServlet {
 
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
 
        String digits = request.getParameter("Digits");
        TwiMLResponse twiml = new TwiMLResponse();
        // Check if the user pressed "1" on their phone
        if (digits != null && digits.equals("1")) {
            // Connect 310 555 1212 to the incoming caller.
            Dial dial = new Dial("+917411877916");
 
            // If the above dial failed, say an error message.
            Say say = new Say("The call failed, or the remote party hung up. Goodbye.");
            try { 
                twiml.append(dial);
                twiml.append(say);
            } catch (TwiMLException e) {
                e.printStackTrace();
            }
        } else {
            // If they didn't press 1, redirect them to the TwilioServlet
            response.sendRedirect("/twilio");
            return;
        }
 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}