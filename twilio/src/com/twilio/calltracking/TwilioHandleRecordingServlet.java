package com.twilio.calltracking;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
 
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.Play;
import com.twilio.sdk.verbs.Dial;
 
public class TwilioHandleRecordingServlet extends HttpServlet {
 
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
 
    	  TwiMLResponse twiml = new TwiMLResponse();
          // Connect 310 555 1212 to the incoming caller.
		 Say say =new Say("GoodBye");
		  try { 
		      twiml.append(say);
		    //  twiml.append(say);
		  } catch (TwiMLException e) {
		      e.printStackTrace();
		  }
 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}