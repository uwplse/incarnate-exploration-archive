import java.util.*;

// Represents a single xy plane at a z level (e.g., z=2.3). Units
// are determined by Preamble code or G20 (inch) / G21 (mm) commands.

public class ZLevel {
	private double zindex;
  // x values => [y values]
  // ex: 2.1  => [2.1, 2.2, 2.5]
  //     2.2  => [2.1, 2.2]
  //     ...
	private Map<Double, Set<Double>> xmap;

  // constructs ZLevel with given zindex and no xy values
	public ZLevel(double zindex) {
		this.zindex = zindex;
		this.xmap = new TreeMap<Double, Set<Double>>();
	}

  // adds new xy pair to this ZLevel. If no y value for passed x value
  // exists, a new x coordinate is added to this plane. If xy pair already
  // exists in this ZLevel, the object remains unchanged.
  public void add(double x, double y) {
		if (!this.xmap.containsKey(x)) {
			this.xmap.put(x, new TreeSet<Double>());
		}
		this.xmap.get(x).add(y);
	}

  // returns z coordinate of this layer (e.g., 2.3)
	public double getZIndex() {
		return this.zindex;
	}

  // returns all x values in this XLevel
	public Set<Double> getXValues() {
		return this.xmap.keySet();
	}

  // returns sorted set of all y values associated with given x coordinate
  // in this ZLevel. If this ZLevel does not contain given x, returns null.
	public Set<Double> getYValues(double x) {
		if (!this.xmap.containsKey(x)) {
			return null;
		} else {
			return this.xmap.get(x);
		}
	}

  // Prints information about this ZLevel in the following format (angle
  // brackets omitted, coordinates given in decimal form, coordinate
  // categories printed in increasing order):
  // Z index: <Z Coordinate>
  //   X coordinate: <(1st) X Coordinate>
  //     Y coordinate: <Y Coordinate>
  //     Y coordinate: <Y coordinate>
  //     ...
  //   X coordinate: <(2nd) X Coordinate>
  //     Y coordinate: <Y Coordinate>
  //     ...
  //   ...
	public void printZLevel() {
		System.out.println("Z index: " + this.zindex);
		for(double x : this.xmap.keySet()) {
			System.out.format("\tX coordinate: %f%n", x);
			for (double y : this.xmap.get(x)) {
				System.out.format("\t\tY coordinate: %f%n", y);
			}
		}
	}
}
