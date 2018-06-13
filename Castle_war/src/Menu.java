import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 40, 350, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 200, 350, 100, 50);
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Castle War", Game.WIDTH / 2 + 20, 130);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.setColor(Color.black);
		g.drawString("PLAY", playButton.x + 10, playButton.y + 35);
		g2d.draw(playButton);
		
		g.drawString("QUIT", quitButton.x + 10, quitButton.y + 35);
		g2d.draw(quitButton);	
				
	}

}
