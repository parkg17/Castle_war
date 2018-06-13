import java.awt.image.BufferedImage;

public class Textures {
	
	private SpriteSheet ss= null;
	
	public BufferedImage[] castle = new BufferedImage[2];
	public BufferedImage[] castle2 = new BufferedImage[2];
	public BufferedImage[] archer = new BufferedImage[2];
	public BufferedImage[] cavalry = new BufferedImage[2];
	public BufferedImage[] spearman = new BufferedImage[2];
	

	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet()); 
			
		getTextures();
	}
	
	private void getTextures() {
	
		castle[0] = ss.grabImage(1, 1, 64, 64);
		castle[1] = ss.grabImage(1, 3, 64, 64);

		castle2[0] = ss.grabImage(3, 1, 64, 64);
		castle2[1] = ss.grabImage(3, 3, 64, 64);

		archer[0] = ss.grabImage(5, 1, 32, 64);
		archer[1] = ss.grabImage(5, 3, 32, 64);
		
		cavalry[0] = ss.grabImage(4, 1, 32, 32);
		cavalry[1] = ss.grabImage(4, 2, 32, 32);

		spearman[0] = ss.grabImage(5, 1, 32, 32);
		spearman[1] = ss.grabImage(5, 2, 32, 32);
	}
}
