package coreservlets.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagDependent extends SimpleTagSupport {
  @Override
  public void doTag() throws JspException, IOException {

    StringWriter writer = new StringWriter();
    getJspBody().invoke(writer);
    writer.append("this velmurugan adding  extra values at the end of the body");
    getJspContext().getOut().write(writer.toString());
  }

}
