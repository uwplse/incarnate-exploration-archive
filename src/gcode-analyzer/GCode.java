import java.util.*;

public class GCode {
  protected List<String> commands;
  protected String gcodeFileName;

  public GCode(String fn)  {
    this.gcodeFileName = fn;
    this.commands = new ArrayList<String>();
  }

  public void add(String s) {
    this.commands.add(s);
  }
}
