public class Command {
  private String code;
  private int lineNumber;

  public Command(String code, int lineNumber) {
    this.code = code;
    this.lineNumber = lineNumber;
  }

  public int getLineNumber() {
    return this.lineNumber;
  }

  /* Returns this GCode command name as String (e.g., "G1") */
  public String getCommandName() {
    return this.code;
  }
}