package gamelogic;

public class Star {
	private int coordinate_x = 0, coordinate_y = 0, type = 1, frame = 0;
	private boolean appear = false;

	public Star(int ex, int why, int type, int frame) {
		this.coordinate_x = ex;
		this.coordinate_y = why;
		this.type = type;
		this.frame = frame;

	}

	public Star(int ex, int why) {
		this.coordinate_x = ex;
		this.coordinate_y = why;

	}

	public void frameAdvance() {
		this.frame++;
	}

	public void frameAdvance(int x) {
		this.frame += x;
	}

	public void upAdvance() {
		this.coordinate_y--;
		if (coordinate_y < -40) {
			this.coordinate_y = 680;
		}

	}

	public void upAdvance(int up) {
		this.coordinate_y -= up;
		if (coordinate_y < 0) {
			this.coordinate_y += 680;
		}

	}

	public int getFrame() {
		return frame;
	}

	public int getType() {
		return type;
	}

	public int getX() {
		return coordinate_x;
	}

	public int getY() {
		return coordinate_y;
	}

	public boolean shouldAppear() {
		return appear;
	}

	public void setAppear(boolean should) {
		this.appear = should;

	}

}
