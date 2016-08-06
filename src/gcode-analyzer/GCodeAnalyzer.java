import java.io.*;
import java.util.Queue;

/* The GCodeAnalyzer (v2) processes and analyzes a GCode file and allows a user
 * to run tests on the validity of the code corresponding to directions for a 3D
 * printer. Currently, this version supports testing for the following:
 *
 * Slicers: Slicer3D
 * Printers: Wanhao Duplicator6
 * Printing Material: PLA 
 *
 */
public class GCodeAnalyzer {

  public static void main(String[] args) throws FileNotFoundException {
    String filename = "";
    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "boat.txt";
    }
    GCodeObject gcode = new GCodeObject(filename);
    Queue<ParamCommand> moveCommands = gcode.getMoveCommands();
    System.out.println("Testing Non-decreasing Z-values in " + filename + ":");
    boolean validZValues = hasNonDecreasingZValues(moveCommands);
  }

  /* Returns whether the Z-values of the passed Queue of commands are sorted in ascending
   * order. Commands without Z-value parameters will be ignored. */
  public static boolean hasNonDecreasingZValues(Queue<ParamCommand> moveCommands) {
    double currentZ = 0.0;
    for (int i = 0; i < moveCommands.size(); i++) {
      ParamCommand next = moveCommands.remove();
      if (next.hasParam('Z')) {
        if (next.getParamValue('Z') < currentZ) {
          System.out.println("TEST FAILED: Move Command #" + i);
          System.out.println("Current Z-value was : " + currentZ);
          System.out.println("Next Z-value was : " + next.getParamValue('Z'));
          return false;
        } else {
          currentZ = next.getParamValue('Z');
        }
      }
    }
    System.out.println("TEST PASSED!");
    return true;
  }
}
