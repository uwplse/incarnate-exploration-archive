import java.io.File;
import java.util.Scanner;

// stores gcode comments - we're not using this right now, but it's here if
// we decide to filter them out of our code.
public class Comments extends GCode {

	public Comments(String fileName) {
		super(fileName);
	}
	public void load(String fileName) throws IOException {
		File f	= new	File(fileName);
	  this.gcodeFileName = fileName;
	  Scanner seeker = new Scanner(f);
	  String	line = "";	
	  while (seeker.hasNextLine()) {
	  	line = seeker.nextLine();
	    if (line.startsWith("(")) {
	    	this.commands.add(line);
	    }
	  }
	}
	
	public void add(String line) {
		this.commands.add(line);
	}
	
}
