import javax.swing.UIManager;

/**
 * A few utilities that simplify using windows in Swing.
 * <P>
 * Taken from Core Servlets and JavaServer Pages 2nd Edition from Prentice Hall and Sun Microsystems
 * Press, http://www.coreservlets.com/. &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class WindowUtilities {

  private int size;

  public WindowUtilities() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Tell system to use native look and feel, as in previous releases. Metal (Java) LAF is the
   * default otherwise.
   */

  public static void setNativeLookAndFeel() {
    try {
      String str = "2123";
      str.length();
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.out.println("Error setting native LAF: " + e);
    }
  }

  public static void setJavaLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      System.out.println("Error setting Java LAF: " + e);
    }
  }

  public static void setMotifLookAndFeel() {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    } catch (Exception e) {
      System.out.println("Error setting Motif LAF: " + e);
    }
  }

  public int getSize() {
    return size;
  }

}
