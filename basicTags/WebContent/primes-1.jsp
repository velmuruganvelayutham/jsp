<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Some N-Digit Primes</TITLE>
<LINK REL=STYLESHEET
      HREF="JSP-Styles.css"
      TYPE="text/css">
</HEAD>
<BODY>
<H1>Some N-Digit Primes</H1>
<%@ taglib uri="/WEB-INF/tlds/csajsp-taglib.tld" 
           prefix="csajsp" %>
<UL>
  <LI>20-digit: <csajsp:prime length="20"/>
  <LI>40-digit: <csajsp:prime length="40"/>
  <LI>80-digit: <csajsp:prime length="80"/>
  <LI>Default (50-digit): <csajsp:prime />
</UL>
</BODY></HTML>