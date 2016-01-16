import java.util.*;
import java.io.*;

public class Instructions extends GCode {
	
  public Instructions() {
    super();
  }
  
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
  
  public void write() throws IOException {
    String outputFileName = this.instrName();
    PrintWriter writer = new	PrintWriter(outputFileName, "UTF-8");
    for	(String command :	this.commands)	{
   	  writer.println(command);
    }
    writer.close();
  }
  
  public String instrName() {
    return gcodeFileName.substring(0, gcodeFileName.lastIndexOf("."))	+ "-i.txt";
}
}
  
