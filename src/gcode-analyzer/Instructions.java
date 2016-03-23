import java.util.*;
import java.io.*;

// The instructions class contains the chronological instructions of a
// gcode file. This is where all of the printing commands magic happens.
public class Instructions extends GCode {
  // Constructs instructions object with given file name. Initially, this
  // object stores no commands.
  public Instructions(String fileName) {
    super(fileName);
  }

  // Pre : fileName exists
  // Post: loads instruct commands from original gcode file into this Instructions object
  // TODO: not sure we need this - GCodeObject.java has a process method
  // that handles this, but we may want to work solely with <gcode>-i.txt files?
  public void load() throws IOException {
    File f	= new	File(this.gcodeFileName);
    Scanner seeker = new Scanner(f);
    String	line = "";
    boolean start =	false;
    while (!start) {
 	    if (line.indexOf("end of start.gcode") != -1) {
 		    start	= true;
 	    }
 	    line = seeker.nextLine();
    }
    while (seeker.hasNextLine()) {
      this.commands.add(line);
      line = seeker.nextLine();
    }
  }

  // Pre : File name stored at original construction is valid.
  // Post: writes this Instructions' commands to a new txt file with -i added to source gcode file name.
  public void write() throws IOException {
    String outputFileName = this.instrName();
    PrintWriter writer = new	PrintWriter(outputFileName, "UTF-8");
    for	(String command :	this.commands)	{
   	  writer.println(command);
    }
    writer.close();
  }

  // returns txt file name in the format of "sourceFileName-i.txt" 
  public String instrName() {
    return this.gcodeFileName.substring(0, gcodeFileName.lastIndexOf("."))	+ "-i.txt";
  }
}

