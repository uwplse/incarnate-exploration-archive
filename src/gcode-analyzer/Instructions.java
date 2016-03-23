import java.util.*;
import java.io.*;

// The instructions class contains the chronological instrustions of a gCode file

public class Instructions extends GCode {
	
  public Instructions(String fileName) {
    super(fileName);
  }
  
  // Pre : fileName exists
  // Post: loads start code commands into this Instructions object  
  public void load(String	fileName) throws IOException {
    File f	= new	File(fileName);
    this.gcodeFileName = fileName;
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

  // Pre : this Preamble has a valid file name
  // Post: writes this Instructions's commands to a new txt file with -i added to source gcode  
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
  
