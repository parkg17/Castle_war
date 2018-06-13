import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

import EntityPackage.EntityCastle;
import EntityPackage.EntityUnit;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Castle War";
	
	private boolean running = false;
	private Thread thread; 
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage win = null;
	private BufferedImage fail = null;
	
	private Textures tex;
	private Controller controller;
	
	private Menu menu;
	private Screen_win screen_win;
	private Screen_fail screen_fail;

	public static enum STATE {
		MENU,
		GAME,
		WIN,
		FAIL
	};

	public static STATE State = STATE.MENU;
	
	public ArrayList<EntityCastle> ea;
	public LinkedList<EntityUnit> eb;
	
	public int countCastle = 0;
	public int countCastle2 = 0;
	
	public void init() {
		requestFocus();
		
		BufferedImageLoader loader = new BufferedImageLoader();  
		try {
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.png");
			win = loader.loadImage("/win.png");
			fail = loader.loadImage("/fail.png");
		}catch(IOException e) {
			e.printStackTrace();
		}
	
		
		tex = new Textures(this);
		menu = new Menu();
		screen_win = new Screen_win();
		screen_fail = new Screen_fail();
		controller = new Controller(tex, this);
		
		ea = controller.getEntityA();
		eb = controller.getEntityB();
		
		this.addMouseListener(new MouseInput());

		controller.createCastle();
	}
	
	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run() {
		init(); 
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames); // 이부분은 완성하고 지워도 될듯 
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(State == STATE.GAME) {
			controller.tick();
		} 
		
		for(int i = 0; i < ea.size(); i++) {
			if(ea.get(i).check_identifier() == 1) {
				countCastle++;
			} else if(ea.get(i).check_identifier() == 2) {
				countCastle2++;
			}
		}
		if(ea.size() == countCastle) {
			State = STATE.WIN;
		} else if(ea.size() == countCastle2) {
			State = STATE.FAIL;
		}
		countCastle = 0;
		countCastle2 = 0;

	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background, 0, 0, null);
		
		if(State == STATE.GAME) {
			controller.render(g);
			
			for(int i = 0; i < ea.size(); i++) {
				Font fnt0 = new Font("arial", Font.BOLD, 16);
				g.setFont(fnt0);
				g.setColor(Color.red);
				g.drawString(ea.get(i).check_unit_number_String(),(int)ea.get(i).getX() + 22, (int)ea.get(i).getY() + 43);
			}

		} else if(State == STATE.MENU) {
			menu.render(g);
		} else if(State == STATE.WIN) {
			g.drawImage(win, 0, 0, null);
			screen_win.render(g);
		} else if(State == STATE.FAIL) {
			g.drawImage(fail, 0, 0, null);
			screen_fail.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}

}
