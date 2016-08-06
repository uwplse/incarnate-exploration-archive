import java.util.*;
import java.io.*;

public class GCodeObject {

  protected Map<String, List<ParamCommand>> paramCommands;
  protected Set<String> booleanCommands;
  protected Queue<ParamCommand> moveCommands;

  public GCodeObject(String filename) throws FileNotFoundException {
    this.paramCommands = new TreeMap<String, List<ParamCommand>>();
    this.booleanCommands = new TreeSet<String>();
    this.moveCommands = new LinkedList<ParamCommand>();
    Scanner seeker = new Scanner(new File(filename));  
    boolean instructions = false;;
    while (seeker.hasNextLine()) {
      String line = seeker.nextLine();
      // filter out comments
      if (!line.isEmpty() && line.charAt(0) != '(' && line.charAt(0) != ';') {
        String[] parts = line.split(" ", 2);
        String code = parts[0];
        if (code.equals("M117")) { // start of instructions
          instructions = true;
        }
        if (parts.length == 1) {
          booleanCommands.add(code);
        } else {
          String params = parts[1];
          if (!paramCommands.containsKey(code)) {
            this.paramCommands.put(code, new ArrayList<ParamCommand>());
          }
          ParamCommand command = new ParamCommand(code, params);
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
    return this.moveCommands;
  }

  public void printQueue() {
    for (int i = 0; i < this.moveCommands.size(); i++) {
      ParamCommand next = this.moveCommands.remove();
      if (next.hasParam('Z')) {
        System.out.println(next);
      }
      this.moveCommands.add(next);
    }
  }

  public String toString() {
    String result = "";
    for (String s : this.paramCommands.keySet()) {
      result += paramCommands.get(s).size() + " occurences of " + s + " command\n";
    }
    for (String s : this.booleanCommands) {
      result += s + " is set\n";
    }
    return result;
  }
}
