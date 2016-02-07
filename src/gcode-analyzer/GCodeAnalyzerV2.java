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

public class GCodeAnalyzerV2 {
   public static void main(String[] args) throws IOException {
      Scanner input = new Scanner(System.in);
      System.out.print("G-Code to test (must be txt file format): ");
      String testFile = input.next();
      GCodeObject gc = new GCodeObject(testFile);
      
      System.out.println("Testing instructions for non-decreasing z-values: ");
      if (testZ(gc.instr.instrName())) {
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
      int count = 0;
      while(seeker.hasNextLine()) {
         String line = seeker.nextLine();
         count++;
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
                  		System.out.print(count);
                     // we've found a case where a z index is less than a previous one.
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
               