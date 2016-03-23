import java.util.*;

// Superclass for gcode objects (Preamble, Instructions, EndCode). Each
// gcode object holds information for associated commands and the original
// gcode file name (e.g., "cube.gcode").
public class GCode {
  protected List<String> commands;
  protected String gcodeFileName;

  // stores file name for this gcode information, without any commands
  // stored.
  public GCode(String fileName)  {
    this.gcodeFileName = fileName;
    this.commands = new ArrayList<String>();
  }

  // adds given command to this gcode object's collection of commands.
  public void add(String s) {
    this.commands.add(s);
  }
}
