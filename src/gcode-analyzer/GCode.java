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

  // Note: this is just for testing purposes to look for patterns in
  // command counts
  public void process() {
    Map<String, Integer> counts = new TreeMap<String, Integer>();
    for (String command : this.commands) {
      if (!command.startsWith("(") && !command.startsWith(";")) {
        command = command.split(" ")[0];
        if (!counts.containsKey(command)) {
          counts.put(command, 1);
        }
        counts.put(command, counts.get(command) + 1);
      }
    }
    for (String key : counts.keySet()) {
      System.out.println("Command: " + key + " found " + counts.get(key) + " times.");
    }
  }
}
