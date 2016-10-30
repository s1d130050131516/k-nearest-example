public class Record {
	public int x1;
	public int x2;
	public String className;

	Record (int x1, int x2, String className) {
		this.x1 = x1;
		this.x2 = x2;
		this.className = className;
	}

	public int distanceFrom(int x1, int x2) {
		return ((this.x1 - x1) * (this.x1 - x1)) + ((this.x2 - x2) * (this.x2 - x2));
	}
}
