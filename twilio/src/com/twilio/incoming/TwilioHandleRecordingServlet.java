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
import com.twilio.sdk.verbs.Dial;
 
public class TwilioHandleRecordingServlet extends HttpServlet {
 
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
 
        String recordingUrl = request.getParameter("RecordingUrl");
        TwiMLResponse twiml = new TwiMLResponse();
        if (recordingUrl != null) {
            try {
                twiml.append(new Say("Thanks for howling... take a listen to what you howled."));
                twiml.append(new Play(recordingUrl));
                twiml.append(new Say("Goodbye"));
            } catch (TwiMLException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("/twilio");
            return;
        }
 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}