/*
Author: Melissa Medsker-Galloway, Paul Curry
PLSE Incarnate Project
Instructions class which contains the instructions in a gCode file as opposed to the preamble and end code.

Current features:
  Loading in instructions, printing out instructions to text file.
*/

import java.util.*;
import java.io.*;

 
public class Instructions extends GCode {

  public Instructions() {
    super();
  }
  
  // Pre: file nam exists
  // Fills the instructions object's commands with instruction commands from instruction part 
  // of the gCode file.
  public void load(String fileName) throws IOException {
    File f  = new File(fileName);
    this.gcodeFileName = fileName;
    Scanner seeker = new Scanner(f);
    String  line = "";
    boolean start = false;
    while (!start) {
      if (line.indexOf("end of start.gcode") != -1) {
        start = true;
      }
      line = seeker.nextLine();
    }
    while (seeker.hasNextLine()) {
      this.commands.add(line);
      line = seeker.nextLine();
    }
  }
  
  // Post: writes this Instructions's commands to a new txt file with -i added to source gcode
  public void write() throws IOException {
    String outputFileName = this.instrName();
    PrintWriter writer = new  PrintWriter(outputFileName, "UTF-8");
    for (String command : this.commands)  {
      writer.println(command);
    }
    writer.close();
  }

   // returns txt file name in the format of "sourceFileName-i.txt"
  public String instrName() {
    return gcodeFileName.substring(0, gcodeFileName.lastIndexOf(".")) + "-i.txt";
  }
  
}
