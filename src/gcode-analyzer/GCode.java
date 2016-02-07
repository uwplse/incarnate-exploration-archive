import java.util.*;

public class GCode {
  protected List<String> commands;
  protected String gcodeFileName;

	public GCode(String fileName)	{
		this.gcodeFileName = fileName;
	  this.commands = new ArrayList<String>();
  }

	public String toString() {
	  String result = "";
	  for (String line : commands) {
	  	result += line + "\n";
	  }
	  return result;
	}

	public void add(String line) {
		this.commands.add(line);
	}
}
