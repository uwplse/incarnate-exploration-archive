import java.util.*;
import java.io.*;

// Stores commands of EndCode, source gcode file name, and can write
// EndCode commands to a separate file.
public class EndCode extends GCode {
   // constructs an EndCode object for given gcode file name - initially,
   // no commands are stored.
   public EndCode(String fileName) {
     super(fileName);
   }

   // Pre : file name associated with this EndCode object exists
   // Post: stores end code commands into this object from gcode file
   // stored at construction.
   // TODO: not sure we need this - GCodeObject.java has a process method
   // that handles this, but we may want to work solely with <gcode>-e.txt
   // files?
   public void load() throws IOException {
     File f = new File(this.gcodeFileName);
     Scanner seeker = new Scanner(f);
     String line = "";
     boolean start = false;
     while (!start) {
       if (line.indexOf("End.gcode") != -1) {
         start = true;
       } else {
         line = seeker.nextLine();
       }
     }
     while (start && seeker.hasNextLine()) {
       if (line.indexOf("end End.gcode") != -1) {
         start = false;
       }
       this.commands.add(line);
       line = seeker.nextLine();
     }
   }

   // Pre : this EndCode has a valid file name
   // Post: writes this EndCode's commands to a new txt file with -e added to source gcode
   public void write() throws IOException {
    PrintWriter writer	= new	PrintWriter(this.eName(), "UTF-8");
    for	(String command :	this.commands)	{
   	  writer.println(command);
    }
    writer.close();
  }

  // returns txt file name in the format of "sourceFileName-e.txt"
  public String eName() {
    return gcodeFileName.substring(0, gcodeFileName.lastIndexOf("."))	+ "-e.txt";
  }
}
