<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Purchase</TITLE>
<LINK REL=STYLESHEET
      HREF="../company-styles.css"
      TYPE="text/css">
</HEAD>
<BODY>
<TABLE BORDER=5 ALIGN="CENTER">
  <TR><TH CLASS="TITLE">Purchase</TABLE>
<P>
<H3><I>hot-dot-com.com</I> congratulates you on a wise 
investment!</H3>
<jsp:useBean id="stock" class="hotdotcom.StockInfo" />
<UL>
  <LI>Current stock value:
      <jsp:getProperty name="stock" property="currentValue" />
  <LI>Predicted value in one year:
      <jsp:getProperty name="stock" property="futureValue" />
</UL>
<FORM ACTION="FinalizePurchase" METHOD="POST">
  <DL>
    <DT>Number of shares:
    <DD><INPUT TYPE="RADIO" NAME="numShares" VALUE="1000">
        1000
    <DD><INPUT TYPE="RADIO" NAME="numShares" VALUE="10000">
        10000
    <DD><INPUT TYPE="RADIO" NAME="numShares" VALUE="100000" 
               CHECKED>
        100000
  </DL>
  Full name: <INPUT TYPE="TEXT" NAME="fullName"><BR>
  Credit card number: <INPUT TYPE="TEXT" NAME="cardNum"><P>
  <CENTER><INPUT TYPE="SUBMIT" VALUE="Confirm Purchase"></CENTER>
</FORM>
</BODY>
</HTML>