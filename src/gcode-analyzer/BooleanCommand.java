/* The BooleanCommand class represents a G-Code Command which has both a code value
 * (e.g., "G1" or "M20"), line number, but no parameters. The presence of a
 * BooleanCommand in a G-Code file indicates that the Command is "set" (e.g.,
 * G21 to indicate units are set to mm in the print). */
public class BooleanCommand extends Command {

  /* Constructs a BooleanCommand with given code and line number associated
   * with source file */
  public BooleanCommand(String code, int lineNumber) {
    super(code, lineNumber);
  }
}
