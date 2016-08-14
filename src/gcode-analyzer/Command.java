/* The Command class represents a G-Code Command which has both a code value
 * (e.g., "G1" or "M20"), line number */
public class Command implements Comparable<Command> {
  private String code;
  private int lineNumber;

  /* Constructs a Command object with given code (e.g., "G1") and
   * line number from a  source file */
  public Command(String code, int lineNumber) {
    this.code = code;
    this.lineNumber = lineNumber;
  }

  /* Returns line number associated with this Command from the source file */
  public int getLineNumber() {
    return this.lineNumber;
  }

  /* Returns this GCode command name as String (e.g., "G1") */
  public String getCommandName() {
    return this.code;
  }

  /* Commands are compared by line number in ascending order */
  public int compareTo(Command other) {
    return this.getLineNumber() - other.getLineNumber();
  }
}
