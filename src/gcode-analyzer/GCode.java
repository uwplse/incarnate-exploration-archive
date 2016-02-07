import java.util.*;

// The GCode class contains a list of commands as strings and a filename string.
public class GCode {
  protected List<String> commands;
  protected String gcodeFileName;

	public GCode(String fileName)	{
		this.gcodeFileName = fileName;
	  this.commands = new ArrayList<String>();
  }

  // Returns line by line string of commands
	public String toString() {
	  String result = "";
	  for (String line : commands) {
	  	result += line + "\n";
	  }
	  return result;
	}

  // Adds the specified line to the commands
	public void add(String line) {
		this.commands.add(line);
	}
}
