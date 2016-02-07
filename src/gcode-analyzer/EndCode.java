import java.util.*;
import java.io.*;

// prints to a new file, and stores the commands of a preamble (start code)
public class EndCode extends GCode {
   
   public EndCode(String fileName) {
     super(fileName);
   } 
   
   // Pre : fileName exists
   // Post: loads end code commands into this EndCode object
   public void load(String fileName) throws IOException {
     File f = new File(fileName);
     this.gcodeFileName = fileName;
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
   // Post: writes this EndCode's commands to a new txt file with -p added to source gcode
   public void write() throws IOException {
    PrintWriter writer	= new	PrintWriter(this.eName(), "UTF-8");
    for	(String command :	this.commands)	{
   	  writer.println(command);
    }
    writer.close();
  }
  
  // returns txt file name in the format of "sourceFileName-p.txt"
  public String eName() {
    return gcodeFileName.substring(0, gcodeFileName.lastIndexOf("."))	+ "-e.txt";
  }
}   