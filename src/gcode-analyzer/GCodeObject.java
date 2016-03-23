import java.io.*;
import java.util.Scanner;

// The GCodeObject represents a file of gCode and contains fields for a preamble, instructions, and end code
// which are split up based on the code within the loaded .gcode file.

public class GCodeObject {
	private String gcodeFileName;

	private Preamble preamble;
	public Instructions instr;
	private EndCode endCode;

  // Constructor loads in a filename and fills in the preamble, endcode, and instruction fields.
	public GCodeObject(String fileName) throws IOException {
		this.preamble = new Preamble(fileName);
		this.instr = new Instructions(fileName);
		this.endCode = new EndCode(fileName);

		File f	= new	File(fileName);
	  this.gcodeFileName = fileName;
	  Scanner seeker = new Scanner(f);

		processCode(seeker);
		this.instr.write();

		this.preamble.write();
		this.endCode.write();
	}

  // Sets the preamble, instruction, and endcode fields given gcode from
  // seeker.
	public void processCode(Scanner seeker) {
		String line = seeker.nextLine();
		boolean start = true;
    // first process start.gcode (Preamble)
		while (start && seeker.hasNextLine()) {
			if (line.indexOf("end of start.gcode") != -1) {
				start = false;
			}
			this.preamble.add(line);
			line = seeker.nextLine();
  	}

    // System.out.println();
    // System.out.println("Command stats for Preamble:");
    // this.preamble.process();

    boolean end = false;
    // next, process gcode Instructions until end.gcode signal is reached
		while (!end) {
			if (line.indexOf("End.gcode") == -1) {
				this.instr.add(line);
				line = seeker.nextLine();
	  	} else {
				end = true;
			}
		}
    // System.out.println();
    // System.out.println("Command stats for Instructions:");
    // this.instr.process();

    // finally, process end.gcode (EndCode) until eof
		while (seeker.hasNextLine()) {
			this.endCode.add(line);
			line = seeker.nextLine();
		}
    // System.out.println();
    // System.out.println("Command stats for EndCode:");
    // this.endCode.process();
	}
}
