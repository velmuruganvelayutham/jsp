package com.twilio.calltracking;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
 
import com.twilio.sdk.verbs.Dial;
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.Play;
import com.twilio.sdk.verbs.Gather;
 
public class HandleIncomingCallServlet extends HttpServlet {
 
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Create a dict of people we know.
        HashMap<String, String> callers = new HashMap<String, String>();
        callers.put("+14158675309", "Curious George");
        callers.put("+14158675310", "Boots");
        callers.put("+14158675311", "Virgil");
 
        String fromNumber = request.getParameter("From");
        String knownCaller = callers.get(fromNumber);
        String message;
        if (knownCaller == null) {
            // Use a generic message
            message = "Hello Monkey";
        } else {
            // Use the caller's name
            message = "Hello " + knownCaller;
        }
 //Store the information in the database:
        System.out.println("Storing  the incoming call  information in the database:");     
        
        
        System.out.println(request.getParameter("CallSid"));
        System.out.println(request.getParameter("AccountSid"));
        System.out.println(request.getParameter("From"));
        System.out.println(request.getParameter("To"));
        System.out.println(request.getParameter("CallStatus"));
        System.out.println(request.getParameter("ApiVersion"));
        System.out.println(request.getParameter("Direction"));
        
     
        if (request.getParameter("FromCity")!=null){
        	  System.out.println(request.getParameter("FromCity"));
              System.out.println(request.getParameter("FromState"));
              System.out.println(request.getParameter("FromZip"));
              System.out.println(request.getParameter("FromCountry"));
         
        } 
        System.out.println(request.getParameter("ToCity"));
        System.out.println(request.getParameter("ToState"));
        System.out.println(request.getParameter("ToCountry"));
        System.out.println(request.getParameter("ToZip"));
      
        TwiMLResponse twiml = new TwiMLResponse();
		  Dial dial = new Dial("+917406097360");
		  dial.set("record", "true");
		  dial.setAction("/handle-calltracking");
		  dial.setMethod("POST");
		  try { 
		      twiml.append(dial);
		  } catch (TwiMLException e) {
		      e.printStackTrace();
		  }

      response.setContentType("application/xml");
      response.getWriter().print(twiml.toXML());
    }
}