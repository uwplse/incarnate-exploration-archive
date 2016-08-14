import java.util.*;
import java.io.*;

/* The GCodeObject stores commands associated with a gcode file (in .gcode or
 * .txt formats), where commands are listed in sequential order per line in the
 * file.
 */
public class GCodeObject {
  // Commands without parameters - usually indicating whether a state is set for
  // the print (e.g., metric units)
  private Set<BooleanCommand> booleanCommands;
  // G0/G1 (primary movement) Commands for processing
  private Queue<ParamCommand> moveCommands;
  // Commands with parameters (e.g., "G1 X1.0 Y2.0 Z1.2")
  private Map<String, List<ParamCommand>> paramCommands;

  // Constructs a GCodeObject representing the state of file associated with the passed GCode
  // filename (in .txt or .gcode formats)
  public GCodeObject(String filename) throws FileNotFoundException {
    this.booleanCommands = new TreeSet<BooleanCommand>();
    this.moveCommands = new LinkedList<ParamCommand>();
    this.paramCommands = new TreeMap<String, List<ParamCommand>>();
    Scanner seeker = new Scanner(new File(filename));  
    boolean instructions = false;
    int lineNumber = 0;
    while (seeker.hasNextLine()) {
      lineNumber++;
      String line = seeker.nextLine();
      // filter out comments
      if (!line.isEmpty() && line.charAt(0) != '(') {
        // ignore anything after a ";" in a command
        String[] parts = line.split(";")[0].split(" ", 2);
        String code = parts[0];
        if (code.equals("M117")) { // start of instructions
          instructions = true;
        }
        if (parts.length == 1) {
          if (!parts[0].trim().isEmpty()) {
            booleanCommands.add(new BooleanCommand(code, lineNumber));
          }
        } else {
          String params = parts[1];
          if (!paramCommands.containsKey(code)) {
            this.paramCommands.put(code, new ArrayList<ParamCommand>());
          }
          ParamCommand command = new ParamCommand(code, lineNumber, params);
          if (instructions && (code.equals("G0") || code.equals("G1"))) {
            this.moveCommands.add(command);
          }
          this.paramCommands.get(code).add(command);
        }
      }
    }
  }

  /* Returns Queue of tihs GCodeObject's G0 and G1 commands, in the order they
   * were found in the original GCode file. Many slicers consider these similar,
   * if not identical, "move" commands in the xyz plane. */
  public Queue<ParamCommand> getMoveCommands() {
    return new LinkedList<ParamCommand>(this.moveCommands);
  }

  /* Prints G0/G1 commands in order they were found in gcode file (helpful
   * for debugging purposes) */
  public void printZMoveCommands() {
    for (int i = 0; i < this.moveCommands.size(); i++) {
      ParamCommand next = this.moveCommands.remove();
      if (next.hasParam('Z')) {
        System.out.println(next);
      }
      this.moveCommands.add(next);
    }
  }

  /* Returns String representation of this GCodeObject, in the following format:
   *   X occurances of Y command
   *   Z is set
   * where each command X with parameter Y in this GCodeObject is printed first,
   * followed by each command Z set without parameters. */
  public String toString() {
    String result = "";
    for (String s : this.paramCommands.keySet()) {
      result += paramCommands.get(s).size() + " occurences of " + s + " command\n";
    }
    for (BooleanCommand bc : this.booleanCommands) {
      result += bc.getCommandName() + " is set\n";
    }
    return result;
  }
}
