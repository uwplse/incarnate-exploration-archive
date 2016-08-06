import java.util.*;

/* The ParamCommand represents a G-Code Command which has both a code value
 * (e.g., "G1" or "M20") and parameters for that command (e.g., "X1.2") */
public class ParamCommand {
  private String code; // e.g., "G1"
  private Map<Character, Double> params; // e.g., 'X' -> 1.2, 'Y' -> 2.0

  /* Pre : code != null && params != null, otherwise throws IllegalArgumentException
   *       Parameters are in the format of : 'Nnn' where 'N' is the single-letter
   *       command and 'nn' is some numerical value
   * Post: Constructs a ParamCommand with given code (e.g., "G1") and String of params
   *       (e.g., "X1.2 Y2.0") */
  public ParamCommand(String code, String params) {
    if (code == null || params == null) {
      throw new IllegalArgumentException("ParamCommand requires code with at least one parameter");
    }
    this.params = new TreeMap<Character, Double>();
    this.code = code;

    // only consider code before comments, if present
    String[] parts = params.split(" "); // ["X1.2", "Y2.0"]
    for (String part : parts) {
      char paramType = part.charAt(0);
      if (!part.substring(1).contains(";") && !Character.isLetter(part.charAt(1))) {
        if (this.params.containsKey(paramType)) {
          throw new IllegalArgumentException("Duplicate " + paramType + 
            " parameters in " + code + " command.");
        }
        double paramValue = Double.parseDouble(part.substring(1));
        this.params.put(paramType, paramValue);
      }
    }
  }

  /* Returns this GCode command name as String (e.g., "G1") */
  public String getCommandName() {
    return this.code;
  }

  /* Pre : this ParamCommand has given paramType as a parameter, otherwise throws
   *       IllegalArgumentException
   * Post: Returns value associated with the given paramType */
  public double getParamValue(char paramType) {
    if (!this.params.containsKey(paramType)) {
      throw new IllegalArgumentException("This ParamCommand does not have given " + paramType + " parameter");
    }
    return this.params.get(paramType);
  }

  /* Returns a Map representing each of this GCode command's parameters and
   * their respective values (e.g., 'X' -> 1.2, 'Y' -> 2.0) */
  public Map<Character, Double> getParamValues() {
    return this.params; 
  }

  /* returns whether this ParamCommand has given paramType (e.g., 'Z') */
  public boolean hasParam(char paramType) {
    return this.params.containsKey(paramType);
  }

  /* Returns String representation of this Command with its code signature and
   * associated parameters */
  public String toString() {
    return this.code + ": " + this.params.toString();
  }
}
