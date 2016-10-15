import java.awt.Graphics;
import java.awt.Graphics2D;

public class MineNumbers {
	private int xPos;
	private int yPos;
	private String s;
	
	public MineNumbers(int number, int x, int y){
		s = String.valueOf(number);
		xPos = x;
		yPos = y;		
	}
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawString(this.s, this.xPos, this.yPos);
	}
}
