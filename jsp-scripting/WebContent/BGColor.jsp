<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- 
Example of a JSP scriptlet.
-->
<HTML>
<HEAD>
  <TITLE>Color Testing</TITLE>
</HEAD>
<% 
String bgColor = request.getParameter("bgColor");
String bgPixels = request.getParameter("bgPixels");
if ((bgColor == null) || (bgColor.trim().equals(""))) { 
  bgColor = "WHITE"; 
} 
// if ((bgPixels == null) || (bgPixels.trim().equals(""))) { 
//   bgColor = "WHITE"; 
// } 
%>
<BODY BGCOLOR="<%= bgColor %>">
<H2 ALIGN="CENTER">Testing a Background of "<%= bgColor %>".</H2>
<BR>
<FORM>
  Color: <INPUT TYPE="TEXT" NAME="bgColor"><BR>
  Pixels: <INPUT TYPE="TEXT" NAME="bgPixels"><BR>
  <INPUT TYPE="SUBMIT" VALUE="Try the Color">
</FORM>
</BODY></HTML>