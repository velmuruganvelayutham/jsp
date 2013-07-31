package coreservlets.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class VelTag extends SimpleTagSupport {

  private int count;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public void doTag() throws JspException, IOException {
    JspWriter out = getJspContext().getOut();
    for (int i = 0; i <= getCount(); i++) {
      out.print("velmurugan:" + i);
    }
    getJspBody().invoke(null);
  }
}
