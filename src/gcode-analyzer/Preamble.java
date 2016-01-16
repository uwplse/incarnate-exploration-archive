import java.util.*;
import java.io.*;

// prints to a new file, and stores the commands of a preamble (start code)
public class Preamble extends GCode {
   
   public Preamble() {
     super();
   } 
   
   // Pre : fileName exists
   // Post: loads start code commands into this Preamble object
   public void load(String fileName) throws IOException {
     File f = new File(fileName);
     this.gcodeFileName = fileName;
     Scanner seeker = new Scanner(f);
     String line = "";
     boolean start = false;     
     while (!start) {
       if (line.indexOf("start.gcode") != -1) {
         start = true;
       } else {
         line = seeker.nextLine();
       }   
     }
     while (start && seeker.hasNextLine()) {
       if (line.indexOf("end of start.gcode") != -1) {
         start = false;
       }
       this.commands.add(line);
       line = seeker.nextLine();
     }    
   }
   
   // Pre : this Preamble has a valid file name
   // Post: writes this Preamble's commands to a new txt file with -p added to source gcode
   public void write() throws IOException {
    PrintWriter writer	= new	PrintWriter(this.pName(), "UTF-8");
    for	(String command :	this.commands)	{
   	  writer.println(command);
    }
    writer.close();
  }
  
  // returns txt file name in the format of "sourceFileName-p.txt"
  public String pName() {
    return gcodeFileName.substring(0, gcodeFileName.lastIndexOf("."))	+ "-p.txt";
  }
}   
