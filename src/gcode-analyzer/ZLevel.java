import java.util.*;

public class ZLevel {
	private double zindex;
	private Map<Double, Set<Double>> xmap;

	public ZLevel(double zindex) {
		this.zindex = zindex;
		this.xmap = new TreeMap<Double, TreeSet<Double>>();
	}

	public void add(double x, double y) {
		if (this.xmap(!containsKey(x)) {
			this.xmap.put(x, new TreeSet<Double>());
		}
		this.xmap.get(x).add(y);
	}

	public double getZIndex() {
		return this.zindex;
	}

	public Set<Double> getXValues() {
		return this.xmap.keySet();
	}

	public Set<Double> getYValues(double x) {
		if (!this.xmap.containsKey(x)) {
			return null;
		} else {
			return this.xmap.get(x);
		}
	}

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
