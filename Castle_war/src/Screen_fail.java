import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Screen_fail {
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 100);
		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString("FAIL..", Game.WIDTH / 2 - 140, 450);			
	}
}