/*
Author: Melissa Medsker-Galloway
PLSE Incarnate Project
Last Edited: 01.15.16 21:11
A basic G-Code analyzer to look at G-Code (currently reads corresponding
txt files) and analyze it.

Current features:
  creates Preamble and Instructions objects for separating gCode

  testZ tests whether z-indices are non-decreasing, so that the extruder head
  is never decreasing its z position during a print.

To do:
  Check preamble code. Currently this program doesn't always work with
  preamble code when Z is initially set to a negative value.
  Make 'Instruction' classes, N-code and 'unknown' subclasses
  Store G-Code in a List(s)
  ...
*/

import java.util.*;
import java.io.*;

public class GCodeAnalyzer {
  private static String usage = String.join("\n"
    , "Usage: java GCodeAnalyzer [file.gcode]"
  );

  public static void main(String[] args) throws IOException {
    if(args.length < 1) {
      System.out.println(usage);
      System.exit(1);
    }

    // store start code
    Preamble p = new Preamble();
    p.load(args[0]);
    p.write();
    // store instructions (the rest)
    Instructions instr = new Instructions();
    instr.load(args[0]);
    instr.write();

    System.out.println("Testing instructions for non-decreasing z-values: ");
    if (testZ(instr.instrName())) {
      System.out.println("Z positions are valid.");
    } else {
      System.out.println("Not all Z positions are valid. Make sure" +
                         " that subsequent Z values are always nondecreasing.");
    }
  }

  // Pre : Given file name exists in local folder, and the associated file
  //       is in standard G-code format.
  // Tests all G-code G1 move commands in given text file name.
  // If all subsequent Z movements are non-decreasing, then the
  // Z movements for the G-Code are considered valid.
  public static boolean testZ(String gCodeTextFile) throws IOException {
    // sets initial z index. *This may need to change depending on how
    // we work with preamble code, which may start with negative z index.
    double z = 0;
    // This is the scanner that looks through the entire text file
    Scanner seeker = new Scanner(new File(gCodeTextFile));

    String line;
    LineNumberReader r = new LineNumberReader(new FileReader(gCodeTextFile));
    while((line = r.readLine()) != null) {
      // we only care if this line has a G1 (move) command
      if (line.substring(0, 3).equals("G1 ")) {
        // This is the scanner that looks through tokens in the current line
        Scanner lineSeeker = new Scanner(line);
        while (lineSeeker.hasNext()) {
          // grab next token (ie. Z0.27 or E700)
          String part = lineSeeker.next();
          // we only care if this term works with the z-index. Z's without
          // a given index are ignored *need to check what these actually do*
          // *need to check if lower-case z's are valid*
          if (part.startsWith("Z") && part.length() > 1) {
            // take the associated z-index with this Z
            double currentZ = Double.parseDouble(part.substring(1));
            if (currentZ < z) {
              // we've found a case where a z index is less than a previous one.
              System.out.println("Violation at line " + r.getLineNumber());
              return false;
            } else {
              // make sure to update currentZ and keep looking
              z = currentZ;
            }
          }
        }
      }
    }
    // if we've reached this point, all G1 Z-indices are nondecreasing, and thus
    // Z movements are considered valid
    return true;
  }
}
