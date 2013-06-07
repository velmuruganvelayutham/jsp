package com.twilio.incoming;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
 
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.Play;
import com.twilio.sdk.verbs.Gather;
 
public class TwilioServletVoiceMessage extends HttpServlet {
 
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
 
        // Create a TwiML response and add our friendly message.
        TwiMLResponse twiml = new TwiMLResponse();
        Say say = new Say(message);
 
        // Play an MP3 for incoming callers.
        Play play = new Play("http://twilio.velmurugan.cloudbees.net/monkey.mp3");
 
        Gather gather = new Gather();
        gather.setAction("/handle-two-key");
        gather.setNumDigits(1);
        gather.setMethod("POST");
        Say sayInGather = new Say("To speak to a real monkey, press 1. " + 
                "Press 2 to record your own monkey howl. " + 
                "Press any other key to start over.");
        try {
            gather.append(sayInGather);
            twiml.append(say);
            twiml.append(play);
            twiml.append(gather);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}