package project2;

public class MppE {
	private String value;
   // private String type;
    private int x;
    private int y;
    private boolean check;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public MppE(String value, int x, int y,boolean check) {	
		this.value = value;
		this.x = x;
		this.y = y;
		this.check = check;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
}
