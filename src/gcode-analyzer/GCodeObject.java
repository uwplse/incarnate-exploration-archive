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

   // returns the bottom/top thickness which controls how many layers are
   // fully filed.
   // Cura docs: "Setting this higher gives a stronger part and setting it
   // lower gives a weaker part (which prints faster). In general, 0.6mm
   // gives strong parts without holes and going lower can cause holes
   // to appear at the top of your models."
   public double getBottomTopThickness() { return 0.0; }

   // returns the diameter of the filament in mm
   public double getFilamentDiameter() { return 0.0; }

   // returns the fill density (percentage), which controls the amount of
   // sparse infill that is made.
   // Cura docs: "A higher infill is more likely to cause some warping in
   // the part, on top of increasing the printing time. Therefore, it is
   // not recommended to use high infill value if not needed."
   public double getFillDensity() { return 0.0; }

   // returns flow rate of molten material
   public double getFlowRate() { return 0.0; }

   // returns speed that the nozzle has when filling the perimeter
   public double getInfillSpeed() { return 0.0; }

   // returns layer thickness of bottom layer in mm units. A thicker first
   // layer improves adhesion to bed. If 0, is the same layer height as
   // others.
   public double getInitialLayerThickness() { return 0.0; }

   // returns layer height(s) (mm) of the GCode file
   // Cura docs: "The layer height directly affects the quality of your
   // final print. The default of .2mm gives decent prints, but for high
   // quality prints is also possible to lower layer height to .1mm (note
   // that this will double the building time.)"
   public double getLayerHeight() { return 0.0; }

   // returns minimum time spent in a layer, which gives the layer time to
   // cool down until the next layer is printed. A higher value slows down
   // the printing speed of small layers.
   public double getMinimalLayerTime() { return 0.0; }

   // returns the extruding speed of the printer head in mm/s units.
   public double getPrintingSpeed() { return 0.0; }

   // returns printing temperature of the nozzle in degrees Celsius. The
   // printer head heats up to this temperature before starting the print.
   // (Default on Cura is 195 degrees Celsius).
   public double getPrintingTemperature() { return 0.0; }

   // returns shell thickness (mm) of the outside walls.
   // Cura docs: "Normal thickness of 0.8mm (2 perimeters) gives good results, but for
   // small prints it's sometimes better to use 0.4mm (1 perimeter)."
   public double getShellThickness() { return 0.0; }

}

