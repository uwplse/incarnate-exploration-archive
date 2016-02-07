import java.util.*;

// Parent class of all GCode subclasses. Contains arraylist of commands.
public class GCode {
  protected List<String> commands;
  protected String gcodeFileName;

  public GCode() {
    this.commands = new ArrayList<String>();
  }
}
