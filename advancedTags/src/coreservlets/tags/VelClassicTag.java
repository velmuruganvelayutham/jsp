package coreservlets.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class VelClassicTag extends TagSupport {
  int count = 0;

  @Override
  public int doStartTag() throws JspException {
    count = 0;
    JspWriter out = pageContext.getOut();
    try {
      out.println(" velmurugan:classic start tag:");
    } catch (IOException e) {
      System.out.println("VelClassicTag.doStartTag()");
    }
    return EVAL_BODY_INCLUDE;
  }

  @Override
  public int doAfterBody() throws JspException {

    if (++count < 30) {
      return EVAL_BODY_AGAIN;
    } else {
      return SKIP_BODY;
    }
  }

  @Override
  public int doEndTag() throws JspException {

    JspWriter out = pageContext.getOut();
    try {
      out.println(" velmurugan:classic end  tag:");
    } catch (IOException e) {
      System.out.println("VelClassicTag.doStartTag()");
    }

    return EVAL_PAGE;
  }
}
