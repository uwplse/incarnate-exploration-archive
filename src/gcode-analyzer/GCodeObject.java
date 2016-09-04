import java.util.*;
import java.io.*;

/* The GCodeObject stores commands associated with a gcode file (in .gcode or
 * .txt formats), where commands are listed in sequential order per line in the
 * file.
 * 
 * Note that this GCodeObject is currently only compatible with GCode files
 * produced by Simplify3D.
 * TODO: Extend Simplify3DGCodeObject from GCodeObject
 */
public class GCodeObject {
  // Commands without parameters - usually indicating whether a state is set for
  // the print (e.g., metric units)
  private Set<BooleanCommand> booleanCommands;
  // G0/G1 (primary movement) Commands for processing
  private Queue<ParamCommand> moveCommands;
  // Commands with parameters (e.g., "G1 X1.0 Y2.0 Z1.2")
  private Map<String, List<ParamCommand>> paramCommands;
  //private PrintSettings printSettings;
  
  /* ---------------- TESTED VARIABLES FOR DIMENSIONAL ANALYSIS ----------------- */
  private double bedTemperature; // degrees C
  private double bottomTopThickness; // mm (firstLayerPercentage * ZLayerHeight)
  private double extruderDiameter; // mm
  private double extruderRetractionSpeed; // mm/min
  private double extruderTemperature; // degrees C
  private double filamentDensity; // g/cm^3
  private double flowRate; 
  private double layerHeight; // ZLayerHeight, mm
  private double minimalLayerTime; // seconds
  private double extruderRetractionDistance; // mm
  private double shellThickness;
  private double travelSpeed;
  /* ---------------------------------------------------------------------------- */

  /* ------------------ SIMPLIFY3D-SPECIFIC G-CODE VARIABLES -------------------- */
  public static final String START_END_POINTS = "G28";
  
  // variables found in G-Code commands
  public static final String SET_BED_TEMPERATURE = "M190";
  public static final String EXTRUDER_TEMPERATURE = "M104";
  
  // variables found from Simplify3D settings printed to G-Code file
  public static final String EXTRUDER_DIAMETER = "extruderDiameter";
  public static final String EXTRUDER_RETRACTION_DISTANCE = "extruderRetractionDistance";
  public static final String EXTRUDER_RETRACTION_SPEED = "extruderRetractionSpeed";
  public static final String FILAMENT_DENSITY = "filamentDensity";
  public static final String LAYER_HEIGHT = "layerHeight"; 
  public static final String MINIMAL_LAYER_TIME = "minimalLayerSpeedTime";
  /* ---------------------------------------------------------------------------- */
  
  /* Constructs a GCodeObject representing the state of file associated with the passed GCode
   * filename (in .txt or .gcode formats) */
  public GCodeObject(String filename) throws FileNotFoundException {
    this.booleanCommands = new TreeSet<BooleanCommand>();
    this.moveCommands = new LinkedList<ParamCommand>();
    this.paramCommands = new TreeMap<String, List<ParamCommand>>();
    Scanner seeker = new Scanner(new File(filename));  
    boolean instructions = false;
    int lineNumber = 0;
    while (seeker.hasNextLine()) {
      lineNumber++;
      String line = seeker.nextLine();
      // Get variable settings that are set explicitly by Simplify3D after ';'
      if (line.startsWith(";")) {
    	  checkForVariable(line);
      } else {
	      if (!line.isEmpty() && line.charAt(0) != '(') {
	        // ignore anything after a ";" in a command
	        String[] parts = line.split("[;(]")[0].split(" ", 2);
	        String code = parts[0];
	        if (code.equals(START_END_POINTS)) { // start or end of instructions
	          instructions = !instructions;
	        }
	        if (parts.length == 1) {
	          if (!parts[0].trim().isEmpty()) {
	            booleanCommands.add(new BooleanCommand(code, lineNumber));
	          }
	        } else {
	          String params = parts[1];
	          if (!paramCommands.containsKey(code)) {
	            this.paramCommands.put(code, new ArrayList<ParamCommand>());
	          }
	          ParamCommand command = new ParamCommand(code, lineNumber, params);
	          if (instructions && (code.equals("G0") || code.equals("G1"))) {
	            this.moveCommands.add(command);
	          }
	          this.paramCommands.get(code).add(command);
	        }
	      }
	    }
    }
    // store the rest of the testing variables that can be found from G-Code commands rather
    // than the Simplify3D output
    storeStateVariables();
  }
  
  /* Processes the passed line and stores any associated setting value if found in
   * the line. */
  public void checkForVariable(String line) {
	  if (line.contains(EXTRUDER_DIAMETER)) {
		  this.extruderDiameter = Double.parseDouble(line.split(",")[1]);
		  System.out.println("Extruder diameter: " + this.extruderDiameter + "mm");
	  } else if (line.contains(EXTRUDER_RETRACTION_DISTANCE)) {
		  this.extruderRetractionDistance = Double.parseDouble(line.split(",")[1]);
		  System.out.println("Extruder retraction distance: " + this.extruderRetractionDistance + " mm");
	  } else if (line.contains(EXTRUDER_RETRACTION_SPEED)) {
		  this.extruderRetractionSpeed = Double.parseDouble(line.split(",")[1]);
		  System.out.println("Extruder retraction speed: " + this.extruderRetractionSpeed + " mm/min");
	  } else if (line.contains(FILAMENT_DENSITY)) {
		  this.filamentDensity = Double.parseDouble(line.split(",")[1]);
		  System.out.println("Filament density: " + this.filamentDensity + " g/cm^3");
	  } else if (line.contains(LAYER_HEIGHT)) {
		  this.layerHeight = Double.parseDouble(line.split(",")[1]);
		  System.out.println("Layer height: " + this.layerHeight + " mm");
	  } else if (line.contains(MINIMAL_LAYER_TIME)) {
		  this.minimalLayerTime = Double.parseDouble(line.split(",")[1]);
		  System.out.println("Minimal layer time: " + this.minimalLayerTime + " seconds");
	  }
	}
  
  /* Returns bed temperature (in degrees Celsius) set for printing. In practice,
   * the printer should not start printing until this temperature is reached. */
  public double getBedTemperature() {
    return this.bedTemperature;
  }
  
  /* Returns the diameter of the extruder head in mm */
  public double getExtruderDiameter() {
    return this.extruderDiameter;
  }
  
  public double getExtruderRetractionDistance() {
    return this.extruderRetractionDistance;
  }
  
  public double getExtruderRetractionSpeed() {
    return this.extruderRetractionSpeed;
  }
  
  /* Returns the temperature (in degrees Celsius) of the extruder head during a print */
  public double getExtruderTemperature() {
    return this.extruderTemperature;
  }
  
  /* Returns the density of the filament in g/cm^3. This is a value that should be input
   * in Simplify3D settings by a user, as it cannot otherwise be determined by the printer. */
  public double getFilamentDensity() {
    return this.filamentDensity;
  }
  
  /* Returns the height (in mm) between each Z level in a print */
  public double getLayerHeight() {
    return this.layerHeight;
  }
  
  /* Returns the minimum time the printer's extruder must stay at any z level 
   * to ensure consistent printing. */
  public double getMinimalLayerTime() {
    return this.minimalLayerTime;
  }
  
  /* Returns Queue of this GCodeObject's G0 and G1 commands, in the order they
   * were found in the original GCode file. Many slicers consider these similar,
   * if not identical, "move" commands in the xyz plane. */
  public Queue<ParamCommand> getMoveCommands() {
    return new LinkedList<ParamCommand>(this.moveCommands);
  }

  /* Prints G0/G1 commands in order they were found in gcode file (helpful
   * for debugging purposes) */
  public void printZMoveCommands() {
    for (int i = 0; i < this.moveCommands.size(); i++) {
      ParamCommand next = this.moveCommands.remove();
      if (next.hasParam('Z')) {
        System.out.println(next);
      }
      this.moveCommands.add(next);
    }
  }

  /* Processes parameter commands to store those that are associated with setting variables
   * used for analyzing prints. Only variables that are able to be derived by a single
   * G-Code command (e.g., M104 for setting extruder temperature) are stored. */
  private void storeStateVariables() {
    for (String code : this.paramCommands.keySet()) {
      ParamCommand pc = this.paramCommands.get(code).get(0);
      // check for a tested setting variable which sets a non-zero value
      if (code.equals(SET_BED_TEMPERATURE) && pc.getParamValue('S') != 0) {
        this.bedTemperature = pc.getParamValue('S');
        System.out.println("Bed temperature set to: " + pc.getParamValue('S') + " C");
      } else if (code.equals(EXTRUDER_TEMPERATURE) && pc.getParamValue('S') != 0) {
        this.extruderTemperature = pc.getParamValue('S');
        System.out.println("Extruder temperature set to: " + pc.getParamValue('S') + " C");
      }
    }
  }

  /* Returns String representation of this GCodeObject, in the following format:
   *   X occurrences of Y command
   *   Z is set
   * where each command X with parameter Y in this GCodeObject is printed first,
   * followed by each command Z set without parameters. */
  public String toString() {
    String result = "";
    for (String s : this.paramCommands.keySet()) {
      result += paramCommands.get(s).size() + " occurences of " + s + " command\n";
    }
    for (BooleanCommand bc : this.booleanCommands) {
      result += bc.getCommandName() + " is set\n";
    }
    return result;
  }
}
