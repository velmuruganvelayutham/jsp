package com.sigma.mailchimp.sigma_mailchimp;

import java.io.IOException;
import java.util.Arrays;

import com.ecwid.mailchimp.MailChimpClient;
import com.ecwid.mailchimp.MailChimpException;
import com.ecwid.mailchimp.MailChimpObject;
import com.ecwid.mailchimp.method.list.ListMemberInfoMethod;
import com.ecwid.mailchimp.method.list.ListMemberInfoResult;
import com.ecwid.mailchimp.method.list.ListSubscribeMethod;
import com.ecwid.mailchimp.method.list.MemberInfo;


//Holds a subscriber's merge_vars info (see http://apidocs.mailchimp.com/api/1.3/listsubscribe.func.php )
public class ListTest extends MailChimpObject {
 @Field
 public String EMAIL, FNAME, LNAME;

 public ListTest() { }

 public ListTest(String email, String fname, String lname) {
     this.EMAIL = email;
     this.FNAME = fname;
     this.LNAME = lname;
 }
public void list() throws IOException, MailChimpException{

	//reuse the same MailChimpClient object whenever possible
	MailChimpClient mailChimpClient = new MailChimpClient(); 

	//Subscribe a person
	ListSubscribeMethod listSubscribeMethod = new ListSubscribeMethod();
	listSubscribeMethod.apikey = "40ca749935ca12480d1ecfec2fc4ffe5-us7";
	listSubscribeMethod.id = "e061188dd8";
	listSubscribeMethod.email_address = "mailtovelmuruga@gmail.com";
	listSubscribeMethod.double_optin = false;
	listSubscribeMethod.update_existing = true;
	listSubscribeMethod.merge_vars = new ListTest("mailtovelmuruga@gmail.com", "Vasya", "Pupkin");
	mailChimpClient.execute(listSubscribeMethod);

	//check his status
	ListMemberInfoMethod listMemberInfoMethod = new ListMemberInfoMethod();
	listMemberInfoMethod.apikey = "40ca749935ca12480d1ecfec2fc4ffe5-us7";
	listMemberInfoMethod.id = "e061188dd8";
	listMemberInfoMethod.email_address = Arrays.asList("mailtovelmuruga@gmail.com");

	ListMemberInfoResult listMemberInfoResult = mailChimpClient.execute(listMemberInfoMethod);
	MemberInfo info = listMemberInfoResult.data.get(0);
	System.out.println(info.email + ": "+  info.status);
}
public static void main(String args[]) throws IOException, MailChimpException{
	new ListTest().list();
}
}