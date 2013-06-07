package com.twilio.outgoing;


import java.util.Map;
import java.util.HashMap;
 
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.factory.CallFactory;
 
public class MakeCall {
 
    public static final String ACCOUNT_SID = "ACcf18c0a2945caf6b701cd8cdec954a69";
    public static final String AUTH_TOKEN = "9fad5e89fd0d514a378fb3ad27600da3";
 
    public static void main(String[] args) throws TwilioRestException {
 
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account mainAccount = client.getAccount();
        CallFactory callFactory = mainAccount.getCallFactory();
        Map<String, String> callParams = new HashMap<String, String>();
        callParams.put("To", "+1-949-705-6905"); // Replace with your phone number
        callParams.put("From", "571-206-3904"); // Replace with a Twilio number
      //  callParams.put("From", "+917406097360"); // Replace with a Twilio number
        callParams.put("Url", "http://twilio.velmurugan.cloudbees.net/voice.xml");
        // Make the call
        Call call = callFactory.create(callParams);
        // Print the call SID (a 32 digit hex like CA123..)
        System.out.println(call.getSid());
    }
}